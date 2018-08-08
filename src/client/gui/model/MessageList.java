package client.gui.model;

public class MessageList
{
	private static MessageList instance;
	
	public static MessageList instance() {
		if (instance == null) {
			instance = new MessageList();
		}
		
		return instance;
	}
	
	private MessageList() {
	
	}
	
	public void set(String messages)
	{
		System.out.println(messages);
	}
	
	// Data for the JPanels
	public Object getMessages() {
		return null;
	}
}
