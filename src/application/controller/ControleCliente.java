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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleCliente implements Initializable{
	@FXML
	private List<Pessoa> pessoas = new ArrayList<>();
    @FXML
    private Button btnCancelar;
	@FXML
    private Button idSalvar;
    @FXML
    private Button idCadastrar;
    @FXML
    private TextField txtNome = new TextField();
    @FXML
    private TextField txtCPF = new TextField();
    @FXML
    private TextField txtTelefone = new TextField();
    @FXML
    private DatePicker txtDataNascimento;
    @FXML
    private TextField txtEndereco = new TextField();
    @FXML
    private TextField txtEmail = new TextField();
    @FXML
    private TextField txtCNH = new TextField();
    @FXML
    private TextField txtCidade = new TextField();
    @FXML
    private TextField txtNumero = new TextField();
    @FXML
    private TextField txtCEP = new TextField();
    @FXML
    private AnchorPane telaCadastroCliente;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnRemover;
    @FXML
    private Text txtErro;
    @FXML
    private ListView<?> listView;
    @FXML
    private TableView<Cliente> tableCliente;
    @FXML
    private TableColumn<Cliente, String> nomeCol;
    @FXML
    private TableColumn<Cliente, String> CPFCol;
    @FXML
    private TableColumn<Cliente, String> CNHCol;
    @FXML
    private TableColumn<Cliente, String> EmailCol;
    @FXML
    private TableColumn<?, Boolean> selectCol;

    public static Cliente clienteEditavel = null;

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
//	private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    @FXML
    void irPaginaCadastrar() {
    	String path = "application/view/TelaCadastroCliente.fxml";
		novaPagina(path,  null);
    }
    void irPaginaEditar(Cliente cliente) {
    	String path = "application/view/TelaCadastroCliente.fxml";
		novaPagina(path,  cliente);
    }

	@Override
	public void initialize(URL location, ResourceBundle resource) {
		if(location.toString().contains("TelaCliente")){

			List<Cliente> listaCliente = new ArrayList<>();
			listaCliente = Cliente.getAll();
			nomeCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
			CPFCol.setCellValueFactory(new PropertyValueFactory<>("CPF"));
			CNHCol.setCellValueFactory(new PropertyValueFactory<>("CNH"));
			EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
			ObservableList<Cliente> lista = FXCollections.observableArrayList(listaCliente);
			System.out.println(lista);
			tableCliente.setItems( lista );
		}
		if(location.toString().contains("TelaCadastroCliente")){

			if(clienteEditavel!=null){
				txtNome.setText(clienteEditavel.getNome());
				txtTelefone.setText(clienteEditavel.getTelefone());
				String[] endereco = clienteEditavel.getEndereco().split(",");
				txtEndereco.setText(endereco[0]);
				txtNumero.setText(endereco[1]);
				txtCidade.setText(endereco[2]);
				txtCEP.setText(clienteEditavel.getCep());
				/*
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate localDate = LocalDate.parse(cliente.getDataNasc(), formatter);
				txtDataNascimento.setValue(localDate);
				*/
				txtCNH.setText(clienteEditavel.getCNH());
				txtCPF.setText(clienteEditavel.getCPF());
				txtEmail.setText(clienteEditavel.getEmail());
				}
		}

	}


	public void novaPagina(String path, Cliente cliente){

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
    	String cpfSelecionado;
    	if(tableCliente.getSelectionModel().getSelectedItem()!=null){
    		cpfSelecionado = tableCliente.getSelectionModel().getSelectedItem().getCPF();
    	}else{
    		TextInputDialog dialog = new TextInputDialog();
        	dialog.setTitle("Alterar Cliente");
        	dialog.setHeaderText("Identificando o cliente");
        	dialog.setContentText("Por favor, informe o CPF do cliente");
        	Optional<String> result = dialog.showAndWait();
        	cpfSelecionado = result.get();
    	}
    	clienteEditavel = Cliente.getCliente(cpfSelecionado);
    	if(clienteEditavel==null){
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Editar Cliente");
	    	alert.setHeaderText("");
	    	alert.setContentText("Cliente não encontrado!");
	    	alert.showAndWait();
    	}else{
    	   	irPaginaEditar(clienteEditavel);
    	}
    }

    @FXML
    void removerCliente(ActionEvent event)  {
    	String cpfSelecionado;

    	if(tableCliente.getSelectionModel().getSelectedItem()!= null){
    		cpfSelecionado = tableCliente.getSelectionModel().getSelectedItem().getCPF();
    	}else{
    		TextInputDialog dialog = new TextInputDialog();
        	dialog.setTitle("Remover Cliente");
        	dialog.setHeaderText("Identificando o cliente");
        	dialog.setContentText("Por favor, informe o CPF do cliente");
        	Optional<String> result = dialog.showAndWait();
        	cpfSelecionado = result.get();
    	}
   	    Cliente cliente = new Cliente();
   	    cliente = Cliente.getCliente(cpfSelecionado);
    	if(cliente==null){
    	   	Alert alert = new Alert(AlertType.ERROR);
    	   	alert.setTitle("Remover Cliente");
    	   	alert.setHeaderText("");
    	   	alert.setContentText("Cliente não encontrado!");
    	   	alert.showAndWait();
    	}else{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Remover Cliente");
    		alert.setHeaderText(null);
    		alert.setContentText("Tem certeza que quer remover esse cliente ?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			try {
        			Cliente.removeCliente(cpfSelecionado);
        			Alert alerta = new Alert(AlertType.INFORMATION);
        			alerta.setTitle("Remover Cliente");
        			alerta.setHeaderText(null);
        			alerta.setContentText("Removido com sucesso!");
       				alerta.showAndWait();
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

    	}

    	if(txtCEP.getText().length() != 9) {
    		cadastroValido = false;
    		System.out.println("CEP invalido!");
    		txtErro.setText(txtErro.getText()+"\nCEP invalido!");
    	}*/

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
		    			Integer.parseInt(txtNumero.getText().replaceAll(" ", "")), //removendo o espaço, caso haja
		    			removerValidacao(txtCEP.getText())
		    			);
		    	if(clienteEditavel == null){ //significa que não tem cliente pra editar, então é a opção Cadastrar Novo Cliente
			    	//System.out.println(c.toString());
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
		         }else{
		        	 try {
							Cliente.updateClient(c);
							limparCampos();
							clienteEditavel = null;
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Dados do Cliente");
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

    public String removerValidacao(String campo){
    	String novoCampo = "";
    	novoCampo = campo.replace("(", "");
    	novoCampo = novoCampo.replace(")", "");
    	novoCampo = novoCampo.replace("-", "");
    	novoCampo = novoCampo.replace(".", "");
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

	public boolean validarCPF(String cpf)
	{
		String CPF = cpf.replaceAll("\\D+", "");
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
