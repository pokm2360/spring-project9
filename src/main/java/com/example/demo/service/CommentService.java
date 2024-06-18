package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Member;

public interface CommentService {
	
//	게시물별로 댓글 목록 조회
	List<CommentDTO> getListByBoardNo(int boardNo);
	
//	새로운 댓글 등록
	int register(CommentDTO dto);
	
//	특정 댓글 삭제
	boolean remove(int no);
	
//	DTO -> Entity
	default Comment dtoToEntity(CommentDTO dto) {
		
		Member member = Member.builder().id(dto.getWriter()).build();
		
		Board board = Board.builder().no(dto.getBoardNo()).build();
		
		Comment entity = Comment.builder()
				.commentNo(dto.getCommentNo())
				.board(board)
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return entity;
	}
	
//	Entity -> DTO
	default CommentDTO entityToDto(Comment entity) {
		
		CommentDTO dto = CommentDTO.builder()
									.commentNo(entity.getCommentNo())
									.boardNo(entity.getBoard().getNo()) // getNo로 int값으로 변경
									.content(entity.getContent())
									.writer(entity.getWriter().getId())
									.regDate(entity.getRegDate())
									.modDate(entity.getModDate())
									.build();
		return dto;
	}
}
