package com.compliancecheck.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountListOfUser {
    private String memberNumber;
    private List<Account> accountList;
    private LocalDate currentDate;
    private String message;
}
