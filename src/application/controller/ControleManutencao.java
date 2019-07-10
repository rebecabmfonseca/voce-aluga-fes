package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.model.Carro;
import application.model.Manutencao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleManutencao implements Initializable{
	@FXML
    private Button btnCadastrar;

    @FXML
    private TableView<Manutencao> table;

    @FXML
    private TableColumn<?, ?> colDataInicio;

    @FXML
    private TableColumn<?, ?> colDataPrevisao;

    @FXML
    private TableColumn<?, ?> colPlaca;

    @FXML
    private TableColumn<?, ?> colDescricao;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRemover;

    @FXML
    private Button idSalvar;

    @FXML
    private Button btnCancelar;

    @FXML
    private DatePicker dataInicio;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private ComboBox<String> comboPlaca;

    @FXML
    private DatePicker dataPrevisao;

    @FXML
    private Text txtErro;

    private List<String> carrosCombo;
    private static Manutencao manutencaoEditavel;

    @FXML
    void fecharJanela(ActionEvent event) {

    }

    @FXML
    void salvarDados(ActionEvent event) {

    }


    @Override
	public void initialize(URL location, ResourceBundle resources) {
		if(location.toString().contains("TelaManutencaoCarro")){

			List<Manutencao> listaManutencao = new ArrayList<>();
			listaManutencao = Manutencao.getAll();
			if(listaManutencao!=null){
				colDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
				colDataPrevisao.setCellValueFactory(new PropertyValueFactory<>("dataPrevisao"));
				colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
				colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

				ObservableList<Manutencao> lista = FXCollections.observableArrayList(listaManutencao);
				table.setItems( lista );
			}

		}
		if(location.toString().contains("TelaCadastroManutencao")){

			List<Carro> carros = Carro.getAll();
			if(carros!=null){
				carrosCombo = new ArrayList<>();
		        for(int i=0; i<carros.size();i++){
		        	carrosCombo.add(carros.get(i).getPlaca());
		        }
		        ObservableList<String> list = FXCollections.observableArrayList(carrosCombo);

		        comboPlaca.setItems(list);
			}

			/*
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
				}*/
		}
	}


    @FXML
    void alteraManutencao(ActionEvent event) {

    }

    @FXML
    void cadastraManutencao(ActionEvent event) {
    	String path = "application/view/TelaCadastroManutencao.fxml";
		novaPagina(path);
    }

    @FXML
    void removeManutencao(ActionEvent event) {
    	int manutencaoSelecionada;

    	if(table.getSelectionModel().getSelectedItem()!= null){
    		manutencaoSelecionada = table.getSelectionModel().getSelectedItem().getIdManutencao();
    	}else{

	    	TextInputDialog dialog = new TextInputDialog();
	    	dialog.setTitle("Manutenção");
	    	dialog.setHeaderText("Identificando a manutenção");
	    	dialog.setContentText("Por favor, informe o ID da Manutenção");
	    	Optional<String> result = dialog.showAndWait();
	    	manutencaoSelecionada = Integer.parseInt(result.get());
    	}
    	Manutencao m = new Manutencao();
    	m = Manutencao.getManutencao(manutencaoSelecionada);
    	if(m==null){
    		Alert alert = new Alert(AlertType.ERROR);
    	   	alert.setTitle("Remover Manutenção");
    	   	alert.setHeaderText("");
    	   	alert.setContentText("Manutenção não encontrada!");
    	   	alert.showAndWait();
    	}else{
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Remover Manutenção");
    		alert.setHeaderText(null);
    		alert.setContentText("Tem certeza que quer essa manutenção ?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			try {
    				Manutencao.removemanutencao(manutencaoSelecionada);
        			Alert alerta = new Alert(AlertType.INFORMATION);
        			alerta.setTitle("Manutenção");
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
    void salvarManutencao(ActionEvent event){
    	boolean cadastroValido = true;
    	//Carregar os dados na classe

    	if(dataInicio.getValue() == null) {
    		cadastroValido = false;
    		txtErro.setText("Preencha com a data\n");
    	}

    	if(dataPrevisao.getValue() == null) {
    		cadastroValido = false;
    		txtErro.setText("Preencha com a data\n");
    	}

    	if(comboPlaca.getValue() == null) {
    		cadastroValido = false;
    		txtErro.setText("Preencha com a placa\n");
    	}

    	if(txtDescricao.getText().length() == 0) {
    		cadastroValido = false;
    		txtErro.setText("Preencha com a descrição");
    	}
    	if(cadastroValido){
    		Manutencao m = new Manutencao();
    		m.setDataInicio(dataInicio.getValue().toString());
    		m.setDataPrevisao(dataPrevisao.getValue().toString());
    		m.setPlaca(comboPlaca.getValue());
    		m.setDescricao(txtDescricao.getText());

	    	try {
	    		Manutencao.saveManutencao(m);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Manutenção");
				alert.setHeaderText(null);
				alert.setContentText("Manutenção realizada com sucesso!");
				alert.showAndWait();
				Stage stage = (Stage) idSalvar.getScene().getWindow();
			 	stage.close();
			} catch (SQLException e) {
				e.printStackTrace();
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
			stage.setTitle("Financeiro");
			stage.setScene(new Scene(root, 600, 600));
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
