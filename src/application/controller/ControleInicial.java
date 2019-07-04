package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControleInicial implements Initializable{
	private String title;
	private String path;
	public void irPaginaCliente(ActionEvent a) throws IOException{
		path = "application/view/TelaCliente.fxml";
		title = "Cliente";
		novaPagina(path, title);

	}

	public void irPaginaVeiculos(ActionEvent a) throws IOException{
		path = "application/view/TelaVeiculo.fxml";
		title = "Veículos";
		novaPagina(path, title);

	}
    public void irPaginaAluguel(ActionEvent event) throws IOException {
		path = "application/view/TelaAluguel.fxml";
		title = "Aluguel";
		novaPagina(path, title);
    }

    public void irPaginaFinanceiro(ActionEvent event) throws IOException {
		path = "application/view/TelaFinanceiro.fxml";
		title = "Financeiro";
		novaPagina(path, title);
    }


	public void novaPagina(String path, String title){
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource(path));
			Pane root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOpacity(1);
			stage.setTitle(title);
			stage.setScene(new Scene(root, 600, 600));
			stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
