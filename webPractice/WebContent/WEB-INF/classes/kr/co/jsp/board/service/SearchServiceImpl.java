package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class SearchServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String keyword = request.getParameter("search");
		String category = request.getParameter("category");
		List<BoardVO> list = BoardDAO.getInstance().searchBoard(keyword,category);
		
		if(list.size() == 0) {
			//자바 클래스 내부에서 HTML이나 JS문법을 사용하는 방법> PrintWriter out객체를 이용
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				
				String htmlCode = "<script>\r\n" + "alert(\"검색 결과에 따른 게시물이 없습니다.\");\r\n"
				+ "location.href=\"/MyWeb/list.board\";\r\n</script>";
				
				out.println(htmlCode);
				out.flush(); //버퍼안에 있는 내용을 클라이언트로 모두 전송하고 버퍼를 비운다.
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		request.setAttribute("boardList", list);
	}

}
