// University of Adelaide - Distributed Systems 2024
// Fida Matin - a1798239
// 11 Aug 2024
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

// Test client testing operation of RMI calculator
public class CalculatorClient {
    // default constructor
    public CalculatorClient() {}

    // main client for automated integration testing through RMI
    public static void main(String[] args) {
        String host = (args.length < 1) ? "127.0.0.1" : args[0];

        try {
            /**
             * Single Client Testing
             */            
            Registry registry = LocateRegistry.getRegistry(host);
            Calculator test = (Calculator) registry.lookup("Calculator");

            while (!test.isEmpty()) {
                test.pop();
            }

            System.out.println("TEST 1\nTest pushValue(), pop() and isEmpty()\n");
            System.out.println("Pushing to stack: 7, 12, 50");
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

            System.out.println("TEST 2\nTest pushOperation()\n");

            System.out.println("Testing Min - 15, 41");
            test.pushValue(15);
            test.pushValue(41);
            test.pushOperation("min");
            assert test.pop() == 15;
            System.out.println("PASS");

            System.out.println("Testing Max - 31, 45");
            test.pushValue(31);
            test.pushValue(45);
            test.pushOperation("max");
            assert test.pop() == 45;
            System.out.println("PASS");

            System.out.println("Testing LCM - 6, 4, 18");
            test.pushValue(6);
            test.pushValue(4);
            test.pushValue(18);
            test.pushOperation("lcm");
            assert test.pop() == 36;
            System.out.println("PASS");

            System.out.println("Testing GCD - 6, 4, 18");
            test.pushValue(6);
            test.pushValue(4);
            test.pushValue(18);
            test.pushOperation("gcd");
            assert test.pop() == 2;
            System.out.println("PASS");
            System.out.println("\nTEST 2 PASS\n");

            System.out.println("TEST 3\nTest delayPop()\n");
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

            /*
             * Multiple Client Testing 
             */

            System.out.println("TEST 4\nMake 4 clients\n");
            Calculator c1 = (Calculator) registry.lookup("Calculator");
            Calculator c2 = (Calculator) registry.lookup("Calculator");
            Calculator c3 = (Calculator) registry.lookup("Calculator");
            Calculator c4 = (Calculator) registry.lookup("Calculator");
            System.out.println("TEST 4 PASS\n");

            System.out.println("TEST 5\nTest all clients use same stack\n");
            System.out.println("Add values using different clients");
            c1.pushValue(9);
            c2.pushValue(8);
            c3.pushValue(7);
            c4.pushValue(6);

            System.out.println("pop values using alternate clients");
            assert c1.pop() == 6;
            assert c2.pop() == 7;
            assert c3.pop() == 8;
            assert c4.pop() == 9;
            System.out.println("TEST 5 PASS\n");

            System.out.println("TEST 6\n Extra tests all clients use same stack\n");
            System.out.println("delayPop()");
            c4.pushValue(3);
            start = System.currentTimeMillis();
            System.out.println("Running Delay for 2 seconds");
            res = c1.delayPop(delay) ;
            diff = System.currentTimeMillis() - start;
            assert res == 3;
            assert (Math.abs(diff - delay)) <= comp_error;
            System.out.println("PASS\n");

            System.out.println("pushOperation() - lcm");
            c1.pushValue(6);
            c2.pushValue(4);
            c3.pushValue(18);
            c4.pushValue(36);
            test.pushOperation("lcm");
            assert test.pop() == 36;
            System.out.println("PASS");

            System.out.println("\nTEST 6 PASS\n");

            System.out.println("All automated testing passed!\n\n");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }
}