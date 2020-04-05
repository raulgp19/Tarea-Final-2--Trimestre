package Vistas;

import Algoritmos.AlgoritmoSHA512;
import Algoritmos.AlgoritmoMD5;
import Algoritmos.Algoritmos;
import DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Registrar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombreTxt;
    private JTextField contraseñaTxt;
    private JLabel nombreLbl;
    private JLabel contraseñaLbl;
    private JLabel algoritmoLbl;
    public JComboBox algoritmoCb;
    private Principal vistaPadre;

    public Registrar(Principal vistaPadre) {
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
        if (algoritmoCb.getSelectedIndex() == 0)
        {
            usedAlgoritmo = new AlgoritmoSHA512();
        }
        if (algoritmoCb.getSelectedIndex() == 1)
        {
            usedAlgoritmo = new AlgoritmoMD5();
        }
        String salt = usedAlgoritmo.getSalt();

        try {
            usuarioDao.crear(nombreTxt.getText(), usedAlgoritmo.getSaltedPasswordHash(contraseñaTxt.getText(), salt), salt, algoritmoCb.getSelectedIndex());
            vistaPadre.actualizarNombres();
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Algún problema ha ocurrido");
            dispose();
        }

    }

    private void onCancel() {

        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        algoritmoCb = new JComboBox();
        algoritmoCb.addItem("SHA-512");
        algoritmoCb.addItem("MD5");
    }

    /*public static void main(String[] args) {
        Registrar dialog = new Registrar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
