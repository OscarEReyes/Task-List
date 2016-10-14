package tasklist.task;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tasklist.task.model.Task;
import tasklist.task.model.TaskListWrapper;
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
	
	/**
	 * Return task file preference
	 * Found in oS specific registry
	 * Return null if no preference found
	 */
	
	public File getTaskFilePath(){
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
				if (filePath != null){
					return new File(filePath);
				} else{
					return null;
				}
	}
	
	/**
	 * Set the file path of the currently loaded file
	 * The path will be stored in the OS specific registry
	 */
	
	public void setTaskFilePath(File file){
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			
			primaryStage.setTitle("Task List - " + file.getName());
		} else {
			prefs.remove("filePath");
			
			primaryStage.setTitle("Task List");
		}
	}
	    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Task List");
		this.primaryStage.getIcons().add(new Image("file:resources/clipboard_logo.png"));

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
	
	/**
	 * Load task data from a given file. 
	 * Replace current task data 
	 */
	
	public void loadTaskDataFromFile(File file) {
	    try {
	        JAXBContext contxt = JAXBContext.newInstance(TaskListWrapper.class);
	        Unmarshaller um = contxt.createUnmarshaller();

	        // Read XML from file
	        // Unmarshall file
	        
	        TaskListWrapper wrapper = (TaskListWrapper) um.unmarshal(file);
	        taskData.clear();
	        taskData.addAll(wrapper.getTasks());
	        // Save filePath to registry.
	        setTaskFilePath(file);
	        
	        // If any exception occurs, alert user of error.
	    } catch (Exception e) { 
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setContentText("Failed to load data from file:\n" + file.getPath());
	        alert.showAndWait();
	    }
	}

	/**
	 * Save current task data to the specified file.
	 */
	
	public void saveTaskDataToFile(File file) {
	    try {
	        JAXBContext contxt = JAXBContext.newInstance(TaskListWrapper.class);
	        Marshaller m = contxt.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Wrap task data
	        TaskListWrapper wrapper = new TaskListWrapper();
	        wrapper.setTasks(taskData);
	        
	        // Save XML File and marshall it
	        m.marshal(wrapper, file);

	        // Save file path to registry.
	        setTaskFilePath(file);
	        
	        
	        
	    } catch (Exception e) {                                      // Catch any Exception
	        Alert alert = new Alert(AlertType.ERROR);				 // Alert of error.
	        alert.setTitle("Error");
	        alert.setContentText("Failed to save data to file:\n" + file.getPath());
	        alert.showAndWait();									
	    }
	}
}
