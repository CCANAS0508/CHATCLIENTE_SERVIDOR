package com.app.sockets.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
 
//Runnable Hilos y subprocesos
//Observable permite que un objeto pueda notificar a otros objetos sobre los cambios de su estado
public class Servidor extends Observable implements Runnable {

    private int puerto;
    //
    public Servidor(int puerto) {
        this.puerto = puerto;
    }
    // Crear un serv 
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket socket = null;
        //DataInputStream nos sirve para recibir los datos que son enviados desde el cliente 
        DataInputStream entrada;

        try {
            //Creamos el servidor del socket e inicio del servidor
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones por este motivo se crea un bucle infinito 
                //para que se actualice constantente para enviar y recibir datos o peticiones
            while (true) {
                //Espero que el cliente se contecte
                socket = servidor.accept();
                System.out.println("Cliente conectado");
                entrada = new DataInputStream(socket.getInputStream());

                //Leemos el mensaje
                String mensaje = entrada.readUTF();
                System.out.println(mensaje);

                this.setChanged(); // notificación de un cambio para hacer lo indicado por el objeto observable
                this.notifyObservers(mensaje);  // notificar estos cambios con observable mostrar mensaje
                this.clearChanged(); // limpiar el cambio cuando ya se notifica 

                socket.close(); // cerrar puente de conexión
                System.out.println("Cliente desconectado");
            }
        } catch (IOException error) {
            System.out.println(error); //error en caso dado que no se conecte al servidor
        }
    }

}
