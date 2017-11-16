package Gui;

import java.io.IOException;

import Test.DTest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuiMain extends Application {

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	private static double xOffset = 0;
    private static double yOffset = 0;
    private static double initialMinX;
	private static double initialMinY;
	private static double initialWidth;
	private static double initialHeight;
	private static boolean isWindowMaximized = false;
	
	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	
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
	
	@FXML
	public void closeWindowButton(MouseEvent event){
		primaryStage.close();
	}
	
	@FXML
	public void minimizeWindowButton(MouseEvent event) {
		primaryStage.setIconified(true);
	}
	
	@FXML
	public void maximizeWindowButton(MouseEvent event) {
		if(isWindowMaximized)
		{
			primaryStage.setX(initialMinX);
			primaryStage.setY(initialMinY);
			primaryStage.setWidth(initialWidth);
			primaryStage.setHeight(initialHeight);
		}
		else if(!isWindowMaximized)
		{
			initialMinX = bounds.getMinX();
			initialMinY = bounds.getMinY();
			initialWidth = bounds.getWidth();
			initialHeight = bounds.getHeight();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			isWindowMaximized = true;
		}
	}
	
	@FXML
	public void getMouseCoordinates(MouseEvent event){
		xOffset = event.getSceneX();
        yOffset = event.getSceneY();
	}
	
	@FXML
	public void dragTheWindow(MouseEvent event){
		primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
	}
}
