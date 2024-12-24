package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public void save(MemberDTO memberDTO) {
		
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
//		Member member = new Member();
//		member.setId(memberDTO.getId());
//		member.setPass(memberDTO.getPass());
//		member.setName(memberDTO.getName());
//		member.setDate(memberDTO.getDate());
		
//		Member member = Member.setMemberEntity(memberDTO);
		
		Member member = Member.createUser(memberDTO, passwordEncoder);
		
		memberRepository.save(member);
	}
	
	public Member findByIdAndPass(MemberDTO memberDTO) {
		return memberRepository.findByIdAndPass(memberDTO.getId(), memberDTO.getPass());
	}
	
	public Optional<Member> findById(String id){
		return memberRepository.findById(id);
	}
	
	public void updateSave(MemberDTO memberDTO) {
//		Member member = new Member();
//		member.setId(memberDTO.getId());
//		member.setPass(memberDTO.getPass());
//		member.setName(memberDTO.getName());
//		member.setDate(memberDTO.getDate());
		
		Member member = Member.setMemberEntity(memberDTO);
		
		memberRepository.save(member);
	}
	
	public void deleteById(String id) {
		
		memberRepository.deleteById(id);
	}
	
	public List<Member> findAll() {
		return memberRepository.findAll();
		
	}
	
	
	
}