package client;

import server.TCPServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TCPClient {
    private static final String SMTH = "smth";
    private final String hostname;
    private final int port;

    public static final String HOST = "localhost";
    public static final int PORT = 8049;

    public static void main(String[] args) throws IOException {

        if(args.length < 2){
             System.out.println("missing parametrs");
        }
        String hostname = args[0];
        String portString  = args[1];

        int portnumber = Integer.parseInt(portString);
        TCPClient tcpClient = new TCPClient(hostname,portnumber );
        tcpClient.doSomething();
    }

    private void doSomething() throws IOException {
        Socket socket = new Socket(this.hostname,this.port);
        socket.getOutputStream().write(SMTH.getBytes());

        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[10000];
        int i = 0;

        int read = 0;
        do{
            read = is.read();
            if (read != -1){
                byte readByte = (byte) read;
                buffer[i++] = readByte;
            }
        }while(read != -1);

        byte[] receivedBytes = new byte[i];
        for(int j=0; j<i; j++){
            receivedBytes[j] = buffer[j];
        }

        String receivedString = new String(receivedBytes);

        System.out.println("recived" + receivedString);
    }

    public TCPClient(String hostname,int port) {
        this.hostname = hostname;
        this.port = port;
    }
}
