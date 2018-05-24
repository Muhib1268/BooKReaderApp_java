package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.DataAccessFunction;
import model.DatabaseFunction;

public class NewBookViewController implements Initializable {
    
    	@FXML
	private AnchorPane rootPane;

	@FXML
	private Button buttonEnterNewBook;
         
         
        @FXML
        private Button buttonGenreList;

        @FXML
        private Button buttonReadBooks;
        
        
        @FXML
        private Circle buttonHome;
        
        
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
		 
		//showDialogInformation("Test Information");
        
        }
	 

	@FXML
	void authorMenuButtonAction(ActionEvent event) throws IOException
        {
            Parent authorViewParent = FXMLLoader.load(getClass().getResource("/view/AuthorView.fxml"));
            Scene authorViewScene = new Scene(authorViewParent);
            
            Stage authorViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            authorViewWindow.setScene(authorViewScene);
            authorViewWindow.show();  
	}

	@FXML
	void bookEntryCancelFunction() {
            entryName.clear();
            entryAuthor.clear();
            entryGenre.clear();
            entryPublisher.clear();
	}


	@FXML
	void bookEntrySaveFunction() {
            
            DatabaseFunction databasefunction= new DatabaseFunction();
            
            databasefunction.setBook_title(entryName.getText());
            databasefunction.setAuthor_name(entryAuthor.getText());
            databasefunction.setGenre_category(entryGenre.getText());
            databasefunction.setPublisher_name(entryPublisher.getText());
            
            //getText test code
            //System.out.println(entryName.getText());
            //System.out.println(entryAuthor.getText());
            //System.out.println(entryGenre.getText());
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
	void catalogMenuButtonAction(ActionEvent event) throws IOException
        {
            
            Parent bookListViewParent = FXMLLoader.load(getClass().getResource("/view/BookListView.fxml"));
            Scene bookListViewScene = new Scene(bookListViewParent);
            
            Stage bookListViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            bookListViewWindow.setScene(bookListViewScene);
            bookListViewWindow.show();  

	}

	@FXML
	void genreMenuButtonAction(ActionEvent event) throws IOException
        {
            
            Parent genreViewParent = FXMLLoader.load(getClass().getResource("/view/GenreView.fxml"));
            Scene genreViewScene = new Scene(genreViewParent);
            
            Stage genreViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            genreViewWindow.setScene(genreViewScene);
            genreViewWindow.show();  

	}
        
        @FXML
        void bookCatalogMenuButtonAction(ActionEvent event) throws IOException
        {
            
            Parent bookListViewParent = FXMLLoader.load(getClass().getResource("/view/BookListView.fxml"));
            Scene bookListViewScene = new Scene(bookListViewParent);
            
            Stage bookListViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            bookListViewWindow.setScene(bookListViewScene);
            bookListViewWindow.show(); 

        }
        
        @FXML
        void onButtonHomeClicked(MouseEvent event) throws IOException
        {
            
            Parent homeViewParent = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            Scene homeViewScene = new Scene(homeViewParent);
            
            Stage homeViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            homeViewWindow.setScene(homeViewScene);
            homeViewWindow.show(); 

        }

        @FXML
        void readBookMenuButtonAction(ActionEvent event) throws IOException
        {
            
            Parent bookReadingViewParent = FXMLLoader.load(getClass().getResource("/view/BookReadingView.fxml"));
            Scene bookReadingViewScene = new Scene(bookReadingViewParent);
            
            Stage bookReadingViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            bookReadingViewWindow.setScene(bookReadingViewScene);
            bookReadingViewWindow.show(); 

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
    
}
