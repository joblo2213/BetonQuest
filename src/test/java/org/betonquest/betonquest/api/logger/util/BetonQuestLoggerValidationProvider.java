package org.betonquest.betonquest.api.logger.util;

import org.betonquest.betonquest.api.logger.BetonQuestLogger;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.mockito.MockedStatic;

import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

/**
 * Resolves a {@link LogValidator} for JUnit 5 tests.
 */
public class BetonQuestLoggerValidationProvider implements ParameterResolver, BeforeAllCallback, AfterAllCallback {
    /**
     * The instance of the parent logger.
     */
    private final Logger parentLogger;
    /**
     * The MockedStatic instance of {@link BetonQuestLogger} class.
     */
    private MockedStatic<BetonQuestLogger> betonQuestLogger;

    /**
     * Default {@link BetonQuestLoggerValidationProvider} Constructor.
     */
    public BetonQuestLoggerValidationProvider() {
        parentLogger = Logger.getAnonymousLogger();
    }

    @Override
    public void beforeAll(final ExtensionContext context) {
        betonQuestLogger = mockStatic(BetonQuestLogger.class);
        betonQuestLogger.when(() -> BetonQuestLogger.create(any(), any())).thenAnswer(invocation ->
                new BetonQuestLogger(parentLogger, invocation.getArgument(0), invocation.getArgument(1)));
    }

    @Override
    public void afterAll(final ExtensionContext context) {
        betonQuestLogger.close();
        betonQuestLogger = null;
    }

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == LogValidator.class;
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext, final ExtensionContext extensionContext) {
        return LogValidator.getForLogger(parentLogger);
    }
}
