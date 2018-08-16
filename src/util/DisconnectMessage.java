package util;

public class DisconnectMessage extends Message
{
	public DisconnectMessage(String sender)
	{
		super(sender, null);
	}
	
	@Override
	public String toString()
	{
		return "Disconnect~" + this.sender;
	}
}
