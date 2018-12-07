package ua.training.logger;

        import org.junit.jupiter.api.AfterAll;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.TestInfo;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.junit.jupiter.api.extension.ExtensionContext;
        import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.slf4j.MDC;

/**
 * Simple test lifecycle logger based on JUnit 5 lifecycle and extension model.
 * Provides basic logging for its subclasses.
 */

@ExtendWith(TestLifecycleLogger.class)
public class TestLifecycleLogger implements TestExecutionExceptionHandler {
    protected static final Logger LOG = LoggerFactory.getLogger(TestLifecycleLogger.class);

    private static final String LOG_SIFTING_PARAM = "className";

    /*
     * JUnit 5 creates independent instances for all of it's extensions.
     * Static counters and flag below are required for TestLifecycleLogger-as-TestExecutionExceptionHandler
     * instance and TestLifecycleLogger instance itself work with the same variables.
     *
     * See JUnit 5 extension model details at:
     * https://junit.org/junit5/docs/current/user-guide/#extensions
     */
    private static int testsOverall;
    private static int testsFailed;
    private static boolean isLastTestSuccessful = true;

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        LOG.info("Exception thrown during test execution: ", throwable);
        if (throwable instanceof AssertionError) {
            testsFailed++;
            isLastTestSuccessful = false;
        }
        throw throwable;
    }

    @BeforeAll
    static void initAll(TestInfo testInfo) {
        // Add tested class name to logging context
        String className = testInfo.getDisplayName();
        MDC.put(LOG_SIFTING_PARAM, className);
        LOG.info("Testing {}", className);
    }

    @BeforeEach
    void initEach(TestInfo testInfo) {
        LOG.info("Starting test {} {}", testInfo.getTags(), testInfo.getDisplayName());
    }

    @AfterEach
    void tearDownEach(TestInfo testInfo) {
        String testStatus;

        testsOverall++;
        if (isLastTestSuccessful) {
            testStatus = "PASSED";
        }
        else {
            testStatus = "FAILED";
            isLastTestSuccessful = true;        // drop flag before next test
        }

        LOG.info("Test {} {} - {}", testInfo.getTags(), testInfo.getDisplayName(), testStatus);
    }

    @AfterAll
    static void tearDownAll() {
        int testsPassed = testsOverall-testsFailed;
        String result = (testsPassed == testsOverall)? "SUCCESS": "FAIL";
        LOG.info("Result of {} tests: {}/{} passed - {}", MDC.get(LOG_SIFTING_PARAM), testsPassed, testsOverall, result);

        // Clear logging context
        MDC.remove(LOG_SIFTING_PARAM);
        // Reset counters
        testsOverall=0;
        testsFailed=0;
    }
}