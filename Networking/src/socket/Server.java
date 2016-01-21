package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter port:");
		int port = Integer.parseInt(sc.nextLine());
		
		try
		{
			ServerSocket server = new ServerSocket(port,5);
			System.out.println("Waiting for a connection at the port " + port);
			Socket socket =  server.accept();
			
			byte[] buf = new byte[100];
			InputStream in = socket.getInputStream();
			in.read(buf);
			System.out.println("Client says: " + new String(buf));
			OutputStream out = socket.getOutputStream();
			out.write("hello Client!!".getBytes());
			server.close();
			sc.close();
		}
		catch (Exception ex) { }
	}
}
