package com.dateformatchecker.services;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class DaysAddingSpec {
    @InjectMocks
    private DateFormatChecker dateformatChecker;

    @Test
    public void testAddDaysMethod(){
       Assertions.assertAll(
               () -> assertEquals("19-11-2020",dateformatChecker.addDays("20-10-2020","30")) ,
               () -> assertEquals("29-11-2020",dateformatChecker.addDays("20-10-2020","40")),
               () -> assertEquals("09-12-2020",dateformatChecker.addDays("20-10-2020","50")),
               () -> assertEquals("Date Cannot be added",dateformatChecker.addDays("20-10-2020","270"))
               );
    }

    @Test
    public void testAddDaysMethodWithWrongValues(){
        Assertions.assertAll(
                () -> assertNotEquals("09-11-2020",dateformatChecker.addDays("23-10-2020","30")),
                () -> assertNotEquals("22-11-2020",dateformatChecker.addDays("25-10-2020","40")),
                () -> assertNotEquals("25-12-2020", dateformatChecker.addDays("23-11-2020","50")),
                () -> assertNotEquals("Proper choice",dateformatChecker.addDays("25-11-2020","80"))

        );
    }


    @Test
    public void dateformatMismatchExceptionTest(){
        Exception exception = assertThrows(DateFormatMismatchException.class,() -> dateformatChecker.addDays("2020-31-10","50"));
        if(exception != null)
            assertEquals("DateFormatMismatchException",exception.getClass().getSimpleName());
    }


}
