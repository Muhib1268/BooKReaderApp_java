package controller;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.DataAccessFunction;
import model.DatabaseFunction;

public class FXMLDocumentController implements Initializable {
    
         
         ObservableList<String> genreList = FXCollections.observableArrayList("History"
                 ,"Fiction", "Classics", "Science", "Fantasy", "Sci-Fi");
    
    	 @FXML
	 private AnchorPane rootPane;

	 @FXML
	 private Button buttonAuthorList;

	 @FXML
	 private Button buttonBookList;

	 @FXML
	 private ComboBox<String> buttonGenreList;

	 @FXML
	 private Button buttonEnterNewBook;

	 @FXML
	 private AnchorPane paneBookTab;

	 @FXML
	 private Tab bookA;

	 @FXML
	 private AnchorPane paneBookA;

	 @FXML
	 private Tab bookB;

	 @FXML
	 private AnchorPane paneBookB;

	 @FXML
	 private AnchorPane paneBookEntry;

	 @FXML
	 private TextField entryName;

	 @FXML
	 private TextField entryAuthor;

	 @FXML
	 private TextField entryGenre;

	 @FXML
	 private TextField entryPublisher;

	 @FXML
	 private Button buttonEntrySave;
         
         @FXML
	 private Button buttonEntryCancel;

	 @FXML
	 private AnchorPane bookPane;

	 @FXML
	 private TableView<DatabaseFunction> book_table;
	 
         @FXML
	 private TableColumn<DatabaseFunction, Integer> book_id_function;

	 @FXML
	 private TableColumn<DatabaseFunction, String> book_name_function;

	 @FXML
	 private TableColumn<DatabaseFunction, String> book_author_function;

	 @FXML
	 private TableColumn<DatabaseFunction, String> book_genre_function;
         
         /*
         @FXML
	 private TableColumn<DatabaseFunction, String> book_publisher_function;
         */
         
	 @FXML
	 private AnchorPane genrePane;

	 @FXML
	 private TableView<?> genre_table;

	 @FXML
	 private TableColumn<?, ?> genreBook;

	 @FXML
	 private TableColumn<?, ?> genreAuthor;

	 @FXML
	 private TableColumn<?, ?> genrePublication;

	 @FXML
	 private AnchorPane paneAuthor;

	 @FXML
	 private TableView<?> author_table;

	 @FXML
	 private TableColumn<?, ?> authorColumn;

	 @FXML
	 private TableColumn<?, ?> author_bookNum;
    
	 private DataAccessFunction dao;
    
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
		 // TODO
		 dao = new DataAccessFunction();
		 
                 buttonGenreList.setItems(genreList);
                 
		 book_id_function.setCellValueFactory(new PropertyValueFactory<>("book_id"));
		 book_name_function.setCellValueFactory(new PropertyValueFactory<>("book_name"));
		 book_author_function.setCellValueFactory(new PropertyValueFactory<>("auhor_name"));
		 book_genre_function.setCellValueFactory(new PropertyValueFactory<>("genre_category"));
                 //book_publisher_function.setCellValueFactory(new PropertyValueFactory<>("book_publisher"));
		 
		 //showDialogInformation("Test Information");
        
        }
	 

	@FXML
	void authorMenuButtonAction() {

	}

	@FXML
	void bookEntryCancelFunction() {
            entryName.clear();
            entryAuthor.clear();
            entryGenre.clear();

	}

	@FXML
	void bookEntryMenuButtonAction() {

	}

	@FXML
	void bookEntrySaveFunction() {
            
            DatabaseFunction databasefunction= new DatabaseFunction();
            databasefunction.setBook_title(book_name_function.getText());
            databasefunction.setAuthor_name(book_author_function.getText());
            databasefunction.setGenre_category(book_genre_function.getText());
            //databasefunction.setBook_title(book_name_function.getText());
            try {
                dao.create(databasefunction);
                showDialogInformation("Create Function Success");
                bookEntryCancelFunction();
            } catch (Exception e) {
            	// TODO Auto-generated catch block
                showDialogError("Create Function Not Working");
            	e.printStackTrace();
            }
            
	}

	@FXML
	void catalogMenuButtonAction() {

	}

	@FXML
	void genreMenuButtonAction() {

	}
	 
	private void showDialogInformation(String information)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(information);
		 
		alert.showAndWait();
	}
	 
	private void showDialogError(String error)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(error);
		 
		alert.showAndWait();
	}
        
        //buttonGenreList.getItems().addAll("");
    
}
