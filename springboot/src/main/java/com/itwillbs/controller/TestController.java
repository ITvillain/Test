package com.itwillbs.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@Controller
@RequiredArgsConstructor
@Log
public class TestController {

// 객체생성
	private final MemberRepository memberRepository;
	
//	http://localhost:8080/test1
	
	@GetMapping("/test1")
	public String test1() {
		return "test1";
	}
	
	@GetMapping("/test2")
	public String test2() {
//		test2.jsp 404 에러발생
		return "test2";
	}
	
	@GetMapping("/test3")
	public String test3(Model model) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("park");
		memberDTO.setPass("1234");
		memberDTO.setName("박아이");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		model.addAttribute("memberDTO",memberDTO);
		return "test3";
	}
	
	@GetMapping("/test4")
	public String test4(Model model) {
		List<MemberDTO> memberList = new ArrayList<>();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("kim");
		memberDTO.setPass("p123");
		memberDTO.setName("김길동");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO2 = new MemberDTO();
		memberDTO2.setId("lee");
		memberDTO2.setPass("p456");
		memberDTO2.setName("이길동");
		memberDTO2.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO3 = new MemberDTO();
		memberDTO3.setId("hong");
		memberDTO3.setPass("p789");
		memberDTO3.setName("홍길동");
		memberDTO3.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberList.add(memberDTO);
		memberList.add(memberDTO2);
		memberList.add(memberDTO3);
		
		model.addAttribute("memberList", memberList);
		
		return "test4";
	}
	
//	http://localhost:8080/test5?id=kim&pass=p123
	@GetMapping("/test5")
	public String test5(MemberDTO memberDTO, Model model) {
		log.info(memberDTO.toString());
		
		model.addAttribute("memberDTO", memberDTO);
		
		return "test5";
	}
	
//	http://localhost:8080/test6
	@GetMapping("/test6")
	public String test6() {
		log.info("test6");
		
		Member member = new Member();
		member.setId("hong");
		member.setPass("p789");
		member.setName("홍길동");
		member.setDate(new Timestamp(System.currentTimeMillis()));
	
//		회원가입
		memberRepository.save(member);
		
		return "test1";
	}


}
