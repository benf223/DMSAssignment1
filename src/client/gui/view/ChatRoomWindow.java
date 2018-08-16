package client.gui.view;

import client.gui.controller.ServerConnector;
import client.gui.model.MessageList;
import client.gui.model.UserList;
import client.gui.util.AppPanel;
import util.BroadcastMessage;
import util.TargetedMessage;

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
		super(serverConnector, new Dimension(600, 800));
		this.setLayout(new BorderLayout());
		initComponents();
		serverConnector.thread.started();
	}
	
	@Override
	protected void initComponents()
	{
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			this.serverConnector.thread.readUsers();
			this.serverConnector.thread.readMessages();
		}
		catch (Exception e)
		{
		
		}
		
		messageList = new JList<>(MessageList.instance().getMessages());
		JScrollPane scrollPane = new JScrollPane(messageList);
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.usersList = new JList<>(UserList.instance().getUsers());
		scrollPane = new JScrollPane(usersList);
		this.add(scrollPane, BorderLayout.EAST);
		
		JPanel tmpPanel = new JPanel(new BorderLayout());
		tmpPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.messageArea = new JTextArea("Message");
		this.messageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tmpPanel.add(this.messageArea, BorderLayout.CENTER);
		
		this.targetUser = new JTextArea("@All");
		this.targetUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tmpPanel.add(this.targetUser, BorderLayout.WEST);
		
		this.submitButton = new JButton("Send message");
		this.submitButton.addActionListener(e -> {
			try
			{
				sendMessage();
			}
			catch (Exception e1)
			{
			}
		});
		tmpPanel.add(this.submitButton, BorderLayout.EAST);
		
		this.add(tmpPanel, BorderLayout.SOUTH);
	}
	
	private void sendMessage() throws Exception
	{
		String target = this.targetUser.getText();
		
		if (UserList.instance().contains(target))
		{
			this.serverConnector.thread.postMessage(new TargetedMessage(this.serverConnector.getUser(), this.messageArea.getText(), target));
		}
		else
		{
			this.serverConnector.thread.postMessage(new BroadcastMessage(this.serverConnector.getUser(), this.messageArea.getText()));
		}
		
		messageList.setListData(MessageList.instance().getMessages());
	}
}
