package cajero;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JOptionPane;

public class MenuInicial extends JFrame {

    JPanel panelPrincipal, Botton;
    JTextField BancoTitulo;

    public MenuInicial() {
        super();
        setSize(600, 100);
        setTitle("Cajero Automatico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        panelPrincipal = (JPanel) this.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());

        CuadroDeTexto BancoTituloG= new CuadroDeTexto(false);
        BancoTitulo = BancoTituloG.RegresarPantalla();
        BancoTitulo.setText("Moviendo a Mexico gobierno federal");
        Botton = new JPanel();
        
        nuevoBotonOperacion("Registro");
        panelPrincipal.add("Center", Botton);
        panelPrincipal.add("North", BancoTitulo);
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });
        Botton.add(btn);
    }

    private void operacionPulsado(String tecla) {
       if (tecla.equals("Registro")) {
           Acceso Reg = new Acceso();
           Reg.setVisible(true);
           this.dispose();
        }
    }
}
