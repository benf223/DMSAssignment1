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
		super(serverConnector, new Dimension(1000, 1200));
		this.setLayout(new BorderLayout());
		
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
		
		String[] users = new String[4];
		users[0] = "Kaylan";
		users[1] = "David";
		users[2] = "Alvin";
		users[3] = "Ben";
		
		messageList = new JList<>(data);
		this.add(messageList, BorderLayout.CENTER);
		//serverConnector.getUsers()
		this.usersList = new JList<>(users);
		this.add(this.usersList, BorderLayout.EAST);
		
		JPanel tmpPanel = new JPanel(new BorderLayout());
		tmpPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.messageArea = new JTextArea("Message");
		this.messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tmpPanel.add(this.messageArea, BorderLayout.CENTER);
		
		this.targetUser = new JTextArea("@All");
		this.targetUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tmpPanel.add(this.targetUser, BorderLayout.WEST);
		
		this.submitButton = new JButton("Send message");
		tmpPanel.add(this.submitButton, BorderLayout.EAST);
		
		this.add(tmpPanel, BorderLayout.SOUTH);
		
		//timer = new Timer(250, (event) -> System.out.println("test"));
		//timer.start();
	}
}
