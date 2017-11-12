package Gui;

import java.io.IOException;

import Test.DTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuiMain extends Application {

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		// TODO Auto-generated method stub
				this.primaryStage=primaryStage;
				this.primaryStage.setTitle("Grad Proj");
				
				FXMLLoader loader=new FXMLLoader();
				loader.setLocation(GuiMain.class.getResource("view/MyView.fxml"));
				mainLayout=loader.load();
				Scene scene=new Scene(mainLayout);
				primaryStage.initStyle(StageStyle.UNDECORATED);
				primaryStage.setScene(scene);
				primaryStage.show();
	}
}
