// University of Adelaide - Distributed Systems 2024
// Fida Matin - a1798239
// 11 Aug 2024
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// Server class
public class CalculatorServer extends CalculatorImplementation {
    // default constructor
    public CalculatorServer() throws RemoteException {}

    // main server function
    public static void main(String args[]) {
        try {
            CalculatorServer stubInstance = new CalculatorServer();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(stubInstance, 0);
    
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();

            // check if registry already bound then rebind instead
            try{
                registry.bind("Calculator", stub);
            } catch (Exception e) {
                registry.rebind("Calculator", stub);
            }
            
            // print once server setup is successful
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}