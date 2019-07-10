package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Carro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleVeiculo implements Initializable{

    @FXML
    private Button btnCancelar;
	@FXML
    private Button idSalvar;
    @FXML
    private Button idCadastrar;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtKM;
    @FXML
    private TextField txtPlaca;
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;

    @FXML
    private ComboBox<String> comboGrupoCarro;

    @FXML
    private TableView<Carro> table;

    @FXML
    private TableColumn<Carro, String> ColCor;

    @FXML
    private TableColumn<Carro, String> ColMarca;

    @FXML
    private TableColumn<Carro, String> ColAno;

    @FXML
    private TableColumn<Carro, String> ColKM;

    @FXML
    private TableColumn<Carro, String> ColModelo;
    @FXML
    private TableColumn<Carro, String> ColPlaca;
    @FXML
    private TableColumn<Carro, String> ColGrupo;
    @FXML
    private Button btnManutencao;


    public static Carro carroEditavel;
    private List<String> nomeGrupos;

    @FXML
    void irPaginaCadastrar(ActionEvent event) {
    	String path = "application/view/TelaCadastroVeiculo.fxml";
		novaPagina(path);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(location.toString().contains("TelaVeiculo")){

			List<Carro> listaCarro = new ArrayList<>();
			listaCarro = Carro.getAll();
			ColCor.setCellValueFactory(new PropertyValueFactory<>("Cor"));
			ColMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
			ColAno.setCellValueFactory(new PropertyValueFactory<>("Ano"));
			ColKM.setCellValueFactory(new PropertyValueFactory<>("Quilometragem"));
			ColModelo.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
			ColPlaca.setCellValueFactory(new PropertyValueFactory<>("Placa"));
			ColGrupo.setCellValueFactory(new PropertyValueFactory<>("Grupo"));

			ObservableList<Carro> lista = FXCollections.observableArrayList(listaCarro);
			table.setItems( lista );

		}
		if(location.toString().contains("TelaCadastroVeiculo")){

			if(carroEditavel!=null){
				txtCor.setText(carroEditavel.getCor());
				txtMarca.setText(carroEditavel.getMarca());
				txtAno.setText(Integer.toString(carroEditavel.getAno()));
				txtKM.setText(Integer.toString((int)carroEditavel.getQuilometragem()));
				txtModelo.setText(carroEditavel.getModelo());
				txtPlaca.setText(carroEditavel.getPlaca());
				comboGrupoCarro.setValue(carroEditavel.getGrupo());
		        nomeGrupos = new ArrayList<>();
		        nomeGrupos.add("A");
		        nomeGrupos.add("B");
		        nomeGrupos.add("C");
		        nomeGrupos.add("D");
		        nomeGrupos.add("E");
		        nomeGrupos.add("F");

		        ObservableList<String> listGrupos = FXCollections.observableArrayList(nomeGrupos);
		        comboGrupoCarro.setItems(listGrupos);
				} else {
			        nomeGrupos = new ArrayList<>();
			        nomeGrupos.add("A");
			        nomeGrupos.add("B");
			        nomeGrupos.add("C");
			        nomeGrupos.add("D");
			        nomeGrupos.add("E");
			        nomeGrupos.add("F");

			        ObservableList<String> listGrupos = FXCollections.observableArrayList(nomeGrupos);
			        comboGrupoCarro.setItems(listGrupos);
				}
		}
	}


	public void novaPagina(String path){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(path));
			Pane root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Carros");
			stage.setScene(new Scene(root, 600, 600));
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void fecharJanela(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair do Cadastro");
		alert.setHeaderText("Sair do Cadastro");
		alert.setContentText("Os dados serão perdidos, tem certeza?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Stage stage = (Stage) btnCancelar.getScene().getWindow();
		 	stage.close();
		}

	}


    @FXML
    void alterarVeiculo(ActionEvent event) {
    	String placaSelecionada;
    	if(table.getSelectionModel().getSelectedItem()!=null){
    		placaSelecionada = table.getSelectionModel().getSelectedItem().getPlaca();
    	}else{
    		TextInputDialog dialog = new TextInputDialog();
        	dialog.setTitle("Alterar Carro");
        	dialog.setHeaderText("Identificando o carro");
        	dialog.setContentText("Por favor, informe a placa do carro");
        	Optional<String> result = dialog.showAndWait();
        	placaSelecionada = result.get();
    	}
    	carroEditavel = new Carro();
    	carroEditavel = Carro.getCarro(placaSelecionada);
    	if(carroEditavel==null){
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Editar Carro");
	    	alert.setHeaderText("");
	    	alert.setContentText("Carro não encontrado!");
	    	alert.showAndWait();
    	}else{
    	   	irPaginaEditar();
    	}




    }
    private void irPaginaEditar() {
    	String path = "application/view/TelaCadastroVeiculo.fxml";
		novaPagina(path);

	}

    @FXML
    void manutencao(ActionEvent event) {
    	String path = "application/view/TelaManutencaoCarro.fxml";
    	novaPagina(path);
		/*String carroSelecionado;

    	if(table.getSelectionModel().getSelectedItem()!= null){
    		carroSelecionado = table.getSelectionModel().getSelectedItem().getPlaca();
    	}else{

	    	TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("Manutenção de Veiculo");
	    	dialog.setHeaderText("Identificando o Veiculo");
	    	dialog.setContentText("Por favor, informe a Placa do Veiculo");
	    	Optional<String> result = dialog.showAndWait();
	    	carroSelecionado = result.get();
    	}
    	Carro c = new Carro();
    	c = Carro.getCarro(carroSelecionado);
    	if(c==null){
    		Alert alert = new Alert(AlertType.ERROR);
    	   	alert.setTitle("Manutenção de veículo");
    	   	alert.setHeaderText("");
    	   	alert.setContentText("Carro não encontrado!");
    	   	alert.showAndWait();
    	}else{
    		//implementar manutenção
    	}*/


    }

	@FXML
    void removerVeiculo(ActionEvent event) {
		String carroSelecionado;

    	if(table.getSelectionModel().getSelectedItem()!= null){
    		carroSelecionado = table.getSelectionModel().getSelectedItem().getPlaca();
    	}else{

	    	TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("Remover Veiculo");
	    	dialog.setHeaderText("Identificando o Veiculo");
	    	dialog.setContentText("Por favor, informe a Placa do Veiculo");
	    	Optional<String> result = dialog.showAndWait();
	    	carroSelecionado = result.get();
    	}
    	Carro c = new Carro();
    	c = Carro.getCarro(carroSelecionado);
    	if(c==null){
    		Alert alert = new Alert(AlertType.ERROR);
    	   	alert.setTitle("Remover Carro");
    	   	alert.setHeaderText("");
    	   	alert.setContentText("Carro não encontrado!");
    	   	alert.showAndWait();
    	}else{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Remover Carro");
    		alert.setHeaderText(null);
    		alert.setContentText("Tem certeza que quer remover esse carro ?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			try {
        			Carro.removeCarro(carroSelecionado);
        			Alert alerta = new Alert(AlertType.INFORMATION);
        			alerta.setTitle("Remover Carro");
        			alerta.setHeaderText(null);
        			alerta.setContentText("Removido com sucesso!");
       				alerta.showAndWait();
        			} catch (SQLException e) {
        				e.printStackTrace();
        			}

    		}

    	}
    }

    @FXML
    void salvarDados(ActionEvent event) {
    	boolean cadastroValido = true;
    	//Carregar os dados na classe

    	if(txtCor.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Cor completo.");
    	}

    	if(txtModelo.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Modelo.");
    	}

    	if(txtMarca.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Marca.");
    	}

    	if(txtAno.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Ano.");
    	}
    	if(txtKM.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Quilometragem.");
    	}
    	if(txtPlaca.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Placa.");
    	}
    	if(comboGrupoCarro.getValue() == "N") {
    		cadastroValido = false;
    		System.out.println("Selecione o Grupo.");
    	}
    	if(cadastroValido){
	    	Carro c = new Carro(
	    			txtPlaca.getText().toUpperCase(),
	    			Integer.parseInt(txtKM.getText()),
	    			txtModelo.getText().toUpperCase(),
	    			txtMarca.getText().toUpperCase(),
	    			txtCor.getText().toUpperCase(),
	    			Integer.parseInt(txtAno.getText()),
	    			comboGrupoCarro.getValue()
	    			);
	    	if(carroEditavel==null){
		    	try {
					Carro.saveCarro(c);
					limparCampos();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Carro");
					alert.setHeaderText(null);
					alert.setContentText("Cadastro realizado com sucesso!");
					alert.showAndWait();
					Stage stage = (Stage) idSalvar.getScene().getWindow();
				 	stage.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}


	    	}else{
	    		try {
					Carro.updateCar(c);
					limparCampos();
					carroEditavel = null;
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Carro");
					alert.setHeaderText(null);
					alert.setContentText("Cadastro atualizado com sucesso!");
					alert.showAndWait();
					Stage stage = (Stage) idSalvar.getScene().getWindow();
				 	stage.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
    	}
    }

	private void limparCampos() {
		txtPlaca.setText("");
		txtKM.setText("");
		txtModelo.setText("");
		txtMarca.setText("");
		txtCor.setText("");
		txtAno.setText("");
		comboGrupoCarro.setValue("N");
	}


}