package server;

import util.BroadcastMessage;
import util.Message;
import util.ResultMessage;
import util.TargetedMessage;

import java.util.ArrayList;
import java.util.Stack;

public class MessageStore
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
	
	private Stack<Message> messageStack;
	
	private MessageStore()
	{
		messageStack = new Stack<>();
		messageStack.push(new BroadcastMessage("Server", "Welcome to the chatroom"));
	}
	
	public synchronized void push(Message message)
	{
		this.messageStack.push(message);
	}
	
	public ResultMessage getDataForClient(String user)
	{
		ArrayList<Message> messages = new ArrayList<>();
		
		for (Message a : messageStack)
		{
			if (a instanceof BroadcastMessage)
			{
				messages.add(a);
			}
			else
			{
				if (a.getSender().equalsIgnoreCase(user) || ((TargetedMessage) a).getReceiver().equalsIgnoreCase(user))
				{
					messages.add(a);
				}
			}
		}
		ResultMessage result = new ResultMessage("Server", "Messages");
		result.setMessages(messages.toArray(new Message[0]));
		
		return result;
	}
}
