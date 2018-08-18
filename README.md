# DMSAssignment1
First assignment for Distributed Mobile Systems

#Developed by
Ben Fisher ID: 15906291

#Features
- Connect to a central server
- Unique usernames
- Broadcast messages to all users
- Messages of who has joined and left the chatroom
- Targeted messages

#Connections
- Client connects to the server via TCP
- Server and client send messages over the TCP connection
- Message objects are passed to and from the server containing the required data

#Threading
- Server is multithreaded with a thread for each client connected
- Client has two threads. One to run the GUI and another to update the data structures

#Instructions
- Start an instance of a server
- Start an instance of a client
- Enter a username and a IP address to the server
- Press login
- The client will connect to a server at the specified IP address
- Send messages to all with the tag @All in the bottom left field (or any name that doesn't exist in the users list)
- Send messages to a single user by writing their username in the bottom left field
- Closing the client window will disconnect the user from the server and send a message notification to the other users
- Stopping the server process will disconnect all users from the server
