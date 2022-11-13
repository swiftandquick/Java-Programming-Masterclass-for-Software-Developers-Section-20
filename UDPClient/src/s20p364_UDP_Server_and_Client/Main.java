package s20p364_UDP_Server_and_Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            /* Get's the local host's IP address.  */
            InetAddress address = InetAddress.getLocalHost();
            /* Constructs a datagram socket and binds it to any available port on the local host machine.  */
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);;
            String echoString;

            do {
                System.out.println("Enter String to be echoed:  ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();

                /* Constructs a datagram packet for sending packets of length of buffer.length to the port 5000 number
                on the local host (address). */
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                byte[] buffer2 = new byte[50];
                /* Constructs a DatagramPacket for receiving packets of 50.  */
                packet = new DatagramPacket(buffer2, buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Text received is:  " + new String(buffer2, 0, packet.getLength()));

            } while(!echoString.equals("exit")); // Type "exit" to exit the while loop.
        }
        catch(SocketTimeoutException e) {
            System.out.println("The socket timed out.  ");
        }
        catch(IOException e) {
            System.out.println("Client error:  " + e.getMessage());
        }
    }

}
