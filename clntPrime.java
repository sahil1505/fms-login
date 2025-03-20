import java.io.*;
import java.net.*;

class clntPrime{
public static void main (String args[]){
	try{
	     Socket cs = new Socket("Localhost", 8001);
	     BufferedReader infu = new BufferedReader (new InputStreamReader(System.in));


 	      System.out.print("Enter your number: ");
	      int a = Integer.parseInt(infu.readLine());
	      DataOutputStream out = new DataOutputStream (cs.getOutputStream());
	      out.writeInt(a);
	      DataInputStream in = new DataInputStream(cs.getInputStream());

		System.out.println(in.readUTF());
		cs.close();
}

catch(Exception e){
	System.out.println(e.toString());
}
}
}