import java.net.*;
import java.io.*;
public class clntBroadcast {
  public static final int PORT = 1234;
  public static void main(String args[]) throws Exception {
    MulticastSocket socket;
    DatagramPacket packet;
    InetAddress address;
    address = InetAddress.getByName("239.1.2.3");
    socket = new MulticastSocket(PORT);
    socket.joinGroup(address);
    byte[] data = new byte[100];
    packet = new DatagramPacket(data, data.length);
    for (;;) {
      socket.receive(packet);
      String str = new String(packet.getData());
      System.out.println("Message received from " + packet.getAddress() + "Message is: " + str);
    }
  }
}
