package application.controller;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.text.Text;
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
    @FXML
    private Text txtErro;

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
//	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @FXML
    void irPaginaCadastrar(ActionEvent event) {
    	String path = "application/view/TelaCadastroCliente.fxml";
		novaPagina(path);
		carregarClientes();
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
	public void mascaraCNH(){
		TextFieldFormatter tff = new TextFieldFormatter();
		tff.setMask("###########");
		tff.setCaracteresValidos("0123456789");
		tff.setTf(txtCNH);
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
    	    Cliente cliente = new Cliente();
    	    cliente = Cliente.getCliente(result.get());
    	    if(cliente==null){
    	    	Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Editar Cliente");
    	    	alert.setHeaderText("");
    	    	alert.setContentText("Cliente não encontrado!");
    	    	alert.showAndWait();
    	    }else{
    	    	//ToDo
            	//Redirecionar para a página de Cadastro
        		//Com os dados do cliente para edição
    	    }


    	}



    }
    @FXML
    void removerCliente(ActionEvent event)  {
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Remover Cliente");
    	dialog.setHeaderText("Identificando o cliente");
    	dialog.setContentText("Por favor, informe o CPF do cliente");

    	Optional<String> result = dialog.showAndWait();
    	if (result.isPresent()){
    	    System.out.println("Seu CPF é: " + result.get());
    	    Cliente cliente = new Cliente();
    	    cliente = Cliente.getCliente(result.get());
    	    if(cliente==null){
    	    	Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Remover Cliente");
    	    	alert.setHeaderText("");
    	    	alert.setContentText("Cliente não encontrado!");
    	    	alert.showAndWait();
    	    }else{
    	    	try {
    				Cliente.removeCliente(result.get());
    				Alert alert = new Alert(AlertType.INFORMATION);
    				alert.setTitle("Remover Cliente");
    				alert.setHeaderText(null);
    				alert.setContentText("Removido com sucesso!");

    				alert.showAndWait();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	    }

    	}
    }

    @FXML
    void salvarDados(ActionEvent event) {
    	boolean cadastroValido = true;
    	txtErro.setText("");


    	//Carregar os dados na classe

    	/*if(!validarCPF(txtCPF.getText())) {
    		//cpf invalido
    		cadastroValido = false;
    		System.out.println("CPF invalido!");
    		txtErro.setText(txtErro.getText()+"\nCPF invalido!");

    	}

    	if(!validarCNH(txtCNH.getText())) {
    		//cnh invalido
    		cadastroValido = false;
    		System.out.println("CNH invalido!");
    		txtErro.setText(txtErro.getText()+"\nCNH invalido!");

    	}*/

    	if(txtCEP.getText().length() != 9) {
    		cadastroValido = false;
    		System.out.println("CEP invalido!");
    		txtErro.setText(txtErro.getText()+"\nCEP invalido!");
    	}

    	if(txtNome.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Nome completo.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo Nome completo.");
    	}

    	if(txtTelefone.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Telefone.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo Telefone.");
    	}

    	if(txtEmail.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Email.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo Email");
    	}

    	if(txtDataNascimento.getValue() == null) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Data de nascimento.");
    		txtErro.setText(txtErro.getText()+"\nPreencha a data de Nascimento");
    	}

    	if(txtEndereco.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Endereço.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo Endereço");
    	}

    	if(txtCidade.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Cidade.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo cidade");
    	}

    	if(txtNumero.getText().length() == 0) {
    		cadastroValido = false;
    		System.out.println("Preencha o campo Numero.");
    		txtErro.setText(txtErro.getText()+"\nPreencha o campo Numero");
    	}

    	if(cadastroValido){
    		LocalDate date = txtDataNascimento.getValue();
	    	Cliente c = new Cliente(
	    			removerValidacao(txtCPF.getText()),
	    			txtNome.getText().toUpperCase(),
	    			txtCNH.getText(),
	    			removerValidacao(txtTelefone.getText()),
	    			txtEmail.getText(),
	    			date.getDayOfMonth(),
	    			date.getMonthValue(),
	    			date.getYear(),
	    			txtEndereco.getText().toUpperCase(),
	    			txtCidade.getText().toUpperCase(),
	    			Integer.parseInt(txtNumero.getText()),
	    			removerValidacao(txtCEP.getText())
	    			);

	    	System.out.println(c.toString());
	    	try {
				Cliente.saveClient(c);
				limparCampos();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Dados do Cliente");
				alert.setHeaderText(null);
				alert.setContentText("Cadastro realizado com sucesso!");
				alert.showAndWait();
				Stage stage = (Stage) idSalvar.getScene().getWindow();
			 	stage.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

    }

    public String removerValidacao(String campo){
    	String novoCampo = "";
    	novoCampo = campo.replace("(", "");
    	novoCampo = novoCampo.replace(")", "");
    	novoCampo = novoCampo.replace("-", "");
    	novoCampo = novoCampo.replace(".", "");
    	System.out.println(novoCampo);
    	return novoCampo;
    }

    public void limparCampos(){
    	txtCPF.setText("");
		txtNome.setText("");
		txtCNH.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtCidade.setText("");
		txtNumero.setText("");
		txtCEP.setText("");

    }


	public void carregarClientes(){
		/*Pessoa c1 = new Pessoa("123.456.789-12", "Rebeca", "1140028922", "rebeca@iarru.com", 1, 2, 3, "Rua", "Cidade", 420, "12345-12");
		Pessoa c2 = new Pessoa("098.765.432-10", "Joao", "1234512345", "joao@rotmeio.com", 2, 0, 3, "Calcada", "Cidade", 69, "21543-21", 13);
		pessoas.add(c1);
		pessoas.add(c2);
		obsPessoa = FXCollections.observableArrayList(pessoas);
		lvCliente.setItems(obsPessoa);*/
		System.out.println("entrou no carregaCliente");
		System.out.println(obsPessoa);
		obsPessoa = FXCollections.observableArrayList(Cliente.getAll());

		lvCliente.setItems(obsPessoa);
	}

	public boolean validarCPF(String cpf)
	{
		String CPF = cpf.replaceAll("\\D+", "");
		//talvez retornar string com tipo de invalidez em caso de invalidez (no caso talvez tenha que usar exception)
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
		}
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
