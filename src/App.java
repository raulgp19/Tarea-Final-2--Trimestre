import Controlador.ControladorUsuarios;
import Vistas.Principal;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setPreferredSize(new Dimension(980,720));
        ControladorUsuarios controladorEmpleados = new ControladorUsuarios();
        frame.setContentPane(new Principal(controladorEmpleados).getPrincipalPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
