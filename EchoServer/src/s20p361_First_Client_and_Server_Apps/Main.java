package s20p361_First_Client_and_Server_Apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        /* Assign port number as 5000.  */
        try(ServerSocket serverSocket = new ServerSocket(5000)) {
            while(true) {
                /* Echoer is the subclass of Thread, call the start() method will invoke the run() method in the
                * Echoever class.  Pass in serverSocket.accept() (ServerSocket object) as argument.  */
                new Echoer(serverSocket.accept()).start();
            }
        }
        catch(IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }

}
