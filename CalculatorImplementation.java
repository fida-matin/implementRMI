// University of Adelaide - Distributed Systems 2024
// Fida Matin - a1798239
// 11 Aug 2024
import java.util.ArrayList;

// Implementation class for the remote operations
public class CalculatorImplementation implements Calculator {
    // Stack for the server
    private ArrayList<Integer> calc_stack = new ArrayList<Integer>();

    // default constructor
    public CalculatorImplementation() {}

    // GCD (Greatest Common Divisor) can be calculated recursively by pasing the modulus of the two numbers each time
    private static int recursiveGCD(int val1, int val2) {
        // base case - if val2 is 0 it means that the previous val1 is divisble by val2
        if (val2 == 0) {
            return val1;
        }
        // recursively iterate until a divisible combination is found
        return recursiveGCD(val2, val1 % val2);
    }


    // INTERFACE OPERATIONS

    // Add values to top of stack
    public void pushValue(int val) {
        calc_stack.add(0, val);
    }

    // Removes value from top of stack and returns the value
    public int pop() {
        int top = calc_stack.get(0);
        calc_stack.remove(0);
        return top;
    }

    // get milliseconds as input and return top of stack after delay
    public int delayPop(int millis) {
        long start = System.currentTimeMillis();
        // pause operations until time is complete
        while (millis != System.currentTimeMillis() - start) {
        }

        // run same operation as pop() 
        int top = calc_stack.get(0);
        calc_stack.remove(0);
        return top;
    }


    // Return true if stack is empty
    public boolean isEmpty() {
        return calc_stack.isEmpty();
    }

    // pop all values from stack and run desired operation and push to stack
    public void pushOperation(String operator) {
        int calc = 0;
        calc = calc_stack.get(0);

        for (int i = 0; i < calc_stack.size(); i++) {

            // swtich case for running through all operations
            switch (operator) {
                case "min":
                    calc = (calc_stack.get(i) < calc) ? calc_stack.get(i) : calc;
                    break;
                case "max":
                    calc = (calc_stack.get(i) > calc) ? calc_stack.get(i) : calc;
                    break;
                case "lcm":
                    calc = (calc * calc_stack.get(i)) / recursiveGCD(calc, calc_stack.get(i));
                    break;
                case "gcd":
                    calc = recursiveGCD(calc, calc_stack.get(i));
                    break;           
            }           
        }

        // clear stack and replace with the result
        calc_stack.clear();
        calc_stack.add(0, calc);
    }


}
