package com.chukwuma.sdet.extensions;

import com.chukwuma.sdet.utilities.EnvironmentHealthCheck;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class EnvironmentCondition implements ExecutionCondition {

    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com";

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(
            ExtensionContext context) {

        boolean available = EnvironmentHealthCheck.isEnvironmentAvailable(BASE_URL);

        if (available) {
            return ConditionEvaluationResult.enabled(
                    "Environment available");
        }

        return ConditionEvaluationResult.disabled(
                "Environment unavailable. Skipping UI tests.");
    }
}