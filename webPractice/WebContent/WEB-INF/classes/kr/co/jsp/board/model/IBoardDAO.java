package kr.co.jsp.board.model;

import java.util.List;

public interface IBoardDAO {
	
	//DB에 작성글을 등록하는 메서드
	void regist(String writer,String title,String content);
	
	//모든 게시글 정보를 가져오는 메서드(글 목록 리스트)
	List<BoardVO> listBoard();
	
	//글 상세정보를 가져오는 메서드
	BoardVO contentBoard(long bId);
	
	//글 수정을 실행하는 메서드
	void updateBoard(long bId,String bTitle, String bContent);
	
	//글 삭제를 실행하는 메서드
	void deleteBoard(long bId);
	
	//글 검색을 실행하는 메서드
	List<BoardVO> searchBoard(String keyword, String category);
	
	//조회수를 올려주는 메서드
	void upHit(long bId);
	//쿠키로 막는거도 있고 아이피주소로 막는 것도 있고...
}
