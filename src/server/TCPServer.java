package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private final int port;
    public static final int PORTNUMBER = 8049;

    public static void main(String[] args) throws IOException, InterruptedException {
        TCPServer tcpServer = new TCPServer(PORTNUMBER);

        tcpServer.doSomething();
    }

    private void doSomething() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(this.port);
        System.out.println("server socket created");
        Socket socket = serverSocket.accept();
        System.out.println("client connection accepted");

        socket.getInputStream().read();
        System.out.println("read something");

        OutputStream os = socket.getOutputStream();
        os.write(":)".getBytes());
        System.out.println("write something");


        Thread.sleep(5000);
        System.out.println("woke up");
        os.close();
    }

    TCPServer (int port){
        this.port = port;
    }
}
