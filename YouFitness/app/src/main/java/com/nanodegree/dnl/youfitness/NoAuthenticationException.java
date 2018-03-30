package com.nanodegree.dnl.youfitness;

public class NoAuthenticationException extends Exception {

    @Override
    public String getMessage() {
        return "No user is authenticated. Authentication is required.";
    }
}
