package trashIsMine.trash.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        //model 객체가 "홍팍" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "hongpark");
        return "greetings";
    }


    @GetMapping("/bye")
    public String SeeYouNextTime(Model model){
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }

}
