import java.io.*;
import java.net.*;
public class Server1 extends Thread
{
    private Socket socket;
    public Server1(Socket s)
    {
        this.socket = s;
    }
    public void run()
    {
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String str = in.readLine();
            while (str != null)
            {
                System.out.println("Received: " + str);
                System.out.println("Server: " + str);
            }
        }
        catch (IOException e)
        {
           System.out.println("Technical Error");
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                System.out.println("Error For Closing Connection");
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server is Established, Waiting for Clients");
        while (true)
        {
            Socket socket = ss.accept();
            System.out.println("Client connected");
            Server1 server = new Server1(socket);
            server.start();
        }
    }
}