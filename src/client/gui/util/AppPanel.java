package client.gui.util;

import client.gui.controller.ServerConnector;

import javax.swing.*;
import java.awt.*;

public abstract class AppPanel extends JPanel
{
	public final ServerConnector serverConnector;
	public final Dimension minDimension;
	
	public AppPanel(ServerConnector serverConnector, Dimension minDimension)
	{
		this.serverConnector = serverConnector;
		this.minDimension = minDimension;
	}
	
	protected abstract void initComponents();
	
	public abstract void updateElements();
}
