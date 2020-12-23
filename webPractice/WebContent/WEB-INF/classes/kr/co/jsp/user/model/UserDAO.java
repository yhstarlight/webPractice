package kr.co.jsp.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO implements IUserDAO {
	
	DataSource ds;
	
	private UserDAO() {
		//커넥션풀 연결
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		if(dao==null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	//////////////////////////////////////////////////////////////////////

	@Override
	public boolean confirmId(String id) {
		boolean flag = false;
		
		String sql = "SELECT * FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public boolean insertUser(UserVO user) {
		boolean flag = false;
		
		String sql = "INSERT INTO user VALUES (?,?,?,?,?)";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getAddress());
			
			if(pstmt.executeUpdate()==1) {
				flag = true;
			} else {
				flag = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int userCheck(String id, String pw) {
		//아이디가 있는지 조회하여 그 아이디에 같이 맵핑되어 있는
				//비밀번호를 얻은 후, 매개 값으로 받은 비밀번호와 같은지 대조하여 
				//return 값을 조정해 주시면 됩니다.
				//아이디가 없으면 > -1
				//비밀번호가 다른 경우 > 0
				//로그인 성공시 > 1
		
		int check = 0;
		
		String sql = "SELECT * FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pw.equals(rs.getString("user_pw"))) {
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}

	@Override
	public UserVO getUserInfo(String id) {
		String sql = "SELECT * FROM user WHERE user_id=?";
		UserVO user = null;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserVO(
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_address")						
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean changePassword(String id, String pw) {
		boolean flag = false;
		String sql = "UPDATE user SET user_pw=? WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			if(pstmt.executeUpdate()==1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}

	@Override
	public boolean updateUser(UserVO user) {
		boolean flag = false;
		String sql = "UPDATE user SET user_name=?, user_email=?, user_address=? WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getAddress());
			pstmt.setString(4, user.getId());
			
			if (pstmt.executeUpdate() == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return flag;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean flag = false;
		String sql = "DELETE FROM user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			if(pstmt.executeUpdate() == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

}
