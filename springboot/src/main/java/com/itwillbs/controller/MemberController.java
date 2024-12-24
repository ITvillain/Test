package com.itwillbs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;


@RequiredArgsConstructor
@Controller
@Log
public class MemberController {
	
	private final MemberService memberService;

//	http://localhost:8080/test1
	
//	@GetMapping("/test1")
//	public String test1() {
//		return "test1";
//	}
	
	@GetMapping("/insert")
	public String insert() {
		return "/member/insert";
	}
	
	@PostMapping("/insert")
	public String insertPro(MemberDTO memberDTO) {
		log.info("post insert");
		log.info(memberDTO.toString());
		
//		memberService.메서드() 호출
		memberService.save(memberDTO);
		
		return "redirect:/login";
	}
	
	// login 가상주소		=> /member/login
	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(MemberDTO memberDTO, HttpSession session) {
		log.info("login");
		log.info(memberDTO.toString());
		
//		Member = memberService.메서드() 호출
		Member member = memberService.findByIdAndPass(memberDTO);
		
		if(member != null) {
//			아이디 비밀번호 일치 => 세션값 생성 "id",id => main 이동
			session.setAttribute("id", member.getId());
			return "redirect:/main";
		}else {
//			아이디 비밀번호 틀림 => login 이동
			return "redirect:/login";
		}
		
	}
	
	//get /main 가상주소 	=> /member/main
	@GetMapping("/main")
	public String main() {
		return "/member/main";
	}
	
	//get /logout 가상주소 => 세션초기화 => /login 이동
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	//get /info 가상주소 => 세션값 가져오기 => memberService 조회 => model 담아서 => /member/info.html 이동
	@GetMapping("/info")
	public String info(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		Optional<Member> member = memberService.findById(id);
		model.addAttribute("member", member.get());
		return "/member/info";
	}
	
	//get /update 가상주소 => 세션값 가져오기 => memberService 조회 => model 담아서 => /member/update.html 이동
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		Optional<Member> member = memberService.findById(id);
		model.addAttribute("member", member.get());
		return "/member/update";
	}
	
	//post /update 가상주소 => memberService.findByIdAndPass() => !=null 일치, ==null 틀림 /update
	@PostMapping("/update")
	public String updatePost(MemberDTO memberDTO) {
		// memberDTO : update.html 수정한 정보가 저장
		Member member = memberService.findByIdAndPass(memberDTO);
		// member : 데이터 베이스에 저장된 정보
		
		if(member != null) {
//			save() 메서드 이용 => update members set pass = ?, name = ?, date = ?, where id = ?
//			member setDate(), memberService updateSave(memberDTO) => /main 이동
			memberDTO.setDate(member.getDate());
			memberService.updateSave(memberDTO);
			return "redirect:/main";
		}else {
//			/update 이동
			return "redirect:/update";
		}
	}
	
	//get /delete 가상주소 => /member/delete.html 이동
	@GetMapping("/delete")
	public String delete() {
		
		return "/member/delete";
	}
	
	//post /delete 가상주소 => memberService.findByIdAndPass()
	@PostMapping("/delete")
	public String deletePost(MemberDTO memberDTO, HttpSession session) {
		// memberDTO : update.html 수정한 정보가 저장
				Member member = memberService.findByIdAndPass(memberDTO);
				// member : 데이터 베이스에 저장된 정보
				
		if(member != null) {
			memberService.deleteById(memberDTO.getId());
			
			session.invalidate();
			return "redirect:/login";
		}else {
//			/delete 이동
			return "redirect:/delete";
	}
	// => != null 일치, memberService deleteById(id), 세션값 초기화 => /main 이동
	// == null 틀림 / delete 이동
	// void deleteById(id) : 엔티티 삭제
	}
	
	//get /list 가상주소 => memberService findAll() => model memberList => member/list.html 이동
	@GetMapping("/list")
	public String list(Model model) {
		List<Member> memberList = memberService.findAll();
		model.addAttribute("memberList", memberList);
		return "/member/list";
	}
	
	
}
