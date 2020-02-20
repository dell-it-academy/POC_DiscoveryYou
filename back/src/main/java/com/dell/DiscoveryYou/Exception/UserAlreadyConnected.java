package com.dell.DiscoveryYou.Exception;

public class UserAlreadyConnected extends Exception {
    public UserAlreadyConnected(String errorMessage) {
        super(errorMessage);
    }
}
