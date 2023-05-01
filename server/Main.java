import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Objects;


public class Main {

    public static void main(String[] args) {
        //using serversocket as argument to automatically close the socket
        //the port number is unique for each server
        String puerto = JOptionPane.showInputDialog("Puerto del server: ");
        //list to add all the clients thread
        if(Objects.nonNull(puerto)){

        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(Integer.parseInt(puerto))){
            MarcoServidor mimarco = new MarcoServidor();
            mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            while(true) {
                Socket socket = serversocket.accept();
                
                ServerThread serverThread = new ServerThread(socket, threadList, mimarco);
                //starting the thread
                threadList.add(serverThread); 
                serverThread.start();

                //get all the list of currently running thread

            }

            
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        }
        }
    }
}

class MarcoServidor extends JFrame {

    public MarcoServidor() {

        setBounds(1200, 300, 280, 350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

    }
    public JTextArea areatexto;
}