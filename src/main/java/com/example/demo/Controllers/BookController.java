package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.services.BookService;



@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //get all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){   //ResponseEntity is return type
        
        List<Book> list = this.bookService.getAllBooks();
        if(list.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));  //if all books found we have to return them so we use Optional
    }

    //get single book handler
    @GetMapping("/books/{id}")  //For this type of RESTful call @PathVariable is used
    public ResponseEntity<Book> book(@PathVariable("id") int id){
        Book book = this.bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    //create a new book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)  //@RequestBody : indicates book parameter must be populated with data from request body. 
                                                 //If request body contains JSON data springboot will automatically convert it to Book object.
    {
        Book b= null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    //delete book handler
    @DeleteMapping("books/{bid}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bid") int bid){
        try {
            this.bookService.deleteBook(bid);
            return ResponseEntity.status(HttpStatus.OK).build();
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    //update book handler
    @PutMapping("books/{bid}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bid") int bid){
        try {
            this.bookService.updateBook(book, bid);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        
    }
}
