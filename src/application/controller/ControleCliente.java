package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Cliente;
import application.model.Pessoa;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleCliente implements Initializable{
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
    private TextField txtNome;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtTelefone;
    @FXML
    private DatePicker txtDataNascimento;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCNH;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCEP;
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
//	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
    @FXML
    void irPaginaCadastrar(ActionEvent event) {
    	String path = "application/view/TelaCadastroCliente.fxml";
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
		tff.setTf(txtTelefone);
		tff.formatter();
	}
	public void mascaraCPF(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###.###.###-##");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCPF);
		tff.formatter();
	}
	@FXML
	public void mascaraCEP(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("#####-###");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCEP);
		tff.formatter();
	}

    @FXML
    void alterarCliente(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Alterar Cliente");
    	dialog.setHeaderText("Identificando o cliente");
    	dialog.setContentText("Por favor, informe o CPF do cliente");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    System.out.println("Seu CPF é: " + result.get());
//    	    Clinte cliente;
    	    Cliente.getCliente(result.get());
    	    //ToDo
    	    //Buscar o cliente
        	//Redirecionar para a página de Cadastro
    		//Com os dados do cliente para edição
    	}



    }
    @FXML
    void removerCliente(ActionEvent event) {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Remover Cliente");
    	dialog.setHeaderText("Identificando o cliente");
    	dialog.setContentText("Por favor, informe o CPF do cliente");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    System.out.println("Seu CPF é: " + result.get());
    	    //ToDo
    	    //Buscar o cliente
        	//Perguntar se quer remover
    		//Remover
    	}
    }

    @FXML
    void salvarDados(ActionEvent event) {
    	//ToDo
    	
    	//Fazer a validação dos dados
    	//Carregar os dados na classe
    	LocalDate date = txtDataNascimento.getValue();
    	if(!validarCPF(txtCPF.getText())) {
    		//cpf invalido
    	} else if(!validarCNH(txtCNH.getText())) {
    		//cnh invalido
    	} else {
    		//ToDo: campo para complemento do endereco
	    	Cliente c = new Cliente(
	    			txtCPF.getText(),
	    			txtNome.getText(),
	    			txtCNH.getText(),
	    			txtTelefone.getText(),
	    			txtEmail.getText(),
	    			date.getDayOfMonth(),
	    			date.getMonthValue(),
	    			date.getYear(),
	    			txtEndereco.getText(),
	    			txtCidade.getText(),
	    			Integer.parseInt(txtNumero.getText()),
	    			txtCEP.getText()
	    			);
	    	
	    	System.out.println(c.toString());
	    	/*System.out.println(txtDataNascimento.getValue());
	    	System.out.println(date.getDayOfMonth());
	    	System.out.println(date.getMonthValue());
	    	System.out.println(date.getYear());*/
    	}

    }


	public void carregarClientes(){
		/*Pessoa c1 = new Pessoa("123.456.789-12", "Rebeca", (long) 1140028922, "rebeca@iarru.com", 1, 2, 3, "Rua", "Cidade", 420, "12345-12");
		Pessoa c2 = new Pessoa("098.765.432-10", "Joao", (long) 1234512345, "joao@rotmeio.com", 2, 0, 3, "Cal�ada", "Cidade", 69, "21543-21", 13);
		pessoas.add(c1);
		pessoas.add(c2);
		obsPessoa = FXCollections.observableArrayList(pessoas);
		lvCliente.setItems(obsPessoa);*/

	}
	
	private boolean validarCPF(String CPF)
	{
		/*//talvez retornar string com tipo de invalidez em caso de invalidez (no caso talvez tenha que usar exception)
		if (CPF.equals("00000000000") ||
	            CPF.equals("11111111111") ||
	            CPF.equals("22222222222") || CPF.equals("33333333333") ||
	            CPF.equals("44444444444") || CPF.equals("55555555555") ||
	            CPF.equals("66666666666") || CPF.equals("77777777777") ||
	            CPF.equals("88888888888") || CPF.equals("99999999999") || CPF.length() != 11){
			
			return false;
		} else {
			Integer digito1 = calcularDigito(CPF.substring(0,9), pesoCPF);
			Integer digito2 = calcularDigito(CPF.substring(0,9) + digito1, pesoCPF);
			return CPF.equals(CPF.substring(0,9) + digito1.toString() + digito2.toString());
		}*/
		return true;
	}

   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }
   
   private boolean validarCNH(String CNH) {
	   //ToDo
	   return true;
   }

}
