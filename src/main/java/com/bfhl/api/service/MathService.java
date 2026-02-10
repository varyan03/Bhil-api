package com.bfhl.api.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for mathematical operations
 */
@Service
public class MathService {

    /**
     * Generate Fibonacci series up to the nth term
     * 
     * @param n the number of terms to generate
     * @return list of Fibonacci numbers
     */
    public List<Integer> generateFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        List<Integer> fibonacci = new ArrayList<>();

        if (n == 0) {
            return fibonacci;
        }

        if (n >= 1) {
            fibonacci.add(0);
        }

        if (n >= 2) {
            fibonacci.add(1);
        }

        for (int i = 2; i < n; i++) {
            int next = fibonacci.get(i - 1) + fibonacci.get(i - 2);
            fibonacci.add(next);
        }

        return fibonacci;
    }

    /**
     * Filter prime numbers from the input list
     * 
     * @param numbers list of integers to filter
     * @return list containing only prime numbers
     */
    public List<Integer> filterPrimes(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty");
        }

        return numbers.stream()
                .filter(this::isPrime)
                .collect(Collectors.toList());
    }

    /**
     * Calculate LCM of a list of numbers
     * 
     * @param numbers list of integers
     * @return LCM value
     */
    public int calculateLCM(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty");
        }

        if (numbers.contains(0)) {
            return 0;
        }

        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = lcm(result, numbers.get(i));
        }

        return result;
    }

    /**
     * Calculate HCF/GCD of a list of numbers
     * 
     * @param numbers list of integers
     * @return HCF/GCD value
     */
    public int calculateHCF(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input list cannot be null or empty");
        }

        int result = numbers.get(0);
        for (int i = 1; i < numbers.size(); i++) {
            result = gcd(result, numbers.get(i));
        }

        return result;
    }

    // Helper methods

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    private int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        a = Math.abs(a);
        b = Math.abs(b);

        return (a * b) / gcd(a, b);
    }
}
