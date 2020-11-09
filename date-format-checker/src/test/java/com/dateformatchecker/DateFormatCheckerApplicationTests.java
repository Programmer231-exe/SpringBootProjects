package com.dateformatchecker;

import com.dateformatchecker.exceptionhandling.DateFormatMismatchException;
import com.dateformatchecker.services.DateFormatChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class DateFormatCheckerApplicationTests {
	private final Logger logger = LogManager.getLogger(DateFormatCheckerApplicationTests.class);

	@Autowired
	DateFormatChecker myChecker;

	@Test
	public void isUserDateBeforeOrEqualsConstantDate(){
			assertTrue("Assertion Fails",myChecker.checkDateFormat("2020-10-10"));
			logger.info("Entered Date is before or on constant date");
	}

	@Test
	public void isUserDateAfterConstantDate(){
		assertFalse("Assertion Fails",myChecker.checkDateFormat("2020-10-30"));
		logger.info("Entered Date is after Constant date");
	}

	@Test
	public void wrongformat(){
		assertEquals("DateFormatMismatchException",assertThrows(DateFormatMismatchException.class,
				() -> {
					myChecker.checkDateFormat("2020/10/30");
				}).getClass().getSimpleName());
		logger.info("Expected DateFormatMismatchException");
	}

}
