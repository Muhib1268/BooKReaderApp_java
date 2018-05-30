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
public class GenreViewController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button buttonAuthorList;

    @FXML
    private Button buttonBookList;

    @FXML
    private Circle buttonHome;

    @FXML
    private Button buttonReadBooks;

    @FXML
    private Button buttonEnterNewBook;

    @FXML
    private AnchorPane genrePane;

    @FXML
    private TableView<DatabaseFunction> genre_table;

    @FXML
    private TableColumn<DatabaseFunction, String> genreBook;

    @FXML
    private TableColumn<DatabaseFunction, String> genreAuthor;

    @FXML
    private TableColumn<DatabaseFunction, String> genrePublication;
    
    
    @FXML
    private ComboBox<String> genreListBoxFunction;
    
    DataAccessFunction daoGenre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
        daoGenre = new DataAccessFunction();
        //authorListBox.getItems().addAll(authorList);
        
        //authorListBoxFunction.getItems().addAll(new PropertyValueFactory<>("author_name"));
        genreBook.setCellValueFactory(new PropertyValueFactory<>("book_title"));
	genreAuthor.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        genrePublication.setCellValueFactory(new PropertyValueFactory<>("publisher_name"));
        
        genreListComboBoxFunction();
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
        void bookCatalogMenuButtonAction(ActionEvent event) throws IOException
        {
            
            Parent bookListViewParent = FXMLLoader.load(getClass().getResource("/view/BookListView.fxml"));
            Scene bookListViewScene = new Scene(bookListViewParent);
            
            Stage bookListViewWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            bookListViewWindow.setScene(bookListViewScene);
            bookListViewWindow.show(); 

        }
        
        @FXML
        void genre_combobox_selectAction(ActionEvent event) {
            readForGenreViewColumns();
        }
        
        void readForGenreViewColumns()
        {
            try{
                List <DatabaseFunction> resultGenre = daoGenre.readForGenreView(genreListBoxFunction.getValue());
                
                if(resultGenre.isEmpty()){
                    showDialogInformation("The Database is Empty");
                }else{
                    //Collections.sort(databasefunction.book_title);
                    genre_table.setItems(FXCollections.observableList(resultGenre));
                }  
            }catch (Exception e){            
                showDialogError("Failed to Read Database");
                e.printStackTrace();
            }   
        }
        
        
        
        void genreListComboBoxFunction()
        {
            try{
            
                List <String> genreListBox = daoGenre.readGenreList();
                
                if(genreListBox.isEmpty()){
                    showDialogInformation("The Genre List is Empty");
                }else{
                    //Collections.sort(databasefunction.book_title);
                    genreListBoxFunction.setItems(FXCollections.observableList(genreListBox));
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
