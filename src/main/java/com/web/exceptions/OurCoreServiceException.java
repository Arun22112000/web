package com.banner.qb.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OurCoreServiceException extends Exception {
    private String message;

    public OurCoreServiceException(String message) {
        super(message);
    }
}
