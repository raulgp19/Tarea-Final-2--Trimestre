package Vistas;

import Algoritmos.Algoritmos;
import Algoritmos.AlgoritmoMD5;
import Algoritmos.AlgoritmoSHA512;
import DAO.UsuarioDAO;
import Modelo.ModeloUsuario;

import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Borrar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombreTxt;
    private JTextField contraseñaTxt;
    private JLabel nombreLbl;
    private JLabel contraseñaLbl;
    private Principal vistaPadre;

    public Borrar(Principal vistaPadre) {
        this.vistaPadre = vistaPadre;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() throws SQLException, NoSuchAlgorithmException {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Algoritmos usedAlgoritmo = null;

        try {
            for (ModeloUsuario usuario : vistaPadre.controlador.getUsuarios()) {
                if (usuario.getAlgoritmo() == 0)
                {
                    usedAlgoritmo = new AlgoritmoSHA512();
                }
                else if (usuario.getAlgoritmo() == 1)
                {
                    usedAlgoritmo = new AlgoritmoMD5();
                }
                if (usuario.getNombre().equals(nombreTxt.getText()) && usuario.getHash().equals(usedAlgoritmo.getSaltedPasswordHash(contraseñaTxt.getText(), usuario.getSalt()))) {
                    usuarioDao.borrar(nombreTxt.getText(), usedAlgoritmo.getSaltedPasswordHash(contraseñaTxt.getText(), usuario.getSalt()));
                    vistaPadre.actualizarNombres();
                    JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
                    dispose();
                }
            }
        }catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Algún problema ha ocurrido");
            dispose();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /*public static void main(String[] args) {
        Borrar dialog = new Borrar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
