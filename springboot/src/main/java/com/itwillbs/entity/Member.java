package com.itwillbs.entity;

import java.sql.Timestamp;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwillbs.domain.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//엔티티 매핑 관련 어노테이션
//@Entity : 클래스 엔티티 선언
//@Table : 엔티티와 매핑할 테이블 지정
//@Id : 테이블에서 기본키 사용할 속성 지정
//@Column : 필드와 컬럼 매핑
//name = "칼럼명", length = 크기, nullable = false, unique
//		  columnDefinition = varchar(5) 직접지정, insertable, updatable
//@GeneratedValue(strategy = GenerationType.AUTO) 키값생성, 자동으로 증가
//@Lob BLOB, CLOB 타입 매핑
//@CreateTimeStamp isert 시 시간 자동 저장
//@Enumerated enum 타입매핑

@Entity
@Table(name = "members")
@Getter
@Setter
@ToString
public class Member {

//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id", length = 50)
	private String id;

	@Column(name = "pass", nullable = true)
	private String pass;

	@Column(name = "name")
	private String name;

	@Column(name = "date")
	private Timestamp date;

//	권한 컬럼 => 일반사용자 USER, 관리자 ADMIN
	@Column(name = "role")
	private String role;

//	스프링 시큐리티 : 애플리케이션 인증, 인가를 일관된 형태로 처리하는 모듈
//	인증 : 로그인 사용자 식별
//	인가 : 시스템 자원에 대한 접근을 통제

//	SecurityFilterChain -> 인증
//						-> 인가

//	생성자
	public Member() {
	}

	public Member(String id, String pass, String name, String roleUser, Timestamp date) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.role = roleUser;
		this.date = date;
	}

	public static Member setMemberEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setPass(memberDTO.getPass());
		member.setName(memberDTO.getName());
		member.setDate(memberDTO.getDate());
		member.setRole(memberDTO.getRole());//추가
		return member;
	}

//	회원가입
	
	public static Member createUser(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
		String roleUser = null;
		if(memberDTO.getId().equals("admin")) {
			roleUser = "ADMIN";
		}else {
			roleUser = "";
		}
		return new Member(memberDTO.getId(), 
				passwordEncoder.encode(memberDTO.getPass()),
				memberDTO.getName(),
				roleUser,
				new Timestamp(System.currentTimeMillis()));
	}
}
