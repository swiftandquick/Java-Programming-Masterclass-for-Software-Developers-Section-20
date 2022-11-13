package s20p364_UDP_Server_and_Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
        try {
            /* DiagramSocket:  Represents a socket for sending and receiving datagram packets.
            * Set the socket's port to 5000.  */
            DatagramSocket socket = new DatagramSocket(5000);
            while(true) {
                byte[] buffer = new byte[50];
                /* Constructs a DatagramPacket for receiving packets of 50 (buffer.length = 50).  */
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                /* Receives a datagram packet from this socket.  */
                socket.receive(packet);
                /* packet.length():  Returns the length of the data to be sent or the length of the data received.
                * This way, the null characters are deleted in the new String.  */
                System.out.println("Text received is:  " + new String(buffer, 0, packet.getLength()));

                String returnString = "echo:  " + new String(buffer, 0, packet.getLength());

                /* Get the bytes of returnString.  */
                byte[] buffer2 = returnString.getBytes();
                /* packet.getAddress():  Get the IP address of the packet.  */
                InetAddress address = packet.getAddress();
                /* packet.getPort():  Get the port of the packet.  */
                int port = packet.getPort();

                /* Once the server receives a packet, it uses the IP address and port in the received packet to create
                 * a new DatagramPacket.  */
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);

                /* Sends the packet back to the client.  */
                socket.send(packet);
            }
        }
        /* SocketException:  Thrown to indicate that there is an error creating or accessing a Socket.  */
        catch(SocketException e) {
            System.out.println("SocketExpcetion:  " + e.getMessage());
        }
        catch(IOException e) {
            System.out.println("IOException:  " + e.getMessage());
        }
    }

}
