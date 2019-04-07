package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleCliente implements Initializable{
	@FXML
	private ListView<Pessoa> lvCliente;
	private List<Pessoa> pessoas = new ArrayList<>();
	private ObservableList<Pessoa> obsPessoa;
	private Button idSalvar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarClientes();
	}

	public void carregarClientes(){
		Pessoa c1 = new Pessoa("123.456.789-12", "Rebeca", (long) 1140028922, "rebeca@iarru.com", 1, 2, 3, "Rua", "Cidade", 420, "12345-12");
		Pessoa c2 = new Pessoa("098.765.432-10", "João", (long) 1234512345, "joao@rotmeio.com", 2, 0, 3, "Calçada", "Cidade", 69, "21543-21", 13);
		pessoas.add(c1);
		pessoas.add(c2);
		obsPessoa = FXCollections.observableArrayList(pessoas);
		lvCliente.setItems(obsPessoa);

	}

}
