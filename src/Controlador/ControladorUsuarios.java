package Controlador;

import DAO.UsuarioDAO;
import Modelo.ModeloUsuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorUsuarios {

        public ArrayList<ModeloUsuario> getUsuarios() {
            ArrayList<ModeloUsuario> listaUsuario = new ArrayList<>();
            try {
                UsuarioDAO empleadoDAO = new UsuarioDAO();
                listaUsuario = empleadoDAO.getUsuarios();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return listaUsuario;

        }

        public ArrayList<ModeloUsuario> getUsuariosPorPatron(String patron) {
            ArrayList<ModeloUsuario> listaEmpleados = new ArrayList<>();
            try {
                UsuarioDAO empleadoDAO = new UsuarioDAO();
                listaEmpleados = empleadoDAO.buscarPorPatron(patron);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return listaEmpleados;

        }
    }

