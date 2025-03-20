import java.net.*;
import java.io.*;
import java.util.*;
public class svrBroadcast {
  public static final int PORT = 1234;
  public static void main(String args[]) throws Exception {
    //Creating a Multicast Socket (one sender multiple receivers)
    MulticastSocket socket;
    DatagramPacket packet;
    InetAddress address;
    address = InetAddress.getByName("239.1.2.3");
    socket = new MulticastSocket();
    socket.joinGroup(address);
    byte[] data = null;
    for (;;) {
      Thread.sleep(10000);
      System.out.println("Sending ");
      String str = ("This is Lufffy Calling....");
      data = str.getBytes();
      packet = new DatagramPacket(data, str.length(), address, PORT);
      socket.send(packet);
    }
  }
}
