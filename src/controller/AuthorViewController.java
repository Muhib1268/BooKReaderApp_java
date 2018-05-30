package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.DataAccessFunction;
import model.DatabaseFunction;

/**
 * FXML Controller class
 *
 * @author Permafrost
 */
public class AuthorViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //String [] authorList = {"George Orwell", "Robert Louis Stevenson", "Dan Brown"};
       
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Circle buttonHome;

    @FXML
    private Button buttonBookList;

    @FXML
    private Button buttonEnterNewBook;
    
    @FXML
    private Button buttonGenreList;

    @FXML
    private Button buttonReadBooks;

    @FXML
    private AnchorPane paneAuthor;

    @FXML
    private TableView<DatabaseFunction> author_table;

    @FXML
    private TableColumn<DatabaseFunction, String> author_book_column;

    @FXML
    private TableColumn<DatabaseFunction, String> author_bookGenre_column;

    @FXML
    private TableColumn<DatabaseFunction, String> author_bookPublisher_Column;
    
    @FXML
    private ComboBox<String> authorListBoxFunction;
    
    private DataAccessFunction daoAuthor;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        daoAuthor = new DataAccessFunction();
        //authorListBox.getItems().addAll(authorList);
        
        //authorListBoxFunction.getItems().addAll(new PropertyValueFactory<>("author_name"));
        author_book_column.setCellValueFactory(new PropertyValueFactory<>("book_title"));
	author_bookGenre_column.setCellValueFactory(new PropertyValueFactory<>("genre_category"));
        author_bookPublisher_Column.setCellValueFactory(new PropertyValueFactory<>("publisher_name"));
        
        authorListComboBoxFunction();
        
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
        void onButtonHomeClicked(MouseEvent event) throws IOException
        {
            
            Parent homeViewParent = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
            
            //homeViewParent.setId("HomeViewBackGround");
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
        void author_combobox_selectAction(ActionEvent event) {
            readForAuthorViewColumns();
        }
        
        
        void readForAuthorViewColumns()
        {
            try{
            
                List <DatabaseFunction> resultAuthor = daoAuthor.readForAuthorView(authorListBoxFunction.getValue());
                
                if(resultAuthor.isEmpty()){
                    showDialogInformation("The Database is Empty");
                }else{
                    //Collections.sort(databasefunction.book_title);
                    author_table.setItems(FXCollections.observableList(resultAuthor));
                }  
            }catch (Exception e){            
                showDialogError("Failed to Read Database");
                e.printStackTrace();
            }   
        }
        
  
        void authorListComboBoxFunction()
        {
            try{
            
                List <String> authorListBox = daoAuthor.readAuthorList();
                
                if(authorListBox.isEmpty()){
                    showDialogInformation("The Author List is Empty");
                }else{
                    //Collections.sort(databasefunction.book_title);
                    authorListBoxFunction.setItems(FXCollections.observableList(authorListBox));
                    //System.out.println(authorListBox);
                } 
            }catch (Exception e){            
                showDialogError("Failed to Read Database");
                e.printStackTrace();
            }   
        }
        
        
        //readAuthorList();
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
