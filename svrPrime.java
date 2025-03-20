import java.io.*;
import java.net.*;

class svrPrime{
public static void main (String args[]){
	try{
	     ServerSocket ss = new ServerSocket(8001);
	     System.out.println("Server started..");
	     Socket s = ss.accept();
             DataInputStream in = new DataInputStream (s.getInputStream());

 	         int x = in.readInt();
             DataOutputStream otc = new DataOutputStream(s.getOutputStream());

             if(x==1 || x==2 || x==3){
                    otc.writeUTF(x + " is prime");
                    System.exit(0);
              }
              int count = 0;
              for(int i=1; i<=x; i++){
                  if(x%i == 0){
                       count++;
                      }
	       }
	       if (count == 2){
		     otc.writeUTF(x + " is prime");
		} else {
		     otc.writeUTF(x + " is not prime");
        }
    }
    catch(Exception e){
                     System.out.println(e.toString());
                 }
}
}

