package util;

public class ResultMessage extends Message
{
	private Message[] messages;
	private String[] users;
	
	public ResultMessage(String sender, String message)
	{
		super(sender, message);
	}
	
	public void setMessages(Message[] messages)
	{
		this.messages = messages;
	}
	
	@Override
	public String toString()
	{
		return "Result~" + sender + "~" + message;
	}
	
	public Message[] getMessages()
	{
		return messages;
	}
	
	public void setUsers(String[] users)
	{
		this.users = users;
	}
	
	public String[] getUsers()
	{
		return users;
	}
}
