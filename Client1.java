import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client1 {
    public static void main(String args[]) {
        boolean flag = true;

        try (Socket clientS = new Socket("localhost", 2000);
             DataInputStream dis = new DataInputStream(clientS.getInputStream());
             DataOutputStream dos = new DataOutputStream(clientS.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to Server");

            while (flag) {
                System.out.println("Write data you want to send to Server:");
                String data = sc.nextLine();
                dos.writeUTF(data.concat(" "));
                dos.flush();
                
                System.out.println("Want to send more data? \nWrite Yes otherwise No to disconnect Server:");
                String confirm = sc.nextLine(); 
                if (!confirm.equalsIgnoreCase("yes")) {
                    flag = false;
                 
                }
               
            }
          
            clientS.close();
        } catch (UnknownHostException e) {
            System.err.println("Unknown host exception: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO exception: " + e.getMessage());
        } 
    }
}
