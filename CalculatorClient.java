// University of Adelaide - Distributed Systems 2024
// Fida Matin - a1798239
// 11 Aug 2024
import java.rmi.Naming;

// Test client testing operation of RMI calculator
public class CalculatorClient {
    // default constructor
    public CalculatorClient() {}

    // main client testing function
    public static void main(String[] args) {
        String host = (args.length < 1) ? "127.0.0.1" : args[0];

        try {
            Calculator test = (Calculator) Naming.lookup(String.format("//%s/Calculator", host));

            while (!test.isEmpty()) {
                test.pop();
            }
                System.out.println("Using pushValue() and pop()");
                System.out.println("Pushing to stack:");
                test.pushValue(7);
                test.pushValue(12);
                test.pushValue(50);

                System.out.println("Checking if values push to stack");
                assert test.pop() == 50;
                assert test.pop() == 12;
                assert test.pop() == 7;
                System.out.println("\nPASS\n");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }
}