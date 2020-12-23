package kr.co.jsp.board.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.service.ContentServiceImpl;
import kr.co.jsp.board.service.DeleteServiceImpl;
import kr.co.jsp.board.service.GetListServiceImpl;
import kr.co.jsp.board.service.IBoardService;
import kr.co.jsp.board.service.ModifyServiceImpl;
import kr.co.jsp.board.service.RegistServiceImpl;
import kr.co.jsp.board.service.SearchServiceImpl;
import kr.co.jsp.board.service.UpdateServiceImpl;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dp;
	IBoardService sv;

    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		uri = uri.substring(conPath.length()+1,uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
		case "write" :
			System.out.println("글 작성 페이지 이동 요청");
			response.sendRedirect("/MyWeb/board/board_write.jsp");
			break;
		case "regist" :
			System.out.println("글 등록 요청이 들어옴");
			sv = new RegistServiceImpl();
			sv.execute(request, response);
			response.sendRedirect("/MyWeb/list.board");
			break;
		case "list" :
			System.out.println("글 목록 요청이 들어옴");
			//request 객체를 다음 화면까지 운반하기 위한 forward이동을 지원하는 객체
			//requestDispatcher
			sv = new GetListServiceImpl();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("/board/board_list.jsp");
			dp.forward(request, response);
			break;
		case "content" :
			System.out.println("글 상세보기 요청이 들어옴!");
			sv = new ContentServiceImpl();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("/board/board_content.jsp");
			dp.forward(request, response);
			break;
			//컨텐트보드를 완성시켜서 호출후 얻어온 객체를 리케스트객체에 담아서 포워딩하면된다. /board/board_content.jsp
		case "modify" :
			System.out.println("글 수정 페이지로 이동 요청이 들어왔다!");
			sv = new ModifyServiceImpl();
			sv.execute(request, response);
			
			dp = request.getRequestDispatcher("/board/board_modify.jsp");
			dp.forward(request, response);
			break;
		case "update" :
			System.out.println("글 수정 요청이 들어옴!");
			sv = new UpdateServiceImpl();
			sv.execute(request, response);
			response.sendRedirect("/MyWeb/list.board");
			break;
		case "delete" :
			System.out.println("글 삭제 요청이 들어옴!");
			sv = new DeleteServiceImpl();
			sv.execute(request, response);
			response.sendRedirect("/MyWeb/list.board");
			break;
		case "search" :
			System.out.println("글 검색 요청이 들어옴!");
			sv = new SearchServiceImpl();
			sv.execute(request, response);
			
			//서치 서비스에서 응답이 한번 일어나면 여기서 응답을 일어나게해선 안됨. 한번 요청엔 한번 응답.
			if(request.getAttribute("boardList") != null) {
			dp = request.getRequestDispatcher("/board/board_list.jsp");
			dp.forward(request, response);
			}
			break;
		}
	}

}
