package com.dell.DiscoveryYou.Exception;

public class UserAlreadyExists extends Exception {
    public UserAlreadyExists(String errorMessage) {
        super(errorMessage);
    }
}
