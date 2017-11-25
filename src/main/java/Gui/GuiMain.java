package Gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import Answers.GreetingsAnswers;
import Domains.Domains;
import Questions.GreetingsQuestions;
import Questions.SportQuestions;
import Search.SearchQuery;
import Test.DTest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GuiMain extends Application{

	private static Stage primaryStage;
	private static BorderPane mainLayout;
	
	private static boolean isSport=true;
	private static boolean isFactoid=false;
	
	@FXML
	private TextField myText;
	
	@FXML
	private TextArea txtAnswer;
	
	private static double xOffset = 0;
    private static double yOffset = 0;
    private static double initialMinX;
	private static double initialMinY;
	private static double initialWidth;
	private static double initialHeight;
	private static boolean isWindowMaximized = false;
	
	private List<Label> messages=new ArrayList<Label>();
	private static int index=0;
	
	private Screen screen = Screen.getPrimary();
	private Rectangle2D bounds = screen.getVisualBounds();
	
	//From Question and Answer Part
	private static String[] greetingsQuestions;
	private static String[] greetingsAnswers;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		// TODO Auto-generated method stub
				this.primaryStage=primaryStage;
				this.primaryStage.setTitle("Grad Proj");
				
				greetingsQuestions=GreetingsQuestions.getGreetings();
				greetingsAnswers=GreetingsAnswers.getGreetings();
				
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
	public void onEnter(ActionEvent ae) throws IOException{
	   String question=myText.getText();
	   String[] domainQuestions=Domains.domainQuestions;
	   
	   messages.add(new Label(question));
	   messages.get(index).setAlignment(Pos.CENTER_LEFT);
	   index++;
	   
	   Thread thread=new Thread(new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				if(isSport) {
					if(ArrayUtils.contains(greetingsQuestions, question)) {
						Random rnd=new Random();
						int rn=rnd.nextInt(greetingsAnswers.length);
						//txtAnswer.setText(greetingsAnswers[rn]);
						messages.add(new Label(greetingsAnswers[rn]));
						messages.get(index).setAlignment(Pos.CENTER_RIGHT);
						index++;
					}else if(ArrayUtils.contains(domainQuestions,question)){
						//txtAnswer.setText(SportQuestions.getJSON());
						messages.add(new Label(SportQuestions.getJSON()));
						messages.get(index).setAlignment(Pos.CENTER_RIGHT);
						index++;
					}
				}
				if(isFactoid) {
					txtAnswer.setText(SearchQuery.getParagraphsFromPages(question).toString());
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	   thread.start();
	}
	
	@FXML
	public void factoidButtonClicked(MouseEvent event) {
		isSport=false;
		isFactoid=true;
	}
	
	@FXML
	public void sportButtonClicked(MouseEvent event) {
		isFactoid=false;
		isSport=true;
		
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
