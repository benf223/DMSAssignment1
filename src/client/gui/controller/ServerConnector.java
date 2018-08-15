package client.gui.controller;

import client.gui.model.MessageList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnector
{
	public static final String HOSTNAME = "localhost";
	public static final int PORT = 46587;
	
	private static ServerConnector instance;
	
	public static ServerConnector instance()
	{
		if (instance == null)
		{
			instance = new ServerConnector();
		}
		
		return instance;
	}
	
	private ServerConnectorThread thread;
	
	private Socket socket;
	private PrintWriter out;
	private Scanner in;
	private MessageList messageList;
	
	private ServerConnector()
	{
		this.thread = new ServerConnectorThread();
		new Thread(thread).start();
		messageList = MessageList.instance();
	}
	
	public void connectToServer()
	{
		if (socket != null) {
			return;
		}
		
		try
		{
			socket = new Socket(HOSTNAME, PORT);
		}
		catch (IOException e)
		{
			System.err.println("Client could not make connection: ");
			e.printStackTrace();
			
			System.exit(-1);
		}
		
		try
		{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new Scanner(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		out.println("test user name");
	}
	
	// Sends a Message to the server and processes the response
	public Object post(String request) throws Exception
	{
		if (socket == null)
		{
			throw new Exception("Server not connected");
		}
		
		out.println(request);
		// What is this
		return in.nextLine();
	}
	
	// Returns the object containing all messages from the server
	// Run on a timer
	public void get(String request) throws Exception
	{
		
		if (socket == null)
		{
			throw new Exception("Server not connected");
		}
		
		out.println(request);
		
		// What is this
		// Need to get a response
		
		while (!in.hasNext())
		{
			Thread.sleep(200);
		}
		
		messageList.set(in.nextLine());
	}
	
	public void close()
	{
		// Send a disconnect message
		// Shutdown connections ...
	}
	
	public String[] getUsers()
	{
		return new String[0];
	}
	
	// This thread should stop the GUI from hanging and process all the requests
	private class ServerConnectorThread implements Runnable
	{
		private ServerConnector connector;
		
		public ServerConnectorThread()
		{
			connector = ServerConnector.instance();
		}
		
		@Override
		public void run()
		{
			connector.connectToServer();
			
			while (true) {
				connector.push();
				connector.pull();
			}
		}
		
		public void postMessage(String message) {
		
		}
		
		public void readMessages() {
		
		}
		
		public void readUsers() {
		
		}
		
		public boolean addUser(String user) {
			return false;
		}
		
		public void disconnect() {
		
		}
	}
	
	private void pull()
	{
	
	}
	
	private void push()
	{
	
	}
}
