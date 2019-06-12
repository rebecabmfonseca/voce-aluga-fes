package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;

public class ControleAluguel {
    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colDataRetirada;

    @FXML
    private TableColumn<?, ?> colDataEntrega;

    @FXML
    private TableColumn<?, ?> colCarro;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<?> comboCliente;

    @FXML
    private ComboBox<?> comboSeguro;

    @FXML
    private ComboBox<?> comboGrupoCarro;

    @FXML
    private ComboBox<?> comboCarro;

    @FXML
    private DatePicker dataRetirada;

    @FXML
    private DatePicker dataEntrega;

    @FXML
    private Button idSalvar;

    @FXML
    private Button btnSair;

}
