package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

@RestController //@Controller + @ResponseBody
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService service;
	
//	게시물별 댓글 목록 조회
	@GetMapping("/list")
	public List<CommentDTO> list(@RequestParam(name = "boardNo") int boardNo) {
		List<CommentDTO> commentlist = service.getListByBoardNo(boardNo);
		return commentlist;
	}
	
//	새로운 댓글 등록
	@PostMapping("/register")
	public Boolean register(CommentDTO dto) {
//		임시로 아이디 등록
		String id = "user1";
		dto.setWriter(id);
		service.register(dto);
		return true;
	}
	
	@DeleteMapping("/remove")
	public Boolean remove(@RequestParam(name = "commentNo") int commentNo) {
		boolean result = service.remove(commentNo);
		return result;
	}
}