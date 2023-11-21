package com.skodin.util;

import com.skodin.entities.BookEntity;
import com.skodin.models.BookDTO;
import jdk.jfr.Category;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

    private final org.modelmapper.ModelMapper mapper = new org.modelmapper.ModelMapper();

    public BookEntity getBookEntity(BookDTO dto){
        return mapper.map(dto, BookEntity.class);
    }

    public BookDTO getBookDTO(BookEntity entity){
        return mapper.map(entity, BookDTO.class);
    }

}
