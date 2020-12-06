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
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class DateFormatCheckerApplicationTests {
	private final Logger logger = LogManager.getLogger(DateFormatCheckerApplicationTests.class);

	@Autowired
	DateFormatChecker myChecker;



}
