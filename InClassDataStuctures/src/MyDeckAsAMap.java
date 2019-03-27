import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyDeckAsAMap extends Application {

	Map<String,ImageView>deck = new HashMap<String,ImageView>();
	int num = 0;
	
	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Setup UI **********
		Button next = new Button("Next Card");
		HBox disp = new HBox(20);
		disp.getChildren().add(next);
		next.setOnMouseClicked((new EventHandler<MouseEvent>() {
	          public void handle(MouseEvent arg0) {
	              disp.getChildren().add(deck.get("c"+num++));
	          }}));
		Scene window = new Scene(disp, 900, 600);
		primaryStage.setScene(window);
		//******************
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("src/cards"), "*.png")) {//get everything in the directory
	           for (Path entry: stream) {// for each entry in the directory do 
	        	   System.out.println("file:"+entry);
	        	   ImageView iv =new ImageView("file:"+entry) ;
	        	   iv.setFitHeight(200);
	        	   iv.setFitWidth(120);
	        	   String key = "c"+num++;
	        	   
	               deck.put(key, iv); //add to Stack
	           }
	       } catch (DirectoryIteratorException ex) {
	           // I/O error encounted during the iteration, the cause is an IOException
	           throw ex.getCause();
       }
		num =0;
	   primaryStage.show();

  }
	
	
}
