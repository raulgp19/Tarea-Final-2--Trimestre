package DAO;

import Modelo.ModeloUsuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private DBConn conn;

    public UsuarioDAO() throws SQLException {
        conn = new DBConn();
    }

    public ArrayList<ModeloUsuario> getUsuarios() throws SQLException {
        ResultSet result = conn.read("SELECT id, nombre, hash, salt, algoritmo FROM Credenciales");
        ArrayList<ModeloUsuario> listaEmpleados = new ArrayList<>();
        while (result.next())
            listaEmpleados.add(new ModeloUsuario(result.getInt("id"), result.getString("nombre"),result.getString("hash"), result.getString("salt"), Integer.parseInt(result.getString("algoritmo"))));
        return listaEmpleados;
    }

    public ArrayList<ModeloUsuario> buscarPorPatron(String nombre) throws SQLException {
        ResultSet result = conn.read("SELECT id, nombre, edad FROM Credenciales WHERE nombre LIKE '%" + nombre + "%'");
        ArrayList<ModeloUsuario> listaEmpleados = new ArrayList<>();
        while (result.next())
            listaEmpleados.add(new ModeloUsuario(result.getInt("id"), result.getString("nombre"),result.getString("hash"), result.getString("salt"), Integer.parseInt(result.getString("algoritmo"))));
        return listaEmpleados;
    }

    public int crear(String nombre, String hash, String salt, int algoritmo) throws SQLException {
        return conn.create("INSERT INTO Credenciales(nombre, hash, salt, algoritmo) VALUES ('"+nombre+"', '"+hash+"', '"+salt+"', '"+algoritmo+"');");
    }

    public int borrar(String nombre, String hash) throws  SQLException{
        return conn.delete("delete from Credenciales where nombre = '" + nombre + "' and hash = '" + hash +"';");
    }

    public int crear(ModeloUsuario usuario) throws SQLException {
        return crear(usuario.getNombre(), usuario.getHash(), usuario.getSalt(), usuario.getAlgoritmo());
    }
}
