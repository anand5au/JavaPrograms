package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server implements Runnable
{
	DatagramSocket socket;

	public Server(int port) throws SocketException
	{
		socket = new DatagramSocket(port);
	}

	public void run()
	{
		try
		{
			byte[] buf = new byte[1000];
			DatagramPacket p = new DatagramPacket(buf, buf.length);
			socket.receive(p);
			System.out.println("Message - " + new String(buf));
			buf = "Hello Client!".getBytes();
			p = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
			socket.send(p);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			socket.close();
		}
	}

	public static void main(String[] args) throws SocketException
	{
		new Thread(new Server(5555)).start();
	}
}
