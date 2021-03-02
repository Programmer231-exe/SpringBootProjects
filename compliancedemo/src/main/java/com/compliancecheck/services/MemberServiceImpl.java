package com.compliancecheck.services;


import com.compliancecheck.model.AccountListOfUser;
import com.compliancecheck.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountListOfUser getUserAccounts(String memberNumber) {
        return accountRepository.getUserAccounts(memberNumber);
    }

    @Override
    public boolean isNumeric(String number) {
        return number != null && number.matches("\\d*");
    }
}
