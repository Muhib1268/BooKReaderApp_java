/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class BookListViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
       
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonAuthorList;

    @FXML
    private Circle buttonHome;

    
    @FXML
    private Button buttonGenreList;

    @FXML
    private Button buttonReadBooks;
    

    @FXML
    private Button buttonEnterNewBook;

    
    @FXML
    private AnchorPane bookPane;

    @FXML
    private TableView<DatabaseFunction> book_table;
	 
    //@FXML
    //private TableColumn<DatabaseFunction, Integer> book_id_function;

    @FXML
    private TableColumn<DatabaseFunction, String> book_name_function;

    @FXML
    private TableColumn<DatabaseFunction, String> book_author_function;

    @FXML
    private TableColumn<DatabaseFunction, String> book_genre_function;
    
    @FXML
    private TableColumn<DatabaseFunction, String> book_publisher_function;

    
    private DataAccessFunction dao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dao = new DataAccessFunction();
                         
	//book_id_function.setCellValueFactory(new PropertyValueFactory<>("book_id"));
	book_name_function.setCellValueFactory(new PropertyValueFactory<>("book_title"));
	book_author_function.setCellValueFactory(new PropertyValueFactory<>("author_name"));
	book_genre_function.setCellValueFactory(new PropertyValueFactory<>("genre_category"));
        book_publisher_function.setCellValueFactory(new PropertyValueFactory<>("publisher_name"));
        
        readForBookReadingPane();
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
        public void readForBookReadingPane()
        {
            try{
            
                List <DatabaseFunction> resultBook = dao.read();
                
                //System.out.println(resultBook);
        
                ///*
                if(resultBook.isEmpty()){
                    showDialogInformation("The Database is Empty");
                }else{
                    book_table.setItems(FXCollections.observableList(resultBook));
                }
                //*/
                   
            }catch (Exception e){            
                showDialogError("Failed to Read Database");
                e.printStackTrace();
            }   
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
