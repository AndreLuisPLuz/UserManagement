package com.javaProject.startup.project.exceptions;

import org.hibernate.query.sqm.sql.ConversionException;

public class ClaimsConversionException extends ConversionException {
    public ClaimsConversionException(String explanation) {
        super(explanation);
    }
}
