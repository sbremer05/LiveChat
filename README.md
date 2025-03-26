# LiveChat

A simple live chat application allowing users to chat with each other via a Java Socket connection. The server manages multiple clients, and users can communicate in real-time.

## Prerequisites
- Java Development Kit (JDK) 8 or higher

## How to Run the Application

### 1. **Compile the Server Code**
First, navigate to the directory where `Server.java` is located and compile the server code:
```sh
javac Server.java
```

### 2. **Start the Server
Once compiled, run the server:
```sh
java Server
```
The server is notified of all client activity, including clients joining and disconnecting, and all messages. The server can also send messages to all clients.

### 3. **Compile the Client Code
Next, the client compiles the client code:
```sh
javac Client.java
```

### 4. **Start the Client
Once compiled, run the client in a separate terminal window:
```sh
java Client
```
Upon running, the client will prompt the user to enter a username. After entering the username, the client will be able to send messages to the server and receive messages from other clients and the server.

### 5. **Interact
Type messages in the client terminal to send them to the server.

The server will broadcast the messages to all connected clients.

You can run multiple clients on different terminals to simulate a live chat between users.

### 6. **Server Admin Input
While the server is running, you can type messages directly into the server terminal. These messages will be broadcast to all connected clients with the prefix [Server].

### 7. **Leave Server
All users simply enter ^c and you will exit.


## Features
Real-time messaging between multiple clients.

Server handles multiple client connections and broadcasts messages.

Server-side admin can send global messages to all clients.

## Troubleshooting
Ensure that the server is running before attempting to start a client.

If you encounter issues with multiple clients connecting, ensure the port (14253) is available and not blocked by a firewall.
