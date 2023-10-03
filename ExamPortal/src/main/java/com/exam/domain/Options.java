package com.exam.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Options {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String option;
    private String optionImage;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Question question;

}
