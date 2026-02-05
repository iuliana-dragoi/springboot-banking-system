package com.example.bankingsystem.account.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class EventListenerMetricsAspect {

    private final MeterRegistry meterRegistry;

    @Around("@annotation(org.springframework.context.event.EventListener) || " +
            "@annotation(org.springframework.transaction.event.TransactionalEventListener)")
    public Object measureEventListener(ProceedingJoinPoint joinPoint) throws Throwable {

        // Extract metadata
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String listenerClass = signature.getDeclaringType().getSimpleName();
        String listenerMethod = signature.getMethod().getName();

        // Extract event type from first parameter
        String eventType = "unknown";
        if (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] != null) {
            eventType = joinPoint.getArgs()[0].getClass().getSimpleName();
        }

        log.debug("Measuring event listener: {}.{} for event: {}", listenerClass, listenerMethod, eventType);

        // Start timing
        Timer.Sample sample = Timer.start(meterRegistry);

        // Increment invocation counter
        Counter.builder("spring.events.listener.invocations")
                .description("Number of times event listener was invoked")
                .tag("listener_class", listenerClass)
                .tag("listener_method", listenerMethod)
                .tag("event_type", eventType)
                .register(meterRegistry)
                .increment();

        try {
            // Execute the listener
            Object result = joinPoint.proceed();

            // SUCCESS - Record metrics
            recordSuccess(sample, listenerClass, listenerMethod, eventType);

            return result;

        } catch (Throwable ex) {
            // FAILURE - Record metrics
            recordFailure(sample, listenerClass, listenerMethod, eventType, ex);

            // Re-throw the exception
            throw ex;
        }
    }

    private void recordSuccess(Timer.Sample sample, String listenerClass, String listenerMethod, String eventType) {
        // Stop timer and record execution time
        sample.stop(Timer.builder("spring.events.listener.execution")
                .description("Time taken to execute event listener")
                .tag("listener_class", listenerClass)
                .tag("listener_method", listenerMethod)
                .tag("event_type", eventType)
                .tag("status", "success")
                .register(meterRegistry));

        // Increment success counter
        Counter.builder("spring.events.listener.success")
                .description("Successful event listener executions")
                .tag("listener_class", listenerClass)
                .tag("listener_method", listenerMethod)
                .tag("event_type", eventType)
                .register(meterRegistry)
                .increment();

        log.debug("Event listener succeeded: {}.{}", listenerClass, listenerMethod);
    }

    private void recordFailure(Timer.Sample sample, String listenerClass, String listenerMethod, String eventType, Throwable ex) {
        String exceptionType = ex.getClass().getSimpleName();

        // Stop timer and record execution time
        sample.stop(Timer.builder("spring.events.listener.execution")
                .description("Time taken to execute event listener")
                .tag("listener_class", listenerClass)
                .tag("listener_method", listenerMethod)
                .tag("event_type", eventType)
                .tag("status", "failure")
                .register(meterRegistry));

        // Increment failure counter
        Counter.builder("spring.events.listener.failures")
                .description("Failed event listener executions")
                .tag("listener_class", listenerClass)
                .tag("listener_method", listenerMethod)
                .tag("event_type", eventType)
                .tag("exception_type", exceptionType)
                .register(meterRegistry)
                .increment();

        log.error("Event listener failed: {}.{} for event {} - Exception: {}", listenerClass, listenerMethod, eventType, exceptionType, ex);
    }
}
