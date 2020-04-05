package Vistas;

import Controlador.ControladorUsuarios;
import Modelo.ModeloUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {
    private JTextPane nombresTxt;
    private JPanel panel1;
    private JButton validarBtn;
    private JButton registrarBtn;
    public JScrollPane scroll;
    private JButton borrarBtn;
    public JTextPane textoValidos;
    public DefaultListModel<ModeloUsuario> modeloUsuario;
    public ControladorUsuarios controlador;
    private Principal estaVista;

public Principal(ControladorUsuarios controlador)
{
    estaVista = this;
this.controlador = controlador;

    actualizarNombres();

    registrarBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           Registrar registrar = new Registrar(estaVista);
            registrar.setLocationRelativeTo(SwingUtilities.getRoot((Component) e.getSource()));
            registrar.pack();
            registrar.setVisible(true);
        }
    });

    borrarBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Borrar borrar = new Borrar(estaVista);
            borrar.setLocationRelativeTo(SwingUtilities.getRoot((Component) e.getSource()));
            borrar.pack();
            borrar.setVisible(true);
        }
    });


    validarBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Validar validar = new Validar(estaVista);
            validar.setLocationRelativeTo(SwingUtilities.getRoot((Component) e.getSource()));
            validar.pack();
            validar.setVisible(true);
        }
    });
}

    public void actualizarNombres(){
    nombresTxt.setText("");
        for (ModeloUsuario usuario: controlador.getUsuarios()){
            String nombre = usuario.getNombre() + "\n";
            nombresTxt.setText(nombre + nombresTxt.getText());
        }

    }

   /* private void createUIComponents() {
        tblEmpleados = new JTable();
        Object[] columnas = {"id", "nombre", "edad"};
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnas);
        tblEmpleados.setModel(tableModel);
    }*/

public JPanel getPrincipalPanel(){return  panel1;}
}
