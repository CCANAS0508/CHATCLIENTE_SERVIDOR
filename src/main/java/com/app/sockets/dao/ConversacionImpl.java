package com.app.sockets.dao;

import com.app.sockets.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//instancia a la clase conversiacionDAO
public class ConversacionImpl implements ConversacionDAO {
    // Conexion a la base de datos 
    Conexion instanciaMySQL = Conexion.getInstance();

    @Override
    public void registrarConversacionA(String mensaje) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMySQL.conectar();
            // insertando mensaje dentro de la tabla conversaciones los datos de la conversación A
            consulta = conexion.prepareStatement("insert into conversaciones(conversacionesA) values(?)");

            consulta.setString(1, mensaje);
            consulta.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public void registrarConversacionB(String mensaje) {
        PreparedStatement consulta = null;
        Connection conexion = null;

        try {
            conexion = instanciaMySQL.conectar();
            // insertando mensaje dentro de la tabla conversaciones los datos de la conversación A
            consulta = conexion.prepareStatement("insert into conversaciones(conversacionesB) values(?)");
            consulta.setString(1, mensaje);
            consulta.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

}
