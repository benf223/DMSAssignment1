package client.gui.view;

import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends AppPanel
{
	private JLabel test = null;
	private JLabel uNameLabel = null;
	private JTextField uName = null;
	private JLabel serverAddressLabel = null;
	private JTextField serverAddress = null;
	private JButton submitButton = null;
	
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
		serverAddress = new JTextField("localhost:1291");
		this.add(serverAddress);
		
		this.add(new JLabel());
		submitButton = new JButton("Login");
		submitButton.addActionListener((event) -> {
			this.serverConnector.connectToServer();
			try
			{
				this.serverConnector.get("test");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
		
		this.add(submitButton);
	}
}
