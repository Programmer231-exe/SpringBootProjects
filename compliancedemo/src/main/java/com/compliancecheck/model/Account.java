package com.compliancecheck.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    private String accountNumber;
    private String productCD;
    private LocalDate nonComplaintDate;
}


