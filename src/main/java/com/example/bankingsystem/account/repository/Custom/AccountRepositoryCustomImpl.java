package com.example.bankingsystem.account.repository.Custom;

import com.example.bankingsystem.account.model.Account;
import com.example.bankingsystem.account.dto.AccountSearchCriteria;
import com.example.bankingsystem.account.repository.Projection.AccountSearchProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Account> findAccounts(AccountSearchCriteria searchCriteria) {

        CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = criteria.createQuery(Account.class);
        Root<Account> account = query.from(Account.class);

        List<Predicate> predicates = new ArrayList<>();

        if(searchCriteria.getStatus() != null) {
            predicates.add(criteria.equal(account.get("status"), searchCriteria.getStatus()));
        }

        if(searchCriteria.getAccountNumber() != null) {
            predicates.add(criteria.equal(account.get("accountNumber"), searchCriteria.getAccountNumber()));
        }

        if(searchCriteria.getMinBalance() != null) {
            predicates.add(criteria.ge(account.get("balance"), searchCriteria.getMinBalance()));
        }

        if(searchCriteria.getMaxBalance() != null) {
            predicates.add(criteria.le(account.get("balance"), searchCriteria.getMaxBalance()));
        }

        if(searchCriteria.getAccountType() != null) {
            predicates.add(criteria.equal(account.get("accountType"), searchCriteria.getAccountType()));
        }

        query.select(account).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
