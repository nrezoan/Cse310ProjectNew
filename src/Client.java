
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;


public class Client {

    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public static void main(String args[]) {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            Socket client = new Socket(serverAddress, serverPort);
            Scanner input = new Scanner(System.in);
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            new ReadThread(ois);
            while (true) {
                //String s=br.readLine();
                String s = input.nextLine();
                oos.writeObject(s);
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}


 class ReadThread implements Runnable
{
 private Thread thr;
 ObjectInputStream ois;
 

 public ReadThread(ObjectInputStream ois) 
 {
  this.ois=ois;
  this.thr = new Thread(this);
  thr.start();
 }
 
 public void run() 
 {
  try
  {
   while(true)
   {
    String t=(String)ois.readObject();
    if(t != null){
    	if(t.equals("From Server: nazib")){
        	System.out.println("it is working");
        }
    	else if(t.equals("From Server: diba")){
    		System.out.println("it is working diba");
    	}
    }
    
    
   }
  }catch(Exception e)
  {
   System.out.println (e);                        
  }   
                
  
 }
}



