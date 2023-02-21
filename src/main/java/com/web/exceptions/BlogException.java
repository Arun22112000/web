package com.banner.qb.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlogException extends Exception {
    private String message;

    public BlogException(String message) {
        super(message);
    }
}
