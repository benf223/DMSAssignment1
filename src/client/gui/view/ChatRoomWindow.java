package client.gui.view;

import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;

import javax.swing.*;
import java.awt.*;

public class ChatRoomWindow extends AppPanel
{
//	private Timer timer;
	private JList<String> messageList;
	
	public ChatRoomWindow(ServerConnector serverConnector)
	{
		super(serverConnector, new Dimension(500, 600));
		
		initComponents();
	}
	
	@Override
	protected void initComponents()
	{
		String[] data = new String[4];
		data[0] = "Kaylan: Hello";
		data[1] = "David: No";
		data[2] = "Alvin: Wut";
		data[3] = "Ben: ...";
		
		messageList = new JList<>(data);
		this.add(messageList);
		
		//		timer = new Timer(250, (event) -> System.out.println("test"));
		//		timer.start();
	}
}
