package com.compliancecheck.repository;

import com.compliancecheck.model.AccountListOfUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
    AccountListOfUser getUserAccounts(String memberNumber);
}
