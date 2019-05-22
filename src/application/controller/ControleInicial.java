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
	public void irPaginaCliente(ActionEvent a) throws IOException{
		//System.out.println("Apertou o botão");
		String path = "application/view/TelaCliente.fxml";
		novaPagina(path);

	}

	public void irPaginaVeiculos(ActionEvent a) throws IOException{
		System.out.println("Apertou o botão");
		String path = "application/view/TelaVeiculo.fxml";
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
			stage.setTitle("Cliente");
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
