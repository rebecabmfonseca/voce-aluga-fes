package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ControleCliente implements Initializable{
	@FXML
	private ListView<Pessoa> lvCliente;
	private List<Pessoa> pessoas = new ArrayList<>();
	private ObservableList<Pessoa> obsPessoa;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		carregarClientes();
	}
	public void carregarClientes(){
		Pessoa c1 = new Pessoa(1,"Rebeca");
		Pessoa c2 = new Pessoa(2,"João");
		pessoas.add(c1);
		pessoas.add(c2);
		obsPessoa = FXCollections.observableArrayList(pessoas);
		lvCliente.setItems(obsPessoa);

	}

}
