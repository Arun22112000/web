package com.banner.qb.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewsException extends Exception {
    private String message;

    public NewsException(String message) {
        super(message);
    }
}
