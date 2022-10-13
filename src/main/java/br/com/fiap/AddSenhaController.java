package br.com.fiap;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import br.com.fiap.model.Senha;

public class AddSenhaController implements Initializable {
    @FXML
    private TextArea txtAreaDesc;
    @FXML
    private TextField txtFieldNovaSenha;
    @FXML
    private TextField txtFieldConfSenha;

    @FXML
    private TableView<Senha> tbViewSenhas;
    @FXML
    private TableColumn<Senha, String> tbColumnDesc;
    @FXML
    private TableColumn<Senha, String> tbColumnSenha;

    private String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private String user = "";
    private String password = "";

    @FXML
    public void cadastrar() {
        
        if (txtFieldNovaSenha.getText().trim().equals(txtFieldConfSenha.getText().trim())) {
            Senha senha = new Senha();

            senha.setDesc(txtAreaDesc.getText());
            senha.setSenha(txtFieldConfSenha.getText());

            try {
                Connection con = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement stm = con.prepareStatement("insert into ddd_senhas values (?, ?)");

                stm.setString(1, senha.getDesc());
                stm.setString(2, senha.getSenha());

                con.close();
                alertaSucesso("✔ Senha salva com sucesso ✔");
            } catch(SQLException e) {
                alertaErro("❌ " + e.getMessage() + " ❌");
            }
        } else {
            alertaErro("❌ As senhas não são iguais ❌");
        }
    }

    @FXML
    public void atualizar() {
        try {
            Connection con = DriverManager.getConnection(this.url, this.user, this.password);
            PreparedStatement stm = con.prepareStatement("select * from ddd_senhas");
            ResultSet result = stm.executeQuery();

            List<Senha> senhas = new ArrayList<Senha>();

            while (result.next()) {
                Senha senha = new Senha();

                senha.setDesc(result.getString("desc_senha"));
                senha.setSenha(result.getString("senha"));

                senhas.add(senha);
            }

            con.close();
            tbViewSenhas.getItems().addAll(senhas);
            System.out.println(senhas);

        } catch(SQLException e) {
            alertaErro("❌ " + e.getMessage() + " ❌");
        }
    }

    public void alertaErro(String msg) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setContentText(msg);
        alerta.show();
    }

    public void alertaSucesso(String msg) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setContentText(msg);
        alerta.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tbColumnDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        tbColumnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        atualizar();
    }
}
