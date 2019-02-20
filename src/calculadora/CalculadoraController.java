/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author lisas
 */
public class CalculadoraController implements Initializable {

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click_Punto(ActionEvent event) {
        if (!Punto && !Digito) {
            label.setText("0.");
            Digito = true;
        } else if (!Punto) {
            String ValActual = label.getText();
            label.setText(ValActual + ".");

        }
        Punto = true;
    }

    private boolean Digito = false;
    private boolean Punto = false;
    private int numOperandos = 0;
    private double Operando1, Operando2;
    private char Operador = ' ';

    private void digitoPantalla(String numero) {
        if (!Digito && numero.equals("0")) {
            return;
        } else if (!Digito) {
            label.setText("");
            Punto = false;
        }
        String valActual = label.getText();
        label.setText(valActual + numero);
        Digito = true;
    }

    @FXML
    private void Clear(ActionEvent event) {
        Digito = false;
        Punto = false;
        numOperandos = 0;
        Operando1 = 0;
        Operando2 = 0;
        Operador = ' ';
        label.setText("0");
    }

    private void evalOperador(String Operador) {
        if (Digito) {
            numOperandos++;
            if (numOperandos == 1) {
                Operando1 = Double.parseDouble(label.getText());
            } else if (numOperandos == 2) {
                Operando2 = Double.parseDouble(label.getText());
                switch (this.Operador) {
                    case '+':
                        Operando1 = Operando1 + Operando2;
                        break;
                    case '-':
                        Operando1 = Operando1 - Operando2;
                        break;
                    case '/':
                        Operando1 = Operando1 / Operando2;
                        break;
                    case '*':
                        Operando1 = Operando1 * Operando2;
                        break;
                    case '=':
                        Operando1 = Operando2;
                        break;

                }
                label.setText(String.valueOf(Operando1));
                numOperandos = 1;
                Punto = false;
            }
            Digito = false;
            this.Operador = Operador.charAt(0);
        }
    }

    @FXML
    private void Click(ActionEvent event) {
        digitoPantalla(((Button) event.getSource()).getText());
    }

    @FXML
    private void Operadores(ActionEvent event) {
        evalOperador(((Button) event.getSource()).getText());
    }
}
