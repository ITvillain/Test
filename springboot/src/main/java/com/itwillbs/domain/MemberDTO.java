package com.itwillbs.domain;


import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberDTO {

// 	Lombok 라이브러리
//	@Getter/@Setter Getter, Setter 메서드 생성
//	@ToString : toString() 메서드 생성
//	@ToString(exclude={"변수명"}) : 변수명 제외 toString() 메서드 생성
//	@NonNull : null 체크 => NullPointException 예외발생
//	@EqualsAndHanshCode : equals() hashCode() 메서드 생성
//	@Builder : 빌더 패턴 이용 객체생성
//	@NoArgsConstructor : 파라미터가 없는 생성자(기본생성자) 생성
//	@AllArgsConstructor : 모든 파라미터가 있는 생성자 생성
//	@RequiredArgsConstructor : 초기화 되지 않는 Final, @NonNull 붙은 필드 생성자 생성
//	@Log : log 자동변수 생성
//	@Value : 불변(값이 변하지 않음, 변경할 수 없는) 클래스 생성
//	@Data : @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor 합친 어노테이션
	
	private String id;
	private String pass;
	private String name;
	private Timestamp date;
	private String role;
	
	
	
}
