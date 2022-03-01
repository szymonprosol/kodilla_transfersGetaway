package com.kodilla.transfersgateway.controller;

public class NoFundsInTheAccountException extends Exception{
    public NoFundsInTheAccountException() {
        System.out.println("No funds in the account exception");
    }
}
