package com.skodin.controllers;

import com.skodin.models.BookDTO;
import com.skodin.sevrices.BookService;
import com.skodin.util.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return mapper.getBookDTO(bookService.findById(id));
    }

    @GetMapping
    public List<BookDTO> getAllBooks(@RequestParam(required = false) String author) {
        if (author != null)
            return bookService.findAllByAuthorContaining(author)
                    .stream()
                    .map(mapper::getBookDTO)
                    .toList();

        return bookService.findAll().stream().map(mapper::getBookDTO).toList();
    }

    @PostMapping
    public void addBook(@RequestBody BookDTO request) {
        bookService.saveAndFlush(mapper.getBookEntity(request));
    }














}
