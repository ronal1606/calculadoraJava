
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    // Variables para los elementos de la interfaz
    private JTextField pantalla;
    private double num1, num2, resultado;
    private char operador;

    public Calculadora() {
        // Configuración de la ventana
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        add(pantalla, BorderLayout.NORTH);

        // Crear los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 4));

        String[] botones = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.addActionListener(this);
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
            pantalla.setText(pantalla.getText() + comando);
        } else if (comando.charAt(0) == 'C') {
            pantalla.setText("");
        } else if (comando.charAt(0) == '=') {
            num2 = Double.parseDouble(pantalla.getText());

            switch (operador) {
                case '+': resultado = num1 + num2; break;
                case '-': resultado = num1 - num2; break;
                case '*': resultado = num1 * num2; break;
                case '/':
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        pantalla.setText("Error");
                        return;
                    }
                    break;
            }
            pantalla.setText(String.valueOf(resultado));
            num1 = resultado;
        } else {
            if (!pantalla.getText().isEmpty()) {
                num1 = Double.parseDouble(pantalla.getText());
                operador = comando.charAt(0);
                pantalla.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
