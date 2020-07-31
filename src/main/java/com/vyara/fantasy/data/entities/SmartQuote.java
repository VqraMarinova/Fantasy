package com.vyara.fantasy.data.entities;

import com.vyara.fantasy.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quotes")
public class SmartQuote extends BaseEntity {

    @Column
    private String author;


    @Column(columnDefinition = "NVARCHAR(400)")
    private String content;

    @Column
    private boolean quoteOfTheDay = false;


}
