package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;

import jakarta.transaction.Transactional;

@Transactional //sql작업결과 commit
public interface CommentRepository extends JpaRepository<Comment, Integer>{

//	select * from comment where board_no=1
//	게시물을 기준으로 댓글 리스트 조회 (쿼리메소드)
	List<Comment> findByBoard(Board board);
	
//	delete from comment where board_no=1
//	게시물을 기준으로 댓글을 모두 삭제
	void deleteByBoard(Board board);
}
