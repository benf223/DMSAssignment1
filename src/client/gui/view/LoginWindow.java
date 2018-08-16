package client.gui.view;

import client.Client;
import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends AppPanel
{
	private JLabel test;
	private JLabel uNameLabel;
	private JTextField uName;
	private JLabel serverAddressLabel;
	private JTextField serverAddress;
	private JButton submitButton;
	
	public LoginWindow(ServerConnector serverConnector)
	{
		super(serverConnector, new Dimension(500, 300));
		this.setLayout(new GridLayout(4, 2, 20, 25));
		initComponents();
	}
	
	@Override
	protected void initComponents()
	{
		test = new JLabel("Login");
		this.add(test);
		this.add(new JLabel());
		
		uNameLabel = new JLabel("Username:");
		this.add(uNameLabel);
		uName = new JTextField("SampleUsername");
		this.add(uName);
		
		serverAddressLabel = new JLabel("Server:");
		this.add(serverAddressLabel);
		serverAddress = new JTextField("localhost");
		this.add(serverAddress);
		
		this.add(new JLabel());
		submitButton = new JButton("Login");
		submitButton.addActionListener((event) -> {
			loginButton();
		});
		
		this.add(submitButton);
	}
	
	private void loginButton()
	{
		this.serverConnector.connectToServer(serverAddress.getText());
		
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
		}
		
		if (!this.serverConnector.thread.addUser(uName.getText()))
		{
			Client.close();
			System.err.println("Error");
			return;
		}
		
		Client.changeView(new ChatRoomWindow(this.serverConnector));
	}
}
