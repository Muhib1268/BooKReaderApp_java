package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataAccessFunction;
import model.DatabaseFunction;


public class BookEditViewController implements Initializable {

 
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonAuthorList;

    @FXML
    private Button buttonBookList;

    @FXML
    private Button buttonGenreList;

    @FXML
    private Button buttonReadBooks;

    @FXML
    private Button buttonEnterNewBook;

    @FXML
    private AnchorPane paneBookEntry;

    @FXML
    private TextField editBookName;

    @FXML
    private TextField editBookAuthor;

    @FXML
    private TextField editBookGenre;

    @FXML
    private Button buttonEditSave;

    @FXML
    private Button buttonEditCancel;

    @FXML
    private TextField editBookPublisher;
    
    @FXML
    private Label bookID;
    
    
    private DataAccessFunction editDBdao;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        editDBdao = new DataAccessFunction();
    } 
    
    private DatabaseFunction DBFunction; 
    
    private String selected_book_name;
    
    private int selected_book_ID;
            
    public void initEditData(DatabaseFunction DBEditFunction)
    {
        DBFunction  = DBEditFunction;
        
        editBookName.setText(DBFunction.getBook_title());
        editBookAuthor.setText(DBFunction.getAuthor_name());
        editBookGenre.setText(DBFunction.getGenre_category());
        editBookPublisher.setText(DBFunction.getPublisher_name());
        
        selected_book_name = DBFunction.getBook_title();
        selected_book_ID=editDBdao.getBookIDForEditFunction(selected_book_name);
        System.out.println(selected_book_ID);
    }
            
            
    @FXML
    void bookEditCancelFunction(ActionEvent event) throws IOException
    {
        
        Parent bookListViewParent = FXMLLoader.load(getClass().getResource("/view/BookListView.fxml"));
        Scene bookListViewScene = new Scene(bookListViewParent);
            
        Stage bookListViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        bookListViewWindow.setScene(bookListViewScene);
        bookListViewWindow.show(); 

    }

    @FXML
    void bookEditSaveFunction(ActionEvent event) {
        DBFunction= new DatabaseFunction();
            
            DBFunction.setBook_id(selected_book_ID);
            DBFunction.setBook_title(editBookName.getText());
            DBFunction.setAuthor_name(editBookAuthor.getText());
            DBFunction.setGenre_category(editBookGenre.getText());
            DBFunction.setPublisher_name(editBookPublisher.getText());
            
            
            if(editBookName.getText().isEmpty() || editBookAuthor.getText().isEmpty()|| 
                        editBookGenre.getText().isEmpty() || editBookPublisher.getText().isEmpty())
            {
                showDialogError("Text Field Cannot Be Empty");
                
            }else{
                try {                                               
                        editDBdao.update(DBFunction);
                        showDialogInformation("Update Function Success");
                        bookEditCancelFunction(event);
                    }catch (Exception e) {
                        // TODO Auto-generated catch block
                        showDialogError("Update Function Not Working");
                        e.printStackTrace();
                    }                
            }          

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
    void bookCatalogMenuButtonAction(ActionEvent event) throws IOException
    {
            
        Parent bookListViewParent = FXMLLoader.load(getClass().getResource("/view/BookListView.fxml"));
        Scene bookListViewScene = new Scene(bookListViewParent);
            
        Stage bookListViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        bookListViewWindow.setScene(bookListViewScene);
        bookListViewWindow.show(); 

    }

    @FXML
    void bookEntryMenuButtonAction(ActionEvent event) throws IOException
    {
                    
            Parent newBookViewParent = FXMLLoader.load(getClass().getResource("/view/NewBookView.fxml"));
            Scene newBookViewScene = new Scene(newBookViewParent);
            
            Stage newBookViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            newBookViewWindow.setScene(newBookViewScene);
            newBookViewWindow.show(); 
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
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText(information);
		 
		alert.showAndWait();
	}
	 
	private void showDialogError(String error)
	{
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(error);
		 
		alert.showAndWait();
	}
    
}
