package tasklist.task;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.stage.Stage;
import tasklist.task.model.Task;


public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	 
	private ObservableList<Task> taskData = FXCollections.observableArrayList();


	public ObservableList<Task> getTaskData() {
		return taskData;
	}
	    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task List");

        initRootLayout();

        showTaskOverview();
	}
	
	public void initRootLayout(){
		  try {
	          // Load the root layout from view/RootLayout.fxml file.
	          FXMLLoader loader = new FXMLLoader();
	          loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
	          rootLayout = (BorderPane) loader.load();
	
	          // Set scene to the scene containing the root layout.
	          Scene scene = new Scene(rootLayout);
	          primaryStage.setScene(scene);
	          primaryStage.show();
	          
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	
	}

	public void showTaskOverview() {
	    try {
	        // Load task overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/TaskOverview.fxml"));
	        AnchorPane taskOverview = (AnchorPane) loader.load();
	
	        // Center task overview in root layout
	        rootLayout.setCenter(taskOverview);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	 /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

 
	public static void main(String[] args) {
		launch(args);
	}
}
