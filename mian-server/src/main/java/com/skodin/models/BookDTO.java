package com.skodin.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDTO {
    Long id;
    String author;
    String title;
    Double price;
}