package com.soldadosvini.FinalProject.producerConsumer;

import java.util.Random;

public class Utils {

    /* TODO(hivini): Call Scheme program instead. */
    public static String resolveTask(String task) {
        // Get all elements that are separated by a whitespace.
        String[] elements = task.split("\\s+");

        char operation = elements[1].charAt(0);
        int current = 3;
        float result = Float.parseFloat(elements[2]);
        while (current < elements.length) {
            if (elements[current].equals(")"))
                break;
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
