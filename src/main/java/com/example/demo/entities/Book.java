package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                 //@Entity : is used to mark a class that can be stored in database. JPA automatically
                        //maps it to a databse table.
@Table(name="books")    //@Table is used to specify a different table name. By default if it is not specified
                        //the entity class name is the Table name.
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //This make the id's be automatically generated based on database algorithm
    @Column(name="book_id")     //Changes the column name of id to book_id
    private int id;
    private String title;
    private String author;
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
    }
    
}
