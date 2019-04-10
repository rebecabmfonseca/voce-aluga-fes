package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/TelaInicial.fxml"));
			Pane root = fxmlLoader.load();
			Scene scene = new Scene(root,600,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Você Aluga");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
