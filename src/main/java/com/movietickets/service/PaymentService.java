package com.movietickets.service;

public class PaymentService {
    public boolean processPayment(double amount, String customerEmail) {
        // This is to simulate external payment gateway
        // In real application, this code would call an external payment gateway like Klarna, GooglePay, etc.
        // For testing, I'm gonna mock this!
        return amount > 0;
    }
}