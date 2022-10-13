package br.com.fiap;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

public class PrimaryController {
    @FXML
    private PasswordField passFieldSenha;    

    private String senhaMestra = "123";

    @FXML
    public void conectar() throws IOException {
        if (passFieldSenha.getText().trim().equals(senhaMestra)) {
            App.setRoot("addSenha");
        } else {
            alertaErro("🚫 Senha incorreta 🚫");
        }
    }

    public void alertaErro(String msg) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setContentText(msg);
        alerta.show();
    }
}
