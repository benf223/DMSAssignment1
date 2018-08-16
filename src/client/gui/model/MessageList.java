package client.gui.model;

import util.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class MessageList
{
	private static MessageList instance;
	
	public static MessageList instance()
	{
		if (instance == null)
		{
			instance = new MessageList();
		}
		
		return instance;
	}
	
	private Stack<Message> messages;
	
	private MessageList()
	{
		messages = new Stack<>();
	}
	
	public void set(Message[] messages)
	{
		this.messages.clear();
		this.messages.addAll(Arrays.asList(messages));
	}
	
	public String[] getMessages()
	{
		ArrayList<String> tmp = new ArrayList<>();
		
		for (Message a : messages)
		{
			tmp.add(a.getMessage());
		}
		
		return tmp.toArray(new String[0]);
	}
}
