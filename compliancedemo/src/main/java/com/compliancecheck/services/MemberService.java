package com.compliancecheck.services;

import com.compliancecheck.model.AccountListOfUser;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    AccountListOfUser getUserAccounts(String memberNumber);
    boolean isNumeric(String number);
}
