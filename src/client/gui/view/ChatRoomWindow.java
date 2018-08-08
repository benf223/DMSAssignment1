package client.gui.view;

import client.gui.controller.ServerConnector;
import client.gui.util.AppPanel;

import javax.swing.*;
import java.awt.*;

public class ChatRoomWindow extends AppPanel
{
	private Timer timer;
	
	public ChatRoomWindow(ServerConnector serverConnector)
	{
		super(serverConnector, new Dimension(400, 600));
		timer = new Timer(250, (event) -> System.out.println("test"));
		timer.start();
	}
}
