/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Permafrost
 */
public class HomeViewController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private AnchorPane homeStylePane;

    @FXML
    private Button buttonAuthorList;

    @FXML
    private Button buttonBookList;

    @FXML
    private Button buttonEnterNewBook;

    @FXML
    private Button buttonGenreList;

    @FXML
    private Button buttonReadBooks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        homeStylePane.setId("HomeViewBackGround");
        
        homeStylePane.getStylesheets().add(getClass().getResource("/view/homeViewStyle.css").toExternalForm());
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
    
}
