package com.javaProject.startup.project.exceptions;

import javax.naming.ConfigurationException;

public class BadHashConfigurationException extends ConfigurationException {
    public BadHashConfigurationException() {
        super();
    }

    public BadHashConfigurationException(String explanation) {
        super(explanation);
    }
}
