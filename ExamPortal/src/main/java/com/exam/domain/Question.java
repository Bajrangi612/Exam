package com.exam.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(length = 5000)
    private String content;
    private String image;
    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Options> optionsList;

}
