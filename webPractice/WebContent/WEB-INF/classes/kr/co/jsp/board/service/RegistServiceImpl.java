package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;

public class RegistServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bContent = request.getParameter("bContent");
		bContent.replace("<br>","\r\n");
		
		BoardDAO.getInstance().regist(
				request.getParameter("bWriter"),
				request.getParameter("bTitle"),
				bContent);
	}

}
