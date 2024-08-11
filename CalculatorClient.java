// University of Adelaide - Distributed Systems 2024
// Fida Matin - a1798239
// 11 Aug 2024
import java.rmi.Naming;

// Test client testing operation of RMI calculator
public class CalculatorClient {
    // default constructor
    public CalculatorClient() {}

    // main client for integration testing through RMI
    public static void main(String[] args) {
        String host = (args.length < 1) ? "127.0.0.1" : args[0];

        try {
            Calculator test = (Calculator) Naming.lookup(String.format("//%s/Calculator", host));

            while (!test.isEmpty()) {
                test.pop();
            }

            System.out.println("TEST 1\nUsing pushValue(), pop() and isEmpty()\n");
            System.out.println("Pushing to stack:");
            test.pushValue(7);
            test.pushValue(12);
            test.pushValue(50);
            System.out.println("Values pushed");

            System.out.println("Checking if values push to stack");
            assert test.pop() == 50;
            assert test.pop() == 12;
            assert test.pop() == 7;
            System.out.println("Popped values matched with values pushed");

            System.out.println("Checking to see if stack is empty");
            assert test.isEmpty() == true;
            System.out.println("\nTEST 1 PASS\n");

            System.out.println("TEST 2\nUsing pushOperation()\n");

            System.out.println("Testing Min");
            test.pushValue(15);
            test.pushValue(41);
            test.pushOperation("min");
            assert test.pop() == 15;
            System.out.println("PASS");

            System.out.println("Testing Max");
            test.pushValue(31);
            test.pushValue(45);
            test.pushOperation("max");
            assert test.pop() == 45;
            System.out.println("PASS");

            System.out.println("Testing LCM");
            test.pushValue(6);
            test.pushValue(4);
            test.pushValue(18);
            test.pushOperation("lcm");
            assert test.pop() == 36;
            System.out.println("PASS");

            System.out.println("Testing GCD");
            test.pushValue(6);
            test.pushValue(4);
            test.pushValue(18);
            test.pushOperation("gcd");
            assert test.pop() == 2;
            System.out.println("PASS");
            System.out.println("\nTEST 2 PASS\n");

            System.out.println("TEST 3\nUsing delayPop()\n");
            int val = 50;
            test.pushValue(val);
            int delay = 2000; // 2 second delay
            double comp_error = delay * 0.05; // 5% error
            double start = System.currentTimeMillis();
            System.out.println("Running Delay for 2 seconds");
            int res = test.delayPop(delay) ;
            double diff = System.currentTimeMillis() - start;
            assert res == val;
            assert (Math.abs(diff - delay)) <= comp_error;
            System.out.println("\nTEST 3 PASS\n");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }
}