package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.BookRepository;
import com.example.demo.entities.Book;

@Component
public class BookService {
    
    // private static List <Book> list = new ArrayList<>();
    // static{
    //     list.add(new Book(12, "Merchant of Venice", "Shakespeare"));
    //     list.add(new Book(13, "The monk who sold his ferrari", "abc"));
    //     list.add(new Book(14, "An introduction to Python", "Sumita arora"));
    // }

    @Autowired
    private BookRepository bookRepository;
    
    //get all books
    public List<Book> getAllBooks(){
        List <Book> list = (List<Book>)this.bookRepository.findAll();
        return list;
    }

    //get single book by id
    public Book getBookById(int id){
       Book book = null;
       try{
    //    book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        book = this.bookRepository.findById(id);
       }catch(Exception e){
        e.printStackTrace();
       }
       return book;

    }

    //adding a book
    public Book addBook(Book b){
        Book result  = bookRepository.save(b);
        return result;
    }

    //deleting a book
    public void deleteBook(int bid){
        // Book bookToDelete = null;
        // for(Book b : list){
        //     if(b.getId() == bid)
        //     {
        //         bookToDelete = b;
        //         break;
        //     }
        // }
        // if(bookToDelete != null)
        // list.remove(bookToDelete);

        bookRepository.deleteById(bid);
    }

    //updating a book
    public void updateBook(Book book, int bid){
        // list.stream().map(b -> {
        //     if(b.getId() == bid){
        //         b.setTitle(book.getTitle());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());
        book.setId(bid);
        bookRepository.save(book);
    }
}
