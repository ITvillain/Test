package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberDTO;

@RestController
public class RestTestController {
	
//	가상주소 : http://localhost:8080/rTest1
	@GetMapping("/rTest1")
	public String rTest1() {
		return "Hello, Spring Boot";
	}

//	가상주소 : http://localhost:8080/rTest2
	@GetMapping("/rTest2")
	public MemberDTO rTest2() {
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("park");
		memberDTO.setPass("3333");
		memberDTO.setName("kim");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		return memberDTO;
	}
	
//	가상주소 : http://localhost:8080/rTest3
	@GetMapping("/rTest3")
	public List<MemberDTO> rTest3() {
		List<MemberDTO> memberList = new ArrayList<>();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("park");
		memberDTO.setPass("3333");
		memberDTO.setName("kim");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO2 = new MemberDTO();
		memberDTO2.setId("park");
		memberDTO2.setPass("3333");
		memberDTO2.setName("kim");
		memberDTO2.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO3 = new MemberDTO();
		memberDTO3.setId("park");
		memberDTO3.setPass("3333");
		memberDTO3.setName("kim");
		memberDTO3.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberList.add(memberDTO);
		memberList.add(memberDTO2);
		memberList.add(memberDTO3);
		
		return memberList;
		
	}
}
