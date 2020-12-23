package kr.co.jsp.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class ContentServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		long bId = Long.parseLong(request.getParameter("bId"));
		String hitId = request.getParameter("bId");
		
		/*
		 1. 조회수 중복 방지를 위한 쿠키를 하나 생성하세요.
		 쿠키 이름과 값은 해당 글 번호를 넣어놓으세요. (String밖에 안들어갑니다. 다시 스트링으로)
		 2. 다시 해당 글의 상세보기 요청이 왔을 때 해당 글 번호로 이루어진 쿠키가 존재하는지를 확인하여
		 존재한다면 쿠키의 값을 얻어오시면 됩니다.
		 3. 쿠키의 값을 얻었을 때 (c.getValue) 해당 값이 요청이 들어온 글 번호와 일치한다면
		 조회수를 올리지 않고, 쿠키의 값이 요청이 들어온 글 번호와 일치하지 않는다면 upHit를 호출하시면 됩니다.
		 */
	
		Cookie cookie = new Cookie(hitId , hitId);
		cookie.setMaxAge(15);
		response.addCookie(cookie);
		
		Cookie[] cookies = request.getCookies();
		String bNum = "";
		
		for(Cookie c :cookies) {
			if(c.getName().equals(hitId)) {
				bNum = c.getValue();
			}
		}
		
		if(!bNum.equals(hitId)) {
			BoardDAO.getInstance().upHit(bId);
		}
		
		BoardVO vo = BoardDAO.getInstance().contentBoard(bId);
		request.setAttribute("contentBoard", vo);

	}

}
