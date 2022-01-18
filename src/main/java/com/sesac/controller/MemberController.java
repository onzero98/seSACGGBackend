package com.sesac.controller;

import com.sesac.dto.MemberDto;
import com.sesac.entity.Member;
import com.sesac.service.MemberService;
import com.sesac.vo.RequestUser;
import com.sesac.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/register")
    public String memberForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/register")
    public String newMember(@RequestBody MemberDto memberFormDto){
        try {
            Member member = Member.createMember(memberFormDto);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            return "member/memberForm";
        }

        return "redirect:/";
    }

//    @PostMapping("/register")
//    public ResponseEntity<ResponseUser> createMember(@Valid MemberDto memberDto, @RequestBody RequestUser user){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        MemberDto userDto = mapper.map(user, MemberDto.class);
//
//        Member member = Member.createMember(memberDto);
//        memberService.saveMember(member);
//
//        // userDto -> responseUser
//        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
//    }
}
