package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessFunction {
    
    private Connection connection;
    
    public DataAccessFunction(){
        
        connection = new ConnectionDB().getConnection();
    }
    
    public void create(DatabaseFunction databasefunction){
        
    String sql="insert into book_catalog (book_title, author_name, genre_category, publisher_name) values (?, ?, ?, ?)"; 
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, databasefunction.getBook_title());
            statement.setString(2, databasefunction.getAuthor_name());
            statement.setString(3, databasefunction.getGenre_category());
            statement.setString(4, databasefunction.getPublisher_name());      
            
            
            statement.execute();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            }
    }
    
    public void update(DatabaseFunction databasefunction){
        
        String sql="update book_catalog set book_title=?, author_name=?, genre_category=? where id=?"; 
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, databasefunction.getBook_title());
            statement.setString(2, databasefunction.getAuthor_name());
            statement.setString(3, databasefunction.getGenre_category());
            //statement.setString(4, databasefunction.getPublisher_name());     , publisher_name=?
            statement.setInt(4, databasefunction.getBook_id());
            
            statement.execute();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 	
        
    }    
    
    public void delete(String bookName){
    	
        String sql="delete from book_catalog where book_title = ?"; 
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, bookName);          
            statement.execute();
            statement.close();           
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
        
    }   
    
    public List <DatabaseFunction> read(){
    	
    	List <DatabaseFunction> databasefunctions = new ArrayList<>();
        
    	String sql="select * from book_catalog order by book_title";
    	
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	DatabaseFunction databasefunction = new DatabaseFunction();
            	
            	//databasefunction.setBook_id(resultSet.getInt("book_id"));
            	databasefunction.setBook_title(resultSet.getString("book_title"));
            	databasefunction.setAuthor_name(resultSet.getString("author_name"));
            	databasefunction.setGenre_category(resultSet.getString("genre_category"));
                databasefunction.setPublisher_name(resultSet.getString("publisher_name"));
            	
            	databasefunctions.add(databasefunction);
            }
            
            resultSet.close();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
        return databasefunctions;
        
    }
    
    public List <DatabaseFunction> readForAuthorView(String author_name_function){
    	
    	List <DatabaseFunction> DBfunction_author = new ArrayList<>();
        
    	String sql="select book_title, genre_category, publisher_name from book_catalog "
                + "where author_name like '%" + author_name_function + "%' order by book_title";
    	
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	DatabaseFunction databasefunction = new DatabaseFunction();
            	
            	//databasefunction.setBook_id(resultSet.getInt("book_id"));
            	databasefunction.setBook_title(resultSet.getString("book_title"));
            	databasefunction.setGenre_category(resultSet.getString("genre_category"));
                databasefunction.setPublisher_name(resultSet.getString("publisher_name"));
            	
            	DBfunction_author.add(databasefunction);
            }
            
            resultSet.close();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
        return DBfunction_author;
        
    }
        
    public List <String> readAuthorList(){
    	
    	List <String> DBfunctionAuthorList = new ArrayList<>();
        
    	String sql="select distinct author_name from book_catalog order by author_name";
    	
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	//DatabaseFunction databasefunction = new DatabaseFunction();
                
                String authorName;
                
            	authorName = resultSet.getString("author_name");
            	
            	DBfunctionAuthorList.add(authorName);
            }
            
            resultSet.close();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
         return DBfunctionAuthorList;
        
    }
    public List <String> readGenreList(){
    	
    	List <String> DBfunctionGenreList = new ArrayList<>();
        
    	String sql="select distinct genre_category from book_catalog order by genre_category";
    	
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	//DatabaseFunction databasefunction = new DatabaseFunction();
                
                String genreName;
                
            	genreName = resultSet.getString("genre_category");
            	
            	DBfunctionGenreList.add(genreName);
            }
            
            resultSet.close();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
         return DBfunctionGenreList;
        
    }
    
        public List <DatabaseFunction> readForGenreView(String genre_category_function){
    	
    	List <DatabaseFunction> DBfunction_genre = new ArrayList<>();
        
    	String sql="select book_title, author_name, publisher_name from book_catalog "
                + "where genre_category like '%" + genre_category_function + "%' order by book_title";
    	
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next())
            {
            	DatabaseFunction databasefunction = new DatabaseFunction();
            	
            	//databasefunction.setBook_id(resultSet.getInt("book_id"));
            	databasefunction.setBook_title(resultSet.getString("book_title"));
            	databasefunction.setAuthor_name(resultSet.getString("author_name"));
                databasefunction.setPublisher_name(resultSet.getString("publisher_name"));
            	
            	DBfunction_genre.add(databasefunction);
            }
            
            resultSet.close();
            statement.close();
            
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            	throw new RuntimeException(e);
            } 
    	
        return DBfunction_genre;
        
    }
}



