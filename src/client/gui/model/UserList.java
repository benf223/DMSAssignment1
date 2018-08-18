package client.gui.model;


import java.util.ArrayList;
import java.util.Arrays;

public class UserList
{
	private static UserList instance;
	
	public static UserList instance()
	{
		if (instance == null)
		{
			instance = new UserList();
		}
		
		return instance;
	}
	
	private ArrayList<String> userList;
	
	private UserList()
	{
		userList = new ArrayList<>();
	}
	
	public void set(ArrayList<String> users)
	{
		userList.clear();
		userList.addAll(users);
	}
	
	// Data for the JPanels
	public String[] getUsers()
	{
		return userList.toArray(new String[0]);
	}
	
	public boolean contains(String target)
	{
		return userList.contains(target);
	}
}
