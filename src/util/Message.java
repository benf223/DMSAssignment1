package util;

import java.io.Serializable;

public abstract class Message implements Serializable
{
	protected String sender;
	protected String message;
	
	public Message(String sender, String message)
	{
		this.sender = sender;
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public String getSender()
	{
		return this.sender;
	}
	
	public abstract String toString();
}
