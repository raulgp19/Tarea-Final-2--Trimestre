package Vistas;

import Algoritmos.AlgoritmoSHA512;
import Algoritmos.AlgoritmoMD5;
import Algoritmos.Algoritmos;
import Modelo.ModeloUsuario;

import javax.swing.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;

public class Validar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nombreTxt;
    private JTextField contraseñaTxt;
    private JLabel nombreLbl;
    private JLabel contraseñaLbl;
    private Principal vistaPadre;


    public Validar(Principal vistaPadre) {
        this.vistaPadre = vistaPadre;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (NoSuchAlgorithmException ex) {
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

    private void onOK() throws NoSuchAlgorithmException {
        Algoritmos usedAlgoritmo = null;

            for (ModeloUsuario usuario : vistaPadre.controlador.getUsuarios()) {
                if (usuario.getAlgoritmo() == 0)
                {
                    usedAlgoritmo = new AlgoritmoSHA512();
                }
                if (usuario.getAlgoritmo() == 1)
                {
                    usedAlgoritmo = new AlgoritmoMD5();
                }
                if (usuario.getNombre().equals(nombreTxt.getText()) && usuario.getHash().equals(usedAlgoritmo.getSaltedPasswordHash(contraseñaTxt.getText(), usuario.getSalt()))) {
                    vistaPadre.textoValidos.setText("Nombre: " + nombreTxt.getText() + "\n Contraseña: " + contraseñaTxt.getText() + "\n Hash: " + usuario.getHash() + "\n Salt: " + usuario.getSalt() +"\n Algoritmo:"+usuario.getAlgoritmo() +"\n Válido: Si" + "\n \n" + vistaPadre.textoValidos.getText());
                    dispose();
                    return;
                }
            }
        vistaPadre.textoValidos.setText("Nombre: " + nombreTxt.getText() + "\n Contraseña: " + contraseñaTxt.getText() + "\n Válido: No" + "\n \n" + vistaPadre.textoValidos.getText());
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    /*public static void main(String[] args) {
        Validar dialog = new Validar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
