package fr.vylaria.api.socket;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.net.URISyntaxException;

public class SocketConnection {

    private Socket socket;

    public SocketConnection(String ip, int port) throws URISyntaxException {

        this.socket = IO.socket("http://"+ip+":"+port);

        this.socket.connect();

    }

    public void send(String channel, String data){
        this.socket.emit(channel, data);
    }

    public Socket getSocket(){
        return this.socket;
    }


}
