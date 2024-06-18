package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;
import com.example.demo.repository.CommentRepository;

@SpringBootTest
public class CommentRepositoryTest {

	@Autowired
	CommentRepository repository;
	
	@Test
	public void 댓글등록() {
//		테이블에 있는 회원
		Member member = Member.builder().id("user1").build();
//		테이블에 있는 게시물
		Board board = Board.builder().no(2).build();
		
		Comment comment = Comment.builder()
								.content("댓글")
								.writer(member)
								.build();
		repository.save(comment);
								
									
	}
	
	@Test
	public void 댓글목록조회() {
		List<Comment> list = repository.findAll();
				
			for (Comment comment : list)
				System.out.println(comment);
	}
	
	@Test
	public void 댓글단건조회() {
		Optional<Comment> result = repository.findById(1);
		
		if(result.isPresent()) {
			Comment comment = result.get();
			System.out.println(comment);
		}
	}
	
	@Test
	public void 댓글수정() {
		
		Optional<Comment> result = repository.findById(2);
		Comment comment = result.get();
		comment.setContent("안녕하세요");
		
		repository.save(comment);
	}
	
	@Test
	public void 댓글삭제() {
		repository.deleteById(1);
	}
	
	@Test
	public void 게시물별_댓글조회() {
		
		Board board = Board.builder().no(2).build();
		
		List<Comment> list = repository.findByBoard(board);
		
		for(Comment comment : list) {
			System.out.println(comment);
		}
	}
	
	@Test
	public void 게시물별_댓글삭제() {
		
		Board board = Board.builder().no(2).build();
		
		repository.deleteByBoard(board);
	}
}
