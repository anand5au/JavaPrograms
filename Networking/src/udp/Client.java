package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket socket = new DatagramSocket();
			byte[] buf = new byte[1000];
			buf = ("Hello from Client ").getBytes();
			DatagramPacket p = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 5555);
			socket.send(p);
			Arrays.fill(buf, (byte) 0);
			p = new DatagramPacket(buf, buf.length);
			socket.receive(p);
			System.out.println("Server says: " + new String(p.getData()));
			socket.close();
		}
		catch (Exception e)
		{
		}
	}
}
