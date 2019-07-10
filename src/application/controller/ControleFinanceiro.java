package application.controller;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Aluguel;
import application.model.Carro;
import application.model.Financeiro;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleFinanceiro implements Initializable{
	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnRemover;

	@FXML
    private ComboBox<String> comboReserva;

	@FXML
    private ComboBox<String> comboFormaPagamento;

    @FXML
    private Button idSalvar;

    @FXML
    private Text txtErro;
    @FXML
    private TextField txtValor;

    @FXML
    private Button btnCancelar;
    private List<String> formaPagamento;

    @FXML
    private TableColumn<?, ?> colReserva;

    @FXML
    private TableColumn<?, ?> colFormaPagamento;

    @FXML
    private TableColumn<?, ?> colValor;

    @FXML
    private TableView<Financeiro> table;

    public static Financeiro financeiroEditavel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(location.toString().contains("TelaFinanceiro")){

			List<Financeiro> listaFinanceiro = new ArrayList<>();
			listaFinanceiro = Financeiro.getAll();

			if(listaFinanceiro != null){

				colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("formaPagamento"));
				colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
				colReserva.setCellValueFactory(new PropertyValueFactory<>("idFinanceiro"));

				ObservableList<Financeiro> lista = FXCollections.observableArrayList(listaFinanceiro);
				table.setItems( lista );
			}



		}
		if(location.toString().contains("TelaCadastroFinanceiro")){
			formaPagamento = new ArrayList<>();
			formaPagamento.add("Dinheiro");
			formaPagamento.add("Cartão de crédito");
			formaPagamento.add("Cartão de débito");

	        ObservableList<String> list = FXCollections.observableArrayList(formaPagamento);
	        comboFormaPagamento.setItems(list);

	        List<Aluguel> reservas = Aluguel.getAll();
	        List<String> reservasCombo = new ArrayList<>();
	        for(int i=0; i<reservas.size(); i++){
	        	reservasCombo.add(reservas.get(i).getPlaca()+" - "+reservas.get(i).getCNH() +
	        			" - "+ reservas.get(i).getDataRetirada());
	        }
	        ObservableList<String> list2 = FXCollections.observableArrayList(reservasCombo);
	        comboReserva.setItems(list2);

	        if(financeiroEditavel!=null){
	        	txtValor.setText(Double.toString(financeiroEditavel.getValor()));
	        	comboFormaPagamento.setValue(financeiroEditavel.getFormaPagamento());

	        }


		}

	}

	  @FXML
	    void salvar(ActionEvent event) {
	    	boolean cadastroValido = true;
	    	//Carregar os dados na classe

	    	if(comboReserva.getValue() == null) {
	    		cadastroValido = false;
	    		txtErro.setText("Preencha com a Reserva");
	    	}
	    	if(comboFormaPagamento.getValue() == null){
	    		cadastroValido = false;
	    		txtErro.setText("Preencha com a Forma de Pagamento");
	    	}
	    	if(txtValor.getText() == null){
	    		cadastroValido = false;
	    		txtErro.setText("Preencha com o valor da Reserva");
	    	}
	    	if(cadastroValido){
	    		Financeiro financeiro = new Financeiro();
	    		financeiro.setFormaPagamento(comboFormaPagamento.getValue());
	    		financeiro.setValor(Double.parseDouble(txtValor.getText()));
	    		String partes[] = comboReserva.getValue().split(" - ");
	    		financeiro.setIdReserva(Aluguel.getReserva(partes[1],partes[0]));
	    		try{
	    			Financeiro.saveFinanceiro(financeiro);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Financeiro");
					alert.setHeaderText(null);
					alert.setContentText("Cadastro realizado com sucesso!");
					alert.showAndWait();
					Stage stage = (Stage) comboFormaPagamento.getScene().getWindow();
				 	stage.close();

	    		} catch (SQLException e) {
					e.printStackTrace();
				}

	    	}


	    }


    @FXML
    void estornarPagamento(ActionEvent event) {
    	int financeiroSelecionado;

    	if(table.getSelectionModel().getSelectedItem()!= null){
    		financeiroSelecionado = table.getSelectionModel().getSelectedItem().getIdFinanceiro();
    	}else{

	    	TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("Estornar Pagamento");
	    	dialog.setHeaderText("Identificando o Pagamento");
	    	dialog.setContentText("Por favor, informe o Id do pagamento");
	    	Optional<String> result = dialog.showAndWait();
	    	financeiroSelecionado = Integer.parseInt(result.get());
    	}
    	Financeiro f = new Financeiro();
    	f = Financeiro.getFinanceiro(financeiroSelecionado);
    	if(f==null){
    		Alert alert = new Alert(AlertType.ERROR);
    	   	alert.setTitle("Financeiro");
    	   	alert.setHeaderText("");
    	   	alert.setContentText("Forma de pagamento não encontrada!");
    	   	alert.showAndWait();
    	}else{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Estornar Pagamento");
    		alert.setHeaderText(null);
    		alert.setContentText("Tem certeza que quer esrtornar esse pagamento ?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			try {
        			Financeiro.estorna(financeiroSelecionado);
        			Alert alerta = new Alert(AlertType.INFORMATION);
        			alerta.setTitle("Estorno");
        			alerta.setHeaderText(null);
        			alerta.setContentText("Estornado com sucesso!");
       				alerta.showAndWait();
        			} catch (SQLException e) {
        				e.printStackTrace();
        			}

    		}

    	}

    }

	@FXML
    void cadastrarFInanceiro(ActionEvent event) {
		String path = "application/view/TelaCadastroFinanceiro.fxml";
		novaPagina(path);
    }

	public void novaPagina(String path){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(path));
			Pane root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle("Financeiro");
			stage.setScene(new Scene(root, 600, 600));
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
