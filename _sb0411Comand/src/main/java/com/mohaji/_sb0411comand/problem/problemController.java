package com.mohaji._sb0411comand.problem;

import com.mohaji._sb0411comand.survey.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/problem")
public class problemController {
    @GetMapping
    public String form(Model model){ // /problem 요청이 들으오면 바로 이 메소드가 실행된다.
        Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("백엔드","프론트","학생","웹디"));
     //   model.addAttribute("question", q1);
        Question q2 = new Question("동그라미 세모 네모중 무엇이 가장 좋나요?", Arrays.asList("동그라미","세모","네모"));
   //     model.addAttribute("question", q2)
        Question q3 = new Question("하고 싶은 말은?");
    //    model.addAttribute("question", q3)
        model.addAttribute("questions", Arrays.asList(q1,q2,q3));
        return "problem/problemForm";
    }
    @PostMapping
    public String submit(@ModelAttribute("ans") Answer ans){
        log.info("응답자료 {}", ans);
        return "problem/submitted";
    }
}
