package com.exam.domain.ReqVO;

import com.exam.domain.Question;
import lombok.Data;

import java.util.List;

@Data
public class ResultEveluateVO {
    private List<Question> questionVOList;
    private String totalTime;
    private String userName;
}
