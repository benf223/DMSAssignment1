package server;

import util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ConnectionThread implements Runnable, Observer
{
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private boolean stopRequested;
	private String name;
	
	public ConnectionThread(Socket socket)
	{
		System.out.println("Connection made with " + socket.getInetAddress());
		this.socket = socket;
		
		try
		{
			in = new ObjectInputStream(this.socket.getInputStream());
			out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while (!stopRequested)
		{
			try
			{
				processGet((Message) in.readObject());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		MessageStore.instance().deleteObserver(this);
		
		try
		{
			in.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Connection closed with " + socket.getInetAddress());
		Server.connectionThreads.remove(this);
		
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// Switch for processing different requests;
	private void processGet(Message request) throws Exception
	{
		if (request instanceof AddUserMessage)
		{
			try
			{
				this.name = request.getSender();
				
				ArrayList<String> users = new ArrayList<>();
				for (ConnectionThread a : Server.connectionThreads)
				{
					users.add(a.name);
				}
				System.out.println(users);
				
				if (users.contains(this.name))
				{
					out.writeObject(new ResultMessage("Server", "Accept"));
				}
				else
				{
					out.writeObject(new ResultMessage("Server", "Reject"));
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else if (request instanceof BroadcastMessage || request instanceof TargetedMessage)
		{
			MessageStore.instance().push(request);
			Thread.yield();
		}
		else if (request instanceof DisconnectMessage)
		{
			stopRequested = true;
		}
		else if (request instanceof RequestMessage)
		{
			if (request.getMessage().equalsIgnoreCase("GetUsers"))
			{
				ResultMessage result = new ResultMessage("Server", "Users");
				
				ArrayList<String> users = new ArrayList<>();
				for (ConnectionThread a : Server.connectionThreads)
				{
					users.add(a.name);
				}
				
				result.setUsers(users.toArray(new String[0]));
				out.writeObject(result);
			}
			else if (request.getMessage().equalsIgnoreCase("GetMessages"))
			{
				out.writeObject(MessageStore.instance().getDataForClient(name));
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if (arg != null)
		{
			if (arg instanceof TargetedMessage)
			{
				if (!this.name.equalsIgnoreCase(((TargetedMessage) arg).getReceiver()))
				{
					return;
				}
			}
		}
		
		try
		{
			out.writeObject(MessageStore.instance().getDataForClient(this.name));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
