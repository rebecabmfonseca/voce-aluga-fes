package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Carro;
import application.model.Cliente;
import application.model.Pessoa;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleVeiculo implements Initializable{
	@FXML
	private ListView<Pessoa> lvCliente;
	private List<Pessoa> pessoas = new ArrayList<>();
	private ObservableList<Pessoa> obsPessoa;
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

//	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
//	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @FXML
    void irPaginaCadastrar(ActionEvent event) {
    	String path = "application/view/TelaCadastroVeiculo.fxml";
		novaPagina(path);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void novaPagina(String path){
		try {		
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(path));
			Pane root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Clientes");
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
	public void mascaraTelefone(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("(##)#####-####");
		tff.setCaracteresValidos("0123456789");
//		tff.setTf(txtTelefone);
		tff.formatter();
	}
	public void mascaraCPF(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
//		tff.setTf(txtCPF);
		tff.formatter();
	}
	public void mascaraCNH(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###########");
		tff.setCaracteresValidos("0123456789");
//		tff.setTf(txtCNH);
		tff.formatter();
	}
	@FXML
	public void mascaraCEP(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#####-###");
		tff.setCaracteresValidos("0123456789");
//		tff.setTf(txtCEP);
		tff.formatter();
	}

    @FXML
    void alterarVeiculo(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Alterar Carro");
    	dialog.setHeaderText("Identificando o carro");
    	dialog.setContentText("Por favor, informe a Placa do Veiculo");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    System.out.println("Sua Placa é: " + result.get());
    	    System.out.println(Carro.getCarro(result.get()));
    	}



    }
    @FXML
    void removerVeiculo(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Remover Veiculo");
    	dialog.setHeaderText("Identificando o Veiculo");
    	dialog.setContentText("Por favor, informe a Placa do Veiculo");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    System.out.println("Sua Placa éa: " + result.get());
    	    try {
				Carro.removeCarro(result.get());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    //ToDo
    	    //Buscar o cliente
        	//Perguntar se quer remover
    		//Remover
    	}
    }

    @FXML
    void salvarDados(ActionEvent event) {
    	boolean cadastroValido = true;    	

    	//Carregar os dados na classe
    	
//    	if(!validarCPF(txtCPF.getText())) {
//    		//cpf invalido
//    		cadastroValido = false;
//    		System.out.println("CPF invalido!");
//    	} 
    	
//    	if(!validarCNH(txtCNH.getText())) {
//    		//cnh invalido
//    		cadastroValido = false;
//    		System.out.println("CNH invalido!");
//    	}
//    	
//    	if(txtCEP.getText().length() != 9) {
//    		cadastroValido = false;
//    		System.out.println("CEP invalido!");
//    	}
//    	
    	if(txtCor.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Nome completo.");
    	}
    	
    	if(txtModelo.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Telefone.");
    	}
    	
    	if(txtMarca.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Email.");
    	}
    	
//    	if(txtAno.getValue() == null) {
//    		cadastroValido = false;
//    		System.out.println("Preencha o campo Data de nascimento.");
//    	}
    	
//    	if(txtKM.getValue() == null) {
//    		cadastroValido = false;
//    		System.out.println("Preencha o campo Endere�o.");
//    	}
    	
    	if(txtPlaca.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Cidade.");
    	}
    	
//    	if(txtNumero.getText().length() == 0) {
//    		cadastroValido = false;
//    		System.out.println("Preencha o campo Numero.");
//    	}
//    	
    	if(cadastroValido){
//    		LocalDate date = txtDataNascimento.getValue();
	    	Carro c = new Carro(
	    			txtPlaca.getText(),
	    			Integer.parseInt(txtKM.getText()),
	    			txtModelo.getText(),
	    			txtMarca.getText(),
	    			txtCor.getText(),
	    			Integer.parseInt(txtAno.getText())
	    			);
	    	
	    	System.out.println(c.toString());
	    	try {
				Carro.saveCarro(c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}

    }


	public void carregarClientes(){
		/*Pessoa c1 = new Pessoa("123.456.789-12", "Rebeca", "1140028922", "rebeca@iarru.com", 1, 2, 3, "Rua", "Cidade", 420, "12345-12");
		Pessoa c2 = new Pessoa("098.765.432-10", "Joao", "1234512345", "joao@rotmeio.com", 2, 0, 3, "Calcada", "Cidade", 69, "21543-21", 13);
		pessoas.add(c1);
		pessoas.add(c2);
		obsPessoa = FXCollections.observableArrayList(pessoas);
		lvCliente.setItems(obsPessoa);*/
	}
	
   
	public static boolean validarCNH(String cnh) {
		if(cnh.length() == 0) return false;
		
		char char1 = cnh.charAt(0);

		if (cnh.replaceAll("\\D+", "").length() != 11 || String.format("%0" + 11 + "d", 0).replace('0', char1).equals(cnh)) {
			return false;
		}

		long v = 0, j = 9;

		for (int i = 0; i < 9; ++i, --j) {
			v += ((cnh.charAt(i) - 48) * j);
		}

		long dsc = 0, vl1 = v % 11;

		if (vl1 >= 10) {
			vl1 = 0;
			dsc = 2;
		}
		
		v = 0;
		j = 1;
		for (int i = 0; i < 9; ++i, ++j) {

			v += ((cnh.charAt(i) - 48) * j);
		}

		long x = v % 11;
		long vl2 = (x >= 10) ? 0 : x - dsc;

		return (String.valueOf(vl1) + String.valueOf(vl2)).equals(cnh.substring(cnh.length() - 2));
	}

}
