// Fida Matin - a1798239
// University of Adelaide
// 11 Aug 2024
import java.rmi.Remote;
import java.rmi.RemoteException;


// Interface that defines the remote operations implemented by the remote service
public interface Calculator extends Remote {
    // Takes val and push to top of stack.
    void pushValue(int val) throws RemoteException;

    // Push string containing operator
    void pushOperation(String operator) throws RemoteException;

    // Pop top of stack and return it to client
    int pop() throws RemoteException;

    // Wait millis milliseconds before carrying out pop
    int delayPop(int millis) throws RemoteException;

    // Return true if stack empty
    boolean isEmpty() throws RemoteException;
}