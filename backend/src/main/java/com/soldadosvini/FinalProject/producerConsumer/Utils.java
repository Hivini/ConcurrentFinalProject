package com.soldadosvini.FinalProject.producerConsumer;

import kawa.standard.Scheme;

import java.util.Random;

public class Utils {

    // Uses Kawa to evaluate a Scheme function
    public static String resolveTask(String task) {
        Scheme scheme = new Scheme();
        try{
            Object result = scheme.eval(task);
            return String.format("%.2f", result);
        }catch (Throwable throwable) {
            System.out.println(throwable);
        }
        return "";
    }

    /**
     * Generates a new task that the @{Producer} can use.
     * 
     * The task is an Scheme operation as a String. The following example is the sum
     * of 1 + 2: - "( + 1 2 )" Both values and operation are randomly generated.
     * 
     * @return The Scheme operation as a String.
     */
    public static String generateTask(int min, int max, Character operator) {
        Random random = new Random();

        int val1 = random.ints(min, max + 1).findFirst().getAsInt();
        int val2 = random.ints(min, max + 1).findFirst().getAsInt();

        // Set a value of the operation as an int between min and max.
        // String operator = getOperator(random.nextInt(4));
        return String.format("( %s %d %d )", operator, val1, val2);
    }

}
