package com.banner.qb.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BannerException extends Exception {
    private String message;

    public BannerException(String message) {
        super(message);
    }
}
