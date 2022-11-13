package s20p361_First_Client_and_Server_Apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Internet address is localhost, port number is 5000.  */
        try(Socket socket = new Socket("localhost", 5000)) {

            /* If no data arrives in 5 seconds, SocketTimeoutException will be thrown.  */
            socket.setSoTimeout(5000);

            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed: ");
                /* Next line of input is echoString.  */
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);
                /* If next input (line) is not "exit", echoes (BufferedReader) takes the next input.  */
                if(!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while(!echoString.equals("exit"));

        }
        catch(SocketTimeoutException e) {
            System.out.println("The socket timed out.  ");
        }
        catch(IOException e) {
            System.out.println("Client error:  " + e.getMessage());
        }
    }

}
