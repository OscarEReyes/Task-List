package tasklist.task.view;
import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import tasklist.task.MainApp;

public class RootLayoutController {
	    // Reference to mainApp - important to access the methods needed
	    private MainApp mainApp;

	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	    }
	    
	    /**
	     * Handle making new file
	     */
	    
	    @FXML
	    private void handleNew() {
	        mainApp.getTaskData().clear();
	        mainApp.setTaskFilePath(null);
	    }
	    
	    /**
	     * Handle opening file
	     */
	    
	    @FXML
	    private void handleOpen() {
	        FileChooser fc = new FileChooser();

	        // Set extension filter
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	        fc.getExtensionFilters().add(extFilter);

	        // Show the open file dialog stage
	        File file = fc.showOpenDialog(mainApp.getPrimaryStage());

	        if (file != null) {
	            mainApp.loadTaskDataFromFile(file);
	        }
	    }
	    
	    /**
	     * Handle Save menu.
	     */
	    
	    @FXML
	    private void handleSave() {
	        File taskFile = mainApp.getTaskFilePath();
	        if (taskFile != null) {
	            mainApp.saveTaskDataToFile(taskFile);
	        } else {
	            handleSaveAs();
	        }
	    }
	    
	    /**
	     * Prompt user to choose new filePath and name for file.
	     */
	    
	    @FXML
	    private void handleSaveAs() {
	        FileChooser fc = new FileChooser();

	        // Set extension filter to XML files
	        FileChooser.ExtensionFilter eFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
	        fc.getExtensionFilters().add(eFilter);

	        // Show the Save As file dialog stage
	        File file = fc.showSaveDialog(mainApp.getPrimaryStage());
	        
	        // Check file is not null 
	        // Make sure file is saved as XML
	        if (file != null) {
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            mainApp.saveTaskDataToFile(file);
	        }
	    }

	    /**
	     * Close the application.
	     */
	    
	    @FXML
	    private void handleExit() {
	        System.exit(0);
	    }
	}