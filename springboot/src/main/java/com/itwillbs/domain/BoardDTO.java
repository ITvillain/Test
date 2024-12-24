package com.itwillbs.domain;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int num;
    private String name;
    private String subject;
    private String content;
    private int readcount;
    private Timestamp date;
    private String file;
	
	
	
}
