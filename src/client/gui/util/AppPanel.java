package client.gui.util;

import javax.swing.*;
import java.awt.*;

public abstract class AppPanel extends JPanel
{
	public final Dimension minDimension;
	
	public AppPanel(Dimension minDimension)
	{
		this.minDimension = minDimension;
	}
}
