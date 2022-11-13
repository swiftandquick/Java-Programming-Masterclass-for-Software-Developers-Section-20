package s20p361_First_Client_and_Server_Apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/* Each client thread is responsible for creating the input / output stream, listening for requests
 * on the client, and then responding to those events.  */

public class Echoer  extends Thread {

    private Socket socket;


    public Echoer(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            /* Returns input stream and output stream for this socket.  */
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                String echoString = input.readLine();
                System.out.println("Received client input:  " + echoString);
                if(echoString.equals("exit")) {
                    break;
                }
                try {
                    // Thread is sleep for 15 seconds.
                    Thread.sleep(15000);
                }
                catch(InterruptedException e) {
                    System.out.println("Thread interrupted.  ");
                }
                output.println();
            }
        }
        catch(IOException e) {
            System.out.println("Oops:  " + e.getMessage());
        }
        finally {
            try {
                socket.close();
            }
            catch(IOException e) {
                // Oh, well!
            }
        }
    }

}
