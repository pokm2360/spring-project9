package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentServicelmpl implements CommentService{

	@Autowired
	CommentRepository repository;
	
	@Override
	public List<CommentDTO> getListByBoardNo(int boardNo) {
		
		Board board = Board.builder().no(boardNo).build(); // 엔티티 생성
		
		List<Comment> entityList = repository.findByBoard(board); //게시물을 기준으로 댓글조회
		
		List<CommentDTO> dtoList = new ArrayList<>(); //엔티티리스트 -> dto리스트
		for (Comment entity : entityList) {
			CommentDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList; //dto리스트 반환
	}

	@Override
	public int register(CommentDTO dto) {
		
		Comment entity = dtoToEntity(dto); //dto -> entity
		repository.save(entity);
		return entity.getCommentNo(); //새로운 댓글 번호 반환
	}

	@Override
	public boolean remove(int no) {
		Optional<Comment> comment = repository.findById(no); //데이터가 실제로 있는지 확인
		if(comment.isEmpty()) { //데이터가 없으면 삭제x
			return false;			
		}
		repository.deleteById(no); //데이터가 있으면 삭제o
		return true;
	}
	
}
