package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Aluguel;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.text.Text;
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
    private TableView<Aluguel> table;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private ComboBox<String> comboCliente;
    @FXML
    private ComboBox<String> comboSeguro;
    @FXML
    private ComboBox<String> comboGrupoCarro;
    @FXML
    private ComboBox<String> comboCarro;
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
    private List<String> gruposDisp;
    private List<String> marcaCarros;
    @FXML
    private Text txtErro;
    
	public List<Aluguel> listaAluguel = new ArrayList<>();


    @Override
	public void initialize(URL location, ResourceBundle resource) {
		if(location.toString().contains("TelaAluguel")){

//			List<Aluguel> listaCarro = new ArrayList<>();
//			listaCarro = Aluguel.getAll();
			
			colCliente.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
			colDataRetirada.setCellValueFactory(new PropertyValueFactory<>("DataRet"));
			colDataEntrega.setCellValueFactory(new PropertyValueFactory<>("DataEnt"));
			colCarro.setCellValueFactory(new PropertyValueFactory<>("Carro"));

			Aluguel temp = new Aluguel("aa", "bb", "12", "34");
			listaAluguel.add(temp);
			Aluguel temp1 = new Aluguel("aAa", "bBb", "132", "374");
			listaAluguel.add(temp1);
			ObservableList<Aluguel> lista = FXCollections.observableArrayList(listaAluguel);
			table.setItems( lista );
		}
    	if(location.toString().contains("CadastroAluguel")){
	    	nomeClientes = Cliente.getAllNames();
	        ObservableList<String> listClientes = FXCollections.observableArrayList(nomeClientes);
	        comboCliente.setItems(listClientes);

	        nomeGrupos = new ArrayList<>();
	        nomeGrupos.add("A");
	        nomeGrupos.add("B");
	        nomeGrupos.add("C");
	        nomeGrupos.add("D");
	        nomeGrupos.add("E");
	        nomeGrupos.add("F");

	        ObservableList<String> listGrupos = FXCollections.observableArrayList(nomeGrupos);
	        comboGrupoCarro.setItems(listGrupos);

	        ObservableList<String> listSeguros = FXCollections.observableArrayList("Básico","Avançado");
	        comboSeguro.setItems(listSeguros);
		}

    }
    @FXML
    void carregaCarrosDeGrupoEspecifico(MouseEvent event) {
    	if(comboGrupoCarro.getValue() == null){
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Grupo do Carro");
	    	alert.setHeaderText("");
	    	alert.setContentText("Selecione um Grupo antes de selecionar um Carro!");
	    	alert.showAndWait();
    	}else{
    		marcaCarros = Carro.getCarOfAGroup(comboGrupoCarro.getValue());
    		ObservableList<String> listCars = FXCollections.observableArrayList(marcaCarros);
	        comboCarro.setItems(listCars);
    	}
    }
    
    //funcao sem ser chamada pelo fxml
    void carregaCarrosDeGrupoEspecifico() {
    	if(comboGrupoCarro.getValue() == null){
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Grupo do Carro");
	    	alert.setHeaderText("");
	    	alert.setContentText("Selecione um Grupo antes de selecionar um Carro!");
	    	alert.showAndWait();
    	}else{
    		marcaCarros = Carro.getCarOfAGroup(comboGrupoCarro.getValue());
    		ObservableList<String> listCars = FXCollections.observableArrayList(marcaCarros);
	        comboCarro.setItems(listCars);
    	}
    }
    
    //disponibilidade do grupo/upgrade/downgrade
	@FXML
    void checaDispGrupo(ActionEvent event) {
    	gruposDisp = new ArrayList<>();
    	gruposDisp = Carro.getAllGrupos();
    	String grupoDesejado = comboGrupoCarro.getValue();
    	boolean grupoAtualizado = false;
    	
    	if(!gruposDisp.contains(comboGrupoCarro.getValue())) {   		
    		int indexGrpDesej = nomeGrupos.indexOf(grupoDesejado);
    		
    		//checar por grupos para upgrade
    		for(int i = indexGrpDesej; i < nomeGrupos.size() - indexGrpDesej; i++) {
    			if(gruposDisp.contains(nomeGrupos.get(i))) {
        			comboGrupoCarro.setValue(nomeGrupos.get(i));
        			carregaCarrosDeGrupoEspecifico();
        			System.out.println("a");
        			txtErro.setText("Nao existem carros do grupo " + grupoDesejado + " no momento. Upgrade efetuado.");
        			grupoAtualizado = true;
        			break;
    			}
    		}
    		
    		//checar por grupos para downgrade
    		if(!grupoAtualizado) {
        		for(int i = indexGrpDesej; i > 0; i--) {
        			if(gruposDisp.contains(nomeGrupos.get(i))) {
            			comboGrupoCarro.setValue(nomeGrupos.get(i));
            			carregaCarrosDeGrupoEspecifico();
            			System.out.println("a");
            			txtErro.setText("Nao existem carros do grupo " + grupoDesejado + " ou de grupos superiores no momento. Downgrade efetuado.");
            			grupoAtualizado = true;
            			break;
        			}
        		}
    		} 
    		//caso nao haja possibilidade de upgrade ou downgrade = sem carro
    		if(!grupoAtualizado){
        		Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Grupos de Carro");
    	    	alert.setHeaderText("");
    	    	alert.setContentText("No momento nao existem carros disponiveis.");
    	    	alert.showAndWait();
    		}
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
	
	@FXML
	void atualizarTable() {
		ObservableList<Aluguel> lista = FXCollections.observableArrayList(listaAluguel);
		table.setItems( lista );
	}

	@FXML
    void salvarAluguel(ActionEvent event) {
		boolean cadastroValido = true;
		if(comboCliente.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione com o nome do Cliente.");
		}
		if(comboSeguro.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione com o Seguro.");
		}
		if(comboGrupoCarro.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione o Grupo do Carro.");
		}
		if(comboCarro.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione com o nome do Carro.");
		}
		if(dataRetirada.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione com a Data de Retirada.");
		}
		if(dataEntrega.getValue() == null){
			cadastroValido = false;
    		txtErro.setText(txtErro.getText()+"\nSelecione com a Data de Entrega.");
		}
		if(cadastroValido){
			System.out.println("Dados obtidos: \n"+"Cliente:"+comboCliente.getValue()
			+"\nSeguro:"+comboSeguro.getValue()+"\nGrupo:"+comboGrupoCarro.getValue()
			+"\nCarro:"+comboCarro.getValue()+"\nData Retirada:"+dataRetirada.getValue()
			+"\nData Entrega:"+dataEntrega.getValue()
		);
			Aluguel temp = new Aluguel(comboCliente.getValue(), comboGrupoCarro.getValue(), dataRetirada.getValue().toString(), dataEntrega.getValue().toString());
			Stage stage = (Stage) idSalvar.getScene().getWindow();
			stage.close();listaAluguel.add(temp);
			
			//System.out.println(temp.getCliente());
			//System.out.println(listaAluguel.get(0).getCliente()); //printa apenas o cliente recebido deste cadastro, ou seja, a lista que recebe o aluguel eh local
			
		}

    }


}
