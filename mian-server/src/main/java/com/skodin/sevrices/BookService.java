package com.skodin.sevrices;

import com.skodin.entities.BookEntity;
import com.skodin.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long aLong) {
        return bookRepository.findById(aLong).orElseThrow();
    }

    public List<BookEntity> findAllByAuthorContaining(String author) {
        return bookRepository.findAllByAuthorContaining(author);
    }

    @Transactional
    public <S extends BookEntity> S saveAndFlush(S entity) {
        return bookRepository.saveAndFlush(entity);

    }

    @Transactional
    public BookEntity updateById(Long id, BookEntity book){

        BookEntity entity = findById(id);

        book.setId(entity.getId());

        return saveAndFlush(book);

    }

    @Transactional
    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }
}
