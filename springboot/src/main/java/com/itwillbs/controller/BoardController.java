package com.itwillbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.entity.Board;
import com.itwillbs.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor
@Controller
@Log
public class BoardController {
	
	private final BoardService boardService;
	
//	파일위치 가져오기
//	uploadPath = D:/workspace_sts4/springboot/src/main/resources/static/upload
	@Value("${uploadPath}")
	String uploadPath;

	// get /boardwrite 가상주소 
//	=> /board/write.html 이동
	@GetMapping("/boardwrite")
	public String boardwrite() {
		return "/board/write";
	}
	
	//post /boardwrite 가상주소 
	// => 게시판 글쓰기 BoardDTO 전달받아서 
   //  => boardService.save(boardDTO) 호출 
//	   => readcount=0, 오늘날짜, => setBoardEntity
//	   => boardRepository.save() 메서드 호출
	@PostMapping("/boardwrite")
	public String boardwrite(BoardDTO boardDTO) {
		
		boardService.save(boardDTO);
		
		return "redirect:/boardlist";
	}
	
	
	// get /boardlist 가상주소 
//			=> /board/list.html 이동
	@GetMapping("/boardlist")
	public String boardlist(Model model,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page) {
//		현 페이지 번호 page
//		한 화면에 보여줄 글 개수
		int size = 10;
		
//		select * from board orderby num desc limit 0,10
//		PageRequest에서는 page 0부터 시작 => page - 1
//		PageRequest.of(page, size, 정렬);
//		import org.springframework.data.domain.PageRequest;
//		import org.springframework.data.domain.Pageable;
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by("num").descending());
		
//		List<Board> boardList = boardService.getBoardList();
		
//		import org.springframework.data.domain.Page;
		Page<Board> boardList = boardService.getBoardList(pageable);
		
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
//		전체 페이지 번호
		model.addAttribute("totalPages", boardList.getTotalPages());
		
//		한 화면에 보여줄 페이지 개수 설정
		int pageBlock = 10;
//		1 ~ 10 => 1
//		11 ~ 20 => 11
		int startPage = (page - 1) / pageBlock * pageBlock + 1;
//		한 화면에 보여줄 끝 페이지 번호 구하기
		int endPage = startPage + pageBlock - 1;
		if(endPage > boardList.getTotalPages()) {
			endPage = boardList.getTotalPages();
		}
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/board/list";
	}
	
//	http://localhost:8080/boardcontent?num=1
	// get /boardcontent 가상주소 
//	=> (@RequestParam("num") int num)
//	=> Optional<Board> board = boardService.findById(num)
//	=> model 담아서 board
//			=> /board/content.html 이동
	@GetMapping("/boardcontent")
	public String boardcontent(Model model, @RequestParam("num") int num) {
		
		Optional<Board> board = boardService.findById(num);
		
		model.addAttribute("board", board.get());
		
		return "/board/content";
	}
	
	
//	http://localhost:8080/boardupdate?num=1
	// get /boardupdate 가상주소 
//	=> (@RequestParam("num") int num)
//	=> Optional<Board> board = boardService.findById(num)
//	=> model 담아서 board
//			=> /board/update.html 이동
	@GetMapping("/boardupdate")
	public String boardupdate(Model model, @RequestParam("num") int num) {
		
		Optional<Board> board = boardService.findById(num);
		
		model.addAttribute("board", board.get());
		
		return "/board/update";
	}
	
	//post /boardupdate 가상주소
	// => 게시판 글쓰기 BoardDTO 전달받아서
	// => boardService.updatesave(boardDTO) 호출
	// => setBoardEntity
	// => boardRepository.save() 메서드 호출
	@PostMapping("/boardupdate")
	public String boardupdatePost(BoardDTO boardDTO) {
		boardService.updatesave(boardDTO);
		return "redirect:/boardlist";
	}
	
//	http://localhost:8080/boarddelete?num=1
	// get /boarddelete 가상주소 
//		=> (@RequestParam("num") int num)
//	boardService.deleteById(num)
//			=> redirect:/boardlist 이동
	@GetMapping("/boarddelete")
	public String boarddelete(@RequestParam("num") int num) {
		boardService.deleteById(num);
		return "redirect:/boardlist";
	}
	
//	--------------------------------------------------------------
	
	//get /fboardwrite 가상주소
//	=> /board/fwrite.html 이동
	@GetMapping("/fboardwrite")
	public String fboardwrite() {
		return "/board/fwrite";
	}
	
//	http://localhost:8080/boarddelete?num=1
	// get /boarddelete 가상주소 
//		=> (@RequestParam("num") int num)
//	boardService.deleteById(num)
//			=> redirect:/boardlist 이동
	@PostMapping("/fboardwrite")
	public String fboardwrite(@RequestParam("name") String name,
								@RequestParam("subject") String subject,
								@RequestParam("content") String content,
								@RequestParam("file") MultipartFile file) throws IOException {
//		첨부파일 랜덤문자_파일이름.확장자
		UUID uuid = UUID.randomUUID();
		String filename = uuid.toString() + "_" + file.getOriginalFilename();
		
//		첨부파일 => upload 복사
		FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename));
				
//		BoardDTO 담아서
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setName(name);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		boardDTO.setFile(filename);
		
		boardService.save(boardDTO);
		return "redirect:/boardlist";
	}

	
	
	
	
	
	
}
