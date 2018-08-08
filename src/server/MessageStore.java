package server;

import java.util.Observable;

// This needs to be thread safe
// Going to use a Stack to represent the message tree
public class MessageStore extends Observable
{
	private static MessageStore instance;
	
	public static MessageStore instance()
	{
		if (instance == null)
		{
			instance = new MessageStore();
		}
		
		return instance;
	}
	
	private MessageStore()
	{
	
	}
	
	public synchronized void push(Object message)
	{
	
	}
	
	// Unused
	public synchronized Object getMessagesForClient()
	{
		return null;
	}
}
