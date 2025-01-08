package umc.springboot.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.springboot.study.service.MemberService.MemberCommandService;
import umc.springboot.study.web.dto.MemberRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.JoinDTO request,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지함
            return "signup";
        }

        try{
            memberCommandService.joinMember(request);
            return "redirect:/login";
        }catch(Exception e){
            // 회원 가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고 signup 페이지 유지함
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model){
        model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDTO());
        return "signup";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
