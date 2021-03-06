package client.gui.controller;

import client.Client;
import client.gui.model.MessageList;
import client.gui.model.UserList;
import util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

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
	
	public final ServerConnectorThread thread;
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private MessageList messageList;
	private UserList userList;
	private String user;
	
	private ServerConnector()
	{
		messageList = MessageList.instance();
		userList = UserList.instance();
		this.thread = new ServerConnectorThread();
	}
	
	public void connectToServer(String url)
	{
		if (socket != null)
		{
			return;
		}
		
		try
		{
			socket = new Socket(url == null ? HOSTNAME : url, PORT);
		}
		catch (IOException e)
		{
			System.err.println("Client could not make connection: ");
			
			System.exit(-1);
		}
		
		try
		{
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		new Thread(thread).start();
	}
	
	private ResultMessage post(Message request) throws Exception
	{
		if (socket == null)
		{
			throw new Exception("Server not connected");
		}
		
		out.writeObject(request);
		
		return (ResultMessage) in.readObject();
	}
	
	private ResultMessage get(RequestMessage request) throws Exception
	{
		if (socket == null)
		{
			throw new Exception("Server not connected");
		}
		
		out.writeObject(request);
		
		ResultMessage msg = (ResultMessage) in.readObject();
		return msg;
	}
	
	private void close() throws Exception
	{
		if (socket != null)
		{
			post(new DisconnectMessage(user));
		}
	}
	
	private ArrayList<String> getUsers() throws Exception
	{
		ResultMessage msg = this.get(new RequestMessage(user, "GetUsers"));
		
		ArrayList<String> tmp = new ArrayList<>(Arrays.asList(msg.getUsers()));
		
		return tmp;
	}
	
	public String getUser()
	{
		return this.user;
	}
	
	public class ServerConnectorThread implements Runnable
	{
		private ServerConnector connector;
		private boolean started;
		private boolean active;
		
		public ServerConnectorThread()
		{
			started = false;
		}
		
		@Override
		public void run()
		{
			connector = ServerConnector.instance();
			
			while (!started)
			{
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			
			while (true)
			{
				try
				{
					if (!active)
					{
						connector.pull();
					}
					
				}
				catch (Exception e)
				{
					System.exit(-1);
				}
				
				try
				{
					Thread.sleep(250);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
		
		public void started()
		{
			started = true;
		}
		
		public void toggleActive()
		{
			active = !active;
		}
		
		public void postMessage(Message message) throws Exception
		{
			if (connector.post(message).getMessage().equalsIgnoreCase("Saved"))
			{
				Client.update();
			}
		}
		
		public void readUsers() throws Exception
		{
			ArrayList<String> users = connector.getUsers();
			
			connector.userList.set(users);
		}
		
		public void readMessages() throws Exception
		{
			ResultMessage messages = connector.get(new RequestMessage(user, "GetMessages"));
			connector.messageList.set(messages.getMessages());
		}
		
		public boolean addUser(String user) throws Exception
		{
			connector.user = user;
			
			ArrayList<String> users = connector.getUsers();
			
			if (users.get(0) != null)
			{
				if (users.contains(user))
				{
					return false;
				}
			}
			
			AddUserMessage msg = new AddUserMessage(user, "Join");
			
			ResultMessage result = connector.post(msg);
			
			if (!result.getMessage().equalsIgnoreCase("Accept"))
			{
				return false;
			}
			
			return true;
		}
		
		public void disconnect() throws Exception
		{
			connector.close();
		}
	}
	
	private void pull() throws Exception
	{
		ResultMessage msg = this.get(new RequestMessage(user, "GetMessages"));
		
		this.messageList.set(msg.getMessages());
		
		this.userList.set(getUsers());
		
		Client.update();
	}
}