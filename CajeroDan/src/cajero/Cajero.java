package cajero;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JOptionPane;


public class Cajero extends JFrame {
    ///-----------------------Seccion de Variables -----------------------\\\\
    int numero = 0;
    Usuario user;
    JTextField CuadroTexto, CuadroTexto1, CuadroTexto2, CuadroTexto3;
    JPanel panelPrincipal, panelNumeros, panelOperaciones, panelTextos;

    public Cajero(Usuario user) {
        ///-----------------------Seccion de Ventana -----------------------\\\\
        super();
        setSize(600, 200);
        setTitle("Cajero Automatico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.user = user;

        JPanel panelPrincipal = (JPanel) this.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());

        CuadroDeTexto Texto0 = new CuadroDeTexto(false);
        this.CuadroTexto = Texto0.RegresarPantalla();
        this.CuadroTexto.setText("Usuario: " + user.getNombre());

        CuadroDeTexto Texto1 = new CuadroDeTexto(false);
        this.CuadroTexto1 = Texto1.RegresarPantalla();
        this.CuadroTexto1.setText("Saldo: " + user.getSaldo() + "Pesos");

        CuadroDeTexto Texto2 = new CuadroDeTexto(false);
        this.CuadroTexto2 = Texto2.RegresarPantalla();
        this.CuadroTexto2.setText("Operacion $");

        CuadroDeTexto Texto3 = new CuadroDeTexto(true);
        this.CuadroTexto3 = Texto3.RegresarPantalla();
        this.CuadroTexto3.setText("0 pesos");
        this.CuadroTexto3.requestFocusInWindow();

        panelOperaciones = new JPanel();
        nuevoBotonOperacion("Depositar");
        nuevoBotonOperacion("Retirar");
        nuevoBotonOperacion("Regresar");
        
        panelTextos = new JPanel();
        panelTextos.add(CuadroTexto);
        panelTextos.add(CuadroTexto1);
        panelTextos.add(CuadroTexto2);
        panelTextos.add(CuadroTexto3);

        
        panelPrincipal.add("South", panelOperaciones);
        panelPrincipal.add("Center", panelTextos);
        
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton Botoncito = (JButton) evt.getSource();
                operacionPulsado(Botoncito.getText());
            }
        });
        panelOperaciones.add(btn);
    }

    private void operacionPulsado(String tecla) {
        CuadroTexto2.setText("Cargando...");
        try {
            numero = Integer.parseInt(this.CuadroTexto3.getText());
        } catch (Exception e) {
            CuadroTexto2.setText("Ingresa un numero");
        }
        boolean condicion = false;
        switch (tecla) {
            case "Depositar":
                condicion = nums();
                int Confirma;
                double numi = .5;
                Confirma = JOptionPane.YES_NO_OPTION;
                if (condicion) {
                    Confirma = JOptionPane.showConfirmDialog(null, "Desea donar teleton", "Porfa",Confirma);
                    switch(Confirma){
                        case JOptionPane.NO_OPTION:
                        user.setSaldo(this.user.getSaldo() + numero);
                        CuadroTexto1.setText("Saldo: " + this.user.getSaldo() + "Pesos");
                        CuadroTexto2.setText("Deposito Exitoso");
                        break;
                        case JOptionPane.YES_OPTION:
                        user.setSaldo((int) (this.user.getSaldo()*numi + numero));
                        CuadroTexto1.setText("Saldo: " + this.user.getSaldo() + "Pesos");
                        CuadroTexto2.setText("Deposito Exitoso");    
                    }
                } else {
                    CuadroTexto2.setText("Coloca los numeros");
                     JOptionPane.showMessageDialog(null, "¡INGRESA UN NUMERO VALIDO!");
                }
                CuadroTexto3.setText("0");
                break;
            case ("Retirar"):
                if (this.user.getSaldo() >= numero) {

                    int Confirmacion;
                    Confirmacion = JOptionPane.YES_NO_OPTION;
                    condicion = nums();
                    if (condicion) {
                        Confirmacion = JOptionPane.showConfirmDialog(null, "Vas a Retirar $" + numero + " pesos?", "Confirmacion", Confirmacion);
                        switch (Confirmacion) {
                            case JOptionPane.NO_OPTION:
                                CuadroTexto2.setText("Retiro Cancelado");
                                break;
                            case JOptionPane.YES_OPTION:
                                int SaldoOriginal=this.user.getSaldo();
                                user.setSaldo(SaldoOriginal - numero);
                                CuadroTexto2.setText("Retiro Completado");
                                CuadroTexto1.setText("Saldo: "+this.user.getSaldo());
                                break;
                            case JOptionPane.CLOSED_OPTION:
                                CuadroTexto2.setText("Cancelada");
                                break;
                            default:
                                break;
                        }
                    } else {
                        CuadroTexto2.setText("ingresa un numero");
                    }

                    CuadroTexto3.setText("0");

                } else {
                    CuadroTexto2.setText("Saldo Insuficiente");
                    CuadroTexto3.setText("0");
                }
                break;
            case "Regresar":
                Acceso m = new Acceso();
                m.setVisible(true);
                this.dispose();
                break;
            default:
                break;
        }
    }

    private boolean nums() {
        if (numero <= 0) {
            numero = 0;
            CuadroTexto2.setText("Numero invalido");
            return false;
        } else {
            return true;
        }
    }
}
