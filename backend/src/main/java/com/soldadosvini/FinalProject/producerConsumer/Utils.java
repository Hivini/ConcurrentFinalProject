package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Random;

public class Utils {

    private static final int MAX_RANDOM = 100;
    
    /* TODO(hivini): Call Scheme program instead. */
    public static String resolveTask(String task) {
        // Get all elements that are separated by a whitespace.
        String[] elements = task.split("\\s+");

        char operation = elements[1].charAt(0);
        int current = 3;
        float result = Float.parseFloat(elements[2]);
        while (current < elements.length) {
            if (elements[current].equals(")")) break;
            switch (operation) {
                case '+':
                    result += Float.parseFloat(elements[current++]);
                    break;
                case '-':
                    result -= Float.parseFloat(elements[current++]);
                    break;
                case '/':
                    // TODO(hivini): Zero not handled yet.
                    result /= Float.parseFloat(elements[current++]);
                    break;
                case '*':
                    result *= Float.parseFloat(elements[current++]);
                    break;
                default:
                    // Fall through
            }
        }
        return String.format("%.2f", result);
    }
    
    public static String generateTask() {
        Random random = new Random();

        double val1 = random.nextDouble() * MAX_RANDOM;
        double val2 = random.nextDouble() * MAX_RANDOM;

        String operator = getOperator(random.nextInt(4));
        return String.format("( %s %.2f %.2f )", operator, val1, val2);
    }

    private static String getOperator(int val) {
        switch(val) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/"; 
            default:
                return "error";
        }
    }
}
