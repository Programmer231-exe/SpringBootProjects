package com.compliancecheck.repository;

import com.compliancecheck.model.Account;
import com.compliancecheck.model.AccountListOfUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Repository
public class AccountRepositoryImpl implements AccountRepository{

    private static final Logger LOGGER = LogManager.getLogger(AccountRepositoryImpl.class);
    @Override
    public AccountListOfUser getUserAccounts(String memberNumber) {
        AccountListOfUser result = new AccountListOfUser();
        List<Account> accountList = getAllAccountsByMemberNumber(memberNumber,"");
        if(accountList.size() == 0) {
            result.setMemberNumber(memberNumber);
            result.setMessage("Account is something");
        }else{
            result.setMemberNumber(memberNumber);
            result.setAccountList(accountList);
            result.setCurrentDate(LocalDate.now());
            result.setMessage("Account is not something");

        }
        return result;
    }

    private List<Account> getAllAccountsByMemberNumber(String memberNumber, String path){
        List<Account> accountList = new ArrayList<>();
        int header = 0;
        if(path == ""){
            path = "database/AccountDetails.xlsx";
        }

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream file = classLoader.getResourceAsStream(path);
        try{
            XSSFWorkbook book = new XSSFWorkbook(file);
            XSSFSheet sheet = book.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            while (itr.hasNext())
            {
                if (header == 0){
                    header = 1;
                    itr.next();
                    continue;
                }else {
                    Row row = itr.next();
                    if(memberNumber.equals(new BigDecimal(row.getCell(1).toString()).toPlainString())){
                        Account account = new Account();
                        account.setProductCD(row.getCell(0).toString());
                        account.setAccountNumber(new BigDecimal(row.getCell(2).toString()).toPlainString());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                        account.setNonComplaintDate(LocalDate.parse(row.getCell(3).toString(), dtf));
                        LOGGER.debug(account);
                        accountList.add(account);
                    }

                }
            }
        }catch(IOException ioe){
            LOGGER.error(ioe.getMessage());
        }
        return accountList;

    }
}
