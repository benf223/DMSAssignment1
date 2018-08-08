package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class ConnectionThread implements Runnable, Observer
{
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	private boolean stopRequested;
	
	public ConnectionThread(Socket socket)
	{
		System.out.println("Connection made with " + socket.getInetAddress());
		this.socket = socket;
		
		try
		{
			in = new Scanner(this.socket.getInputStream());
			out = new PrintWriter(this.socket.getOutputStream(), true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		while (!stopRequested) {
			if (in.hasNext()) {
				processGet(in.nextLine());
			}
		}
		
		MessageStore.instance().deleteObserver(this);
		
		in.close();
		out.close();
		
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	// Switch for processing different requests;
	private void processGet(String request) {
		switch (request){
			case "test": {
				System.out.println(request);
				out.println("test res");
				break;
			}
			case "stop": {
				this.stopRequested = true;
				break;
			}
			default: {
				break;
			}
		}
	}
	
	
	@Override
	public void update(Observable o, Object arg)
	{
		// Will push update to clients
	}
}
