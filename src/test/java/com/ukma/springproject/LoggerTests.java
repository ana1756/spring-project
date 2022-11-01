package com.ukma.springproject;

import com.ukma.springproject.logger.MapAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggerTests {
    @Test
    public void givenLoggerWithDefaultConfig_whenLogToConsole_thenOK()
            throws Exception {
        Logger logger = LogManager.getLogger(getClass());
        Exception e = new RuntimeException("This is only a test!");

        logger.debug("Debug message");
        logger.info("This is a simple message at INFO level. " +
                "It will be hidden.");
        logger.error("This is a simple message at ERROR level. " +
                "This is the minimum visible level.", e);
    }

    @Test
    public void MapLoggerTest()
            throws Exception {
        Logger logger = LogManager.getLogger(getClass());
        Exception e = new RuntimeException("This is only a test!");

        logger.debug("Debug message");
        logger.info("This is a simple message at INFO level. " +
                "It will be hidden.");
        logger.error("This is a simple message at ERROR level. " +
                "This is the minimum visible level.", e);
    }
}
