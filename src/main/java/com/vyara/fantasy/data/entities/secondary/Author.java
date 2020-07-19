package com.vyara.fantasy.data.entities.secondary;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "author", targetEntity = Book.class)
    private List<Book> books;



}
