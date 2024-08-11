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

    // Return true if stack is empty
    public boolean isEmpty() {
        return calc_stack.isEmpty();
    }


}
