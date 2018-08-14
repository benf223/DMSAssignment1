package client.gui.view;

import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;

import javax.swing.*;
import java.awt.*;

public class ChatRoomWindow extends AppPanel
{
	private JList<String> messageList;
	private JList<String> usersList;
	private JTextArea messageArea;
	private JTextArea targetUser;
	private JButton submitButton;
	
	public ChatRoomWindow(ServerConnector serverConnector)
	{
		super(serverConnector, new Dimension(500, 600));
		this.setLayout(new GridLayout(3, 2, 20, 20));
		
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
		
		this.usersList = new JList<>(serverConnector.getUsers());
		this.add(this.usersList);
		
		this.messageArea = new JTextArea("Message");
		this.add(this.messageArea);
		
		this.targetUser = new JTextArea("@All");
		this.add(this.targetUser);
		
		this.add(new JPanel());
		
		this.submitButton = new JButton("Send message");
		this.add(this.submitButton);
		
		//		timer = new Timer(250, (event) -> System.out.println("test"));
		//		timer.start();
	}
}
