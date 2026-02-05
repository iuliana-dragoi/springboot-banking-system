package com.example.bankingsystem.account.service;

import com.example.bankingsystem.account.exception.AccountNotFoundException;
import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.model.AccountStatus;
import com.example.bankingsystem.account.model.AccountType;
import com.example.bankingsystem.account.model.CurrentAccount;
import com.example.bankingsystem.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountOrchestrator {

    private final AccountRepository repository;
    private final Map<Long, Account> cache = new ConcurrentHashMap<>();

    /*** ARRAYLIST ***/

    public AccountOrchestrator(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> loadAllAccounts() {
        return new ArrayList<>(repository.findAll());
    }

    public LinkedList<Account> loadAccountsForBatchProcessing() {
        return new LinkedList<>(repository.findAll());
    }

    public Account[] toArray(List<Account> accounts) {
        return accounts.toArray(new Account[0]);
    }

    /*** SET ***/

    public Set<String> uniqueAccountNumbers(List<Account> accounts) {
        Set<String> result = new HashSet<>();
        for(Account account : accounts) {
            result.add(account.getAccountNumber());
        }
        return result;
    }

    public Set<Account> orderedUniqueAccounts(List<Account> accounts) {
        return new LinkedHashSet<>(accounts);
    }

    public Set<Account> sortedByBalance(List<Account> accounts) {
        Set<Account> sorted = new TreeSet<>(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getBalance().compareTo(o2.getBalance());
            }
        });
        sorted.addAll(accounts);
        return sorted;
    }

    /*** MAP ***/

    public Map<Long, Account> indexById(List<Account> accounts) {
        Map<Long, Account> map = new HashMap<>();
        for(Account account : accounts) {
            map.put(account.getId(), account);
        }
        return map;
    }

    public Map<Long, Account> indexByAccountNumber(List<Account> accounts) {
        Map<Long, Account> map = new LinkedHashMap<>();
        for(Account account : accounts) {
            map.put(account.getId(), account);
        }
        return map;
    }

    public Map<Long, Account> sortedByAccountNumber(List<Account> accounts) {
        Map<Long, Account> map = new TreeMap<>();
        for(Account account : accounts) {
            map.put(account.getId(), account);
        }
        return map;
    }

    /*** GENERICS ***/

    public void printAccounts(List<? extends Account> accounts) {
        for(Account account : accounts) {
            System.out.println(account.getAccountNumber());
        }
    }

    public void addCurrentAccount(List<? super CurrentAccount> list, CurrentAccount account) {
        list.add(account);
    }

    /*** ITERATOR ***/

    public void removeClosedAccounts(List<Account> accounts) {
        Iterator<Account> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            Account account = iterator.next();
            if(account.getStatus() == AccountStatus.CLOSED) {
                iterator.remove();
            }
        }
    }

    /*** LISTITERATOR ***/

    public void markNegativeBalances(List<Account> accounts) {
        ListIterator<Account> it = accounts.listIterator();
        while (it.hasNext()) {
            Account account = it.next();
            if(account.getBalance().signum() < 0) {
                account.setStatus(AccountStatus.BLOCKED);
            }
        }
    }

    /*** QUEUE ***/

    public Queue<Account> priorityQueueByBalance(List<Account> accounts) {
        Queue<Account> queue = new PriorityQueue<>(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getBalance().compareTo(o2.getBalance());
            }
        });

        queue.addAll(accounts);
        return queue;
    }

    /*** STACK via DEQUE ***/

    public Deque<Account> accountStack(List<Account> accounts) {
        Deque<Account> stack = new ArrayDeque<>();
        for(Account account : accounts) {
            stack.push(account);
        }
        return stack;
    }

    /*** Collections utility class ***/

    public void sortAccounts(List<Account> accounts) {
        accounts.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o1.getBalance().compareTo(o2.getBalance());
            }
        });
    }

    /*** Comparator vs Comparable ***/

    public static class BalanceComparator implements Comparator<Account> {

        @Override
        public int compare(Account o1, Account o2) {
            return o1.getBalance().compareTo(o2.getBalance());
        }
    }

    public void sortAccountsByBalance(List<Account> accounts)  {
        accounts.sort(new BalanceComparator());
    }

    /*** Immutable collections ***/

    public List<AccountType> supportedTypes() {
        return List.of(AccountType.CURRENT, AccountType.SAVINGS);
    }

    /*** Concurrent  collections ***/

    public Account getCachedAccount(Long id) {
        Account cached = cache.get(id);
        if(cached != null) {
            return cached;
        }

        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found!"));
        cache.putIfAbsent(id, account);
        return account;
    }

}
