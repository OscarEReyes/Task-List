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
import javafx.stage.Modality;
import javafx.stage.Stage;
import tasklist.task.model.Task;
import tasklist.task.view.TaskEditDialogController;
import tasklist.task.view.TaskOverviewController;


public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	
	private ObservableList<Task> taskData = FXCollections.observableArrayList();


	public MainApp(){
	}
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
	          primaryStage.setResizable(false);
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
	        
	        // Give mainApp access to the controller
	        TaskOverviewController controller = loader.getController();
	        controller.setMainApp(this);
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean showTaskEditDialog(Task task) {
	    try {
	        // Load fxml file and create new stage for the TaskEditDialog
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create stage for taskEditDialog.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Task");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set task on controller.
	        TaskEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setTask(task);

	        // Show Edit dialog and wait for a user response.
	        dialogStage.showAndWait();
	        
	        return controller.OkIsClicked();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    public Stage getPrimaryStage() {
        return primaryStage;
    }

 
	public static void main(String[] args) {
		launch(args);
	}
}
