package com.fitness.accountservice.managers;

public interface TotpManager {

    String generateSecret ();

    boolean validateCode (String secret);

}
