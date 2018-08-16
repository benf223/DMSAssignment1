package util;

public class RequestMessage extends Message
{
	
	public RequestMessage(String sender, String message)
	{
		super(sender, message);
	}
	
	@Override
	public String toString()
	{
		return "Request~" + sender + "~" + message;
	}
}
