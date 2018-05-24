package model;

public class DatabaseFunction {
    
    private Integer book_id;
    private Integer author_id;
    private Integer genre_id;
    private String book_title;
    private String author_name;
    private String genre_category;
    private String publisher_name;
    
    public Integer getBook_id() {
	return book_id;
    }
    public void setBook_id(Integer Book_id) {
	this.book_id = Book_id;
    }
    public Integer getAuthor_id() {
       	return author_id;
    }
    public void setAuthor_id(Integer author_id) {
	this.author_id = author_id;
    }
    public Integer getGenre_id() {
    	return genre_id;
    }
    public void setGenre_id(Integer genre_id) {
    	this.genre_id = genre_id;
    }
    public String getBook_title() {
    	return book_title;
    }
    public void setBook_title(String Book_title) {
    	this.book_title = Book_title;
    }
    public String getAuthor_name() {
    	return author_name;
    }
    public void setAuthor_name(String Author_name) {
    	this.author_name = Author_name;
    }
    public String getGenre_category() {
    	return genre_category;
    }
    public void setGenre_category(String Genre_category) {
    	this.genre_category = Genre_category;
    }
    
    public String getPublisher_name() {
    	return publisher_name;
    }
    
    public void setPublisher_name(String Publisher_name) {
    	this.publisher_name = Publisher_name;
    }
    
}
