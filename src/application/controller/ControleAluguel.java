package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Carro;
import application.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleAluguel implements Initializable{
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
    private ComboBox<String> comboCliente;
    @FXML
    private ComboBox<?> comboSeguro;
    @FXML
    private ComboBox<String> comboGrupoCarro;
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
    private List<String> nomeClientes;
    private List<String> nomeGrupos;

    @Override
	public void initialize(URL location, ResourceBundle resource) {
    	if(location.toString().contains("CadastroAluguel")){
	    	nomeClientes = Cliente.getAllNames();
	        ObservableList<String> listClientes = FXCollections.observableArrayList(nomeClientes);
	        comboCliente.setItems(listClientes);

	        nomeGrupos = Carro.getAllGrupos();
	        ObservableList<String> listGrupos = FXCollections.observableArrayList(nomeGrupos);
	        comboGrupoCarro.setItems(listGrupos);
		}
    	if(location.toString().contains("TelaAluguel")){

    	}

    }

    @FXML
    void alterarAluguel(ActionEvent event) {
    	System.out.println("Clicou no botão alterar");

    }

    @FXML
    void cadastrarAluguel(ActionEvent event) {
    	String path = "application/view/TelaCadastroAluguel.fxml";
		novaPagina(path);
    }

    private void novaPagina(String path) {
		try {
			Stage stage;
			Parent root;
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(path));
			root = fxmlLoader.load();
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root, 600, 600));
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
    void cancelarAluguel(ActionEvent event) {
    	System.out.println("Clicou no botão cancelar");
    }


}
