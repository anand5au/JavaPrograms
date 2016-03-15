package socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter hostname and port:");
		String host = sc.nextLine();
		int port = Integer.parseInt(sc.nextLine());

		System.out.println("Creating connection to " + host + " at the port " + port);
		try
		{
			Socket client = new Socket(host, port);
			OutputStream out = client.getOutputStream();
			out.write("hello Server!!".getBytes());
			byte[] buf = new byte[100];
			InputStream in = client.getInputStream();
			in.read(buf);
			System.out.println("Server says: " + new String(buf));
			client.close();
			sc.close();
		}
		catch (Exception ex)
		{
		}
	}

}
