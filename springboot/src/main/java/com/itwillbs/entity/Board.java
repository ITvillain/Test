package com.itwillbs.entity;

import java.sql.Timestamp;

import com.itwillbs.domain.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "board")
@Getter
@Setter
@ToString
public class Board {
	
	//primary key, 글번호 자동으로 1증가
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num", updatable = false)
	private int num;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "readcount")
	private int readcount;
	
	@Column(name = "date")
	private Timestamp date;
	
	@Column(name = "file")
	private String file;
	
	
	public static Board setBoardEntity(BoardDTO boardDTO) {
		Board board = new Board();
		board.setNum(boardDTO.getNum());
		board.setName(boardDTO.getName());
		board.setSubject(boardDTO.getSubject());
		board.setContent(boardDTO.getContent());
		board.setReadcount(boardDTO.getReadcount());
		board.setDate(boardDTO.getDate());
		board.setFile(boardDTO.getFile());
		return board;
	}

}