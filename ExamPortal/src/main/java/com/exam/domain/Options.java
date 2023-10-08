package com.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Options {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long optionId;
    private String option;
//    private String optionImage;
    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

}
