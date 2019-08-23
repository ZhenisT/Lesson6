package server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {
    Vector <ClientHandler> clients;
    private static final Logger logserv = Logger.getLogger(Server.class);

    public Server() throws SQLException {
        AuthService.connect();

        ServerSocket server = null;
        Socket socket = null;

        try {
            clients = new Vector<>();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");
            logserv.info("Сервер запущен");

            while(true){
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this,socket);
            }

        } catch (IOException e) {
            logserv.error(e);
        } finally {
            try {
                logserv.info("socket closed");
                socket.close();
            } catch (IOException e) {
                logserv.error(e);
            }
            try {
                logserv.info("server closed");
                server.close();
            } catch (IOException e) {
                logserv.error(e);
            }
            AuthService.disconnect();
        }
    }

    public void broadcastMsg(String str, String sender){
        AuthService.addMessageToDB(sender,null,str,"0000");

        for (ClientHandler o: clients) {
            o.sendMsg(sender + ": "+ str);
        }
    }

    public void broadcastMsg(String str, String sender, String receiver){
        AuthService.addMessageToDB(sender,receiver,str,"0000");

        for (ClientHandler o: clients) {
            if(o.getNick().equals(receiver) || o.getNick().equals(sender)){
                o.sendMsg("private ["+ sender +" ] to [ "+receiver+" ] :"+ str);
            }
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        broadcastClientList();
//        clientHandler.sendMsg(
//                AuthService.getMessagesFromDBForNick(clientHandler.getNick())
//        );
    }

    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientList();
    }

    public boolean isNickAuthorized(String nick){
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)){
                return true;
            }
        }
        return false;
    }

    public void broadcastClientList(){
        StringBuilder sb = new StringBuilder();
        sb.append("/clientlist ");

        for (ClientHandler o: clients) {
            sb.append(o.getNick()+" ");
        }

        String msg = sb.toString();

        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }
}
