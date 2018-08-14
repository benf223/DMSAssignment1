package server;

import util.BroadcastMessage;
import util.Message;
import util.TargetedMessage;

import java.util.Observable;
import java.util.Stack;

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
	
	private Stack<Message> messageStack;
	
	private MessageStore()
	{
		messageStack = new Stack<>();
	}
	
	public synchronized void push(Message message)
	{
		this.messageStack.push(message);
		
		if (message instanceof BroadcastMessage) {
			this.notifyObservers();
		} else if (message instanceof TargetedMessage) {
			this.notifyObservers(((TargetedMessage) message).getReceiver());
		}
		
		this.notifyObservers();
	}
	
	public String getDataForClient()
	{
		return "";
	}
}
