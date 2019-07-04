package application.controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Aluguel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
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
    private Button btnCancelar;
    private List<String> formaPagamento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(location.toString().contains("TelaFinanceiro")){


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
	        	reservasCombo.add(reservas.get(i).getPlaca()+" - "+reservas.get(i).getCNH());
	        }
	        ObservableList<String> list2 = FXCollections.observableArrayList(reservasCombo);
	        comboReserva.setItems(list2);


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
