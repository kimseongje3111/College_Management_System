package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOAllSubject {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/se_term_1?autoReconnect=true&useSSL=false";
	static final String USERNAME = "root";
	static final String PASSWORD = "admin";

	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch(ClassNotFoundException e) {
			System.out.println("클래스 로드 실패 : "+e.getMessage());
		}
	}
	
	private DAOAllSubject() {}
	private static DAOAllSubject obj;
	
	public static DAOAllSubject sharedInstance() {
		if(obj == null) {
			obj = new DAOAllSubject();
		}
		return obj;
	}
	// 데이터베이스 연동에 필요한 변수들을 선언
	Connection conn;
	
	// SQL 실행에 필요한 변수
	Statement stmt; 
	
	// select 구문을 수행했을 때 결과를 저장할 변수
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false;
		
		try{
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("연결 실패 : " +e.getMessage());
		}
		return result;
	}
	
	private void close() {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch( Exception e) {
			System.out.println("해제 실패 : " + e.getMessage());
		}
	}
	
	public List<AllSubject> getAllSubjectList() { // select
		List<AllSubject> list = null;
		String sql = "SELECT * FROM allsubject";
		if(connect()) {
			try {
				stmt = conn.createStatement();
				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					
					list = new ArrayList<AllSubject>();
					
					while(rs.next()) {
						AllSubject allSubject = new AllSubject();
						
						allSubject.setClassIdNum(rs.getString("classIdNum"));
						allSubject.setsubjectName(rs.getString("subjectName"));
						allSubject.setClassIdNum(rs.getString("classNum"));
						allSubject.setClassTime(rs.getString("classTime"));
						allSubject.setClassRoom(rs.getString("classRoom"));
						allSubject.setAvailNum(rs.getInt("availNum"));
						
						list.add(allSubject);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			// 연결에 실패했을 때 작업
			System.out.println("데이터베이스 연결에 실패했습니다.");
			System.exit(0);
		}
		return list;
	}
	
	public boolean InsertAllSubject(AllSubject allSubject) {
		boolean result = false;
		
		if(this.connect()) {
			try {
				String sql = "INSERT INTO allsubject VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, allSubject.getClassIdNum());
				pstmt.setString(2, allSubject.getsubjectName());
				pstmt.setString(3, allSubject.getClassNum());
				pstmt.setString(4, allSubject.getClassTime());
				pstmt.setString(5, allSubject.getClassRoom());
				pstmt.setString(6, allSubject.getSyllabus());
				pstmt.setInt(7, allSubject.getAvailNum());
				
				int r = pstmt.executeUpdate();
				
				if(r>0) {
					result = true;
				}
				// 데이터베이스 생성 객체 해제
				pstmt.close();
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("데이터베이스 연결에 실패");
			System.exit(0);
		}
		return result;
	}
}
