package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Convert to a GUI to better handle hangs
public class Server
{
	public static final int PORT = 46587;
	public static ArrayList<ConnectionThread> connectionThreads;
	public static final MessageStore messageStore = MessageStore.instance();
	
	// Entry point for the server
	public static void main(String[] args)
	{
		connectionThreads = new ArrayList<>();
		
		ServerSocket serverSocket = startServer();
		
		try
		{
			if (serverSocket != null)
			{
				while (true)
				{
					startNewThread(serverSocket.accept());
				}
			}
			else
			{
				throw new Exception("Server never started");
			}
		}
		catch (Exception e)
		{
			System.err.println("Client failed to connect");
			e.printStackTrace();
		}
	}
	
	private static ServerSocket startServer()
	{
		try
		{
			ServerSocket tmp = new ServerSocket(PORT);
			System.out.println("Server started at " + InetAddress.getLocalHost() + " on port " + PORT);
			
			return tmp;
		}
		catch (IOException e)
		{
			System.err.println("Error starting up server");
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}
	
	private static void startNewThread(Socket socket)
	{
		ConnectionThread connectionThread = new ConnectionThread(socket);
		connectionThreads.add(connectionThread);
		messageStore.addObserver(connectionThread);
		
		Thread thread = new Thread(connectionThread);
		thread.start();
	}
}
