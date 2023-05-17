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
        boolean err=true;
        String input1;
        Integer puerto = 0;
        ServerSocket socket_verificador;
        do{
            try{
                input1 = JOptionPane.showInputDialog("Puerto del server: ");
                puerto = Integer.parseInt(input1);
                if(puerto > 1023){
                    socket_verificador = new ServerSocket(puerto);
                    socket_verificador.close();
                    err=false;
                }
            }catch(NumberFormatException e){
                // e.printStackTrace();
            }catch(Exception e){
            }
        }while(err);
        
        //list to add all the clients thread
        if(Objects.nonNull(puerto)){

        ArrayList<ServerThread> threadList = new ArrayList<>();
        try (ServerSocket serversocket = new ServerSocket(puerto)){
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