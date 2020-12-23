package kr.co.jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO implements IBoardDAO {

	DataSource ds;
	
	private BoardDAO() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static BoardDAO dao = new BoardDAO();
	
	public static BoardDAO getInstance() {
		if(dao != null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	////////////////////////////////////////////////////////////////////
	
	@Override
	public void regist(String writer, String title, String content) {
		
		String sql = "INSERT INTO my_board (writer, title, content) VALUES (?,?,?)";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<BoardVO> listBoard() {
		
		String sql = "SELECT * FROM my_board ORDER BY board_id DESC";
		
		List<BoardVO> list = new ArrayList<>();
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO(
						rs.getLong("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("date"),
						rs.getInt("hit")
						);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}

	@Override
	public BoardVO contentBoard(long bId) {
		
		BoardVO vo = null;
		
		String sql = "SELECT * FROM my_board WHERE board_id=?";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, bId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO(
						rs.getLong("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content").replace("\r\n", "<br>"),
						rs.getTimestamp("date"),
						rs.getInt("hit")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return vo;
	}

	@Override
	public void updateBoard(long bId, String bTitle, String bContent) {
		String sql = "UPDATE my_board set title=?, content=? WHERE board_id=?";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setLong(3,bId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteBoard(long bId) {
		String sql = "DELETE FROM my_board WHERE board_id=?";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, bId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<BoardVO> searchBoard(String keyword, String category) {
		List<BoardVO> list = new ArrayList<>();
		
		String sql = "SELECT * FROM my_board WHERE "+ category + " LIKE ?"
				+ "ORDER BY board_id DESC";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%"+keyword+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO(
						rs.getLong("board_id"),
						rs.getString("title"),
						rs.getString("writer"),
						rs.getString("content"),
						rs.getTimestamp("date"),
						rs.getInt("hit")
						);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}

	@Override
	public void upHit(long bId) {
		String sql = "UPDATE my_board SET hit = hit+1 WHERE board_id=?";
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setLong(1, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
