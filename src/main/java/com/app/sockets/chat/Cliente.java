package com.app.sockets.chat;

import java.io.*;
import java.net.Socket;

public class Cliente implements Runnable{

    //Creamos los atriburtos de puerto y mensaje
    private int puerto;
   
    private String mensaje;
    public Cliente(int puerto, String mensaje) {
        this.puerto = puerto;
        this.mensaje = mensaje;
    }
     
    @Override
    public void run() {
        //Host del servidor
        final String HOST = "127.0.0.1";
        
        //Puerto del servidor
        DataOutputStream salida;
        
        try{
            //conexion con el cliente mediante el puerto que se va a enviar
            Socket socket = new Socket(HOST, puerto);
            //En la variable salida se va a poner lo que se va a enviar al cliente 
            salida = new DataOutputStream(socket.getOutputStream());
            //Enviamos el mensaje
            salida.writeUTF(mensaje);
        }catch(IOException error){
            System.out.println(error);
        }
    }
    
}
