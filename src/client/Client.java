package client;

import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;
import client.gui.view.ChatRoomWindow;
import client.gui.view.LoginWindow;
import server.Server;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Client
{
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}
	
	// Entry point for the client
	public static void main(String[] args)
	{
		JFrame mainFrame = new JFrame("Client");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		AppPanel login = new LoginWindow(ServerConnector.instance());
		AppPanel login = new ChatRoomWindow(ServerConnector.instance());
		mainFrame.setContentPane(login);
		mainFrame.setMinimumSize(login.minDimension);
		mainFrame.setVisible(true);
		mainFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				ServerConnector.instance().close();
			}
		});
	}
}
