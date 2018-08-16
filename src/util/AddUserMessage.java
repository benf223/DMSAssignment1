package util;

public class AddUserMessage extends Message
{
	
	public AddUserMessage(String sender, String message)
	{
		super(sender, message);
	}
	
	@Override
	public String toString()
	{
		return "AddUser~" + this.sender + "~" + this.message;
	}
}
