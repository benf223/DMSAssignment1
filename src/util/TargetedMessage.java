package util;

public class TargetedMessage extends Message
{
	private String receiver;
	
	public TargetedMessage(String sender, String message, String receiver)
	{
		super(sender, message);
		this.receiver = receiver;
	}
	
	public String getReceiver() {
		return this.receiver;
	}
	
	@Override
	public String toString()
	{
		return null;
	}
}
