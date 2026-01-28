package com.example.bankingsystem.account.repository;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.repository.Custom.AccountRepositoryCustom;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom, JpaSpecificationExecutor<Account> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select a from Account a where a.accountNumber =: accountNumber")
    Optional<Account> findForUpdateByAccountNumber(@Param("accountNumber") String accountNumber);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select a from Account a where a.id =: accountId")
    Optional<Account> findForUpdateByAccountNumber(@Param("accountId") Long accountId);

    List<Account> findByStatus(AccountStatus status);

    List<Account> findByBalanceGreaterThanOrderByBalanceDesc(BigDecimal amount);

    Page<AccountSearchProjection> findAllByStatus(AccountStatus status, Pageable pageable);

    Page<AccountSearchProjection> findAllByStatusIn(Set<AccountStatus> statuses, Pageable pageable);

    Page<AccountSearchProjection> findAllByStatusAndType(AccountStatus status, AccountType type, Pageable pageable);

    boolean existsByAccountNumber(String accountNumber);
}
