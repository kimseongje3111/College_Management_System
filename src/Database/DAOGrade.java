package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOGrade {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/se_term_1?autoReconnect=true&useSSL=false";
	static final String USERNAME = "root";
	static final String PASSWORD = "admin";

	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch(ClassNotFoundException e) {
			System.out.println("Ŭ���� �ε� ���� : "+e.getMessage());
		}
	}
	
	private DAOGrade() {}
	private static DAOGrade obj;
	
	public static DAOGrade sharedInstance() {
		if(obj == null) {
			obj = new DAOGrade();
		}
		return obj;
	}
	// �����ͺ��̽� ������ �ʿ��� �������� ����
	Connection conn;
	
	// SQL ���࿡ �ʿ��� ����
	Statement stmt; 
	
	// select ������ �������� �� ����� ������ ����
	private ResultSet rs;
	
	private boolean connect() {
		boolean result = false;
		
		try{
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("\n- MySQL Connection");
			result = true;
		} catch (Exception e) {
			System.out.println("���� ���� : " +e.getMessage());
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
			System.out.println("���� ���� : " + e.getMessage());
		}
	}
	
	public List<Grade> getGradeList() { // select
		List<Grade> list = null;
		String sql = "SELECT * FROM grade";
		if(connect()) {
			try {
				stmt = conn.createStatement();
				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					
					list = new ArrayList<Grade>();
					
					while(rs.next()) {
						Grade grade = new Grade();
						
						grade.setClassIdNum(rs.getString("classIdNum"));
						grade.setUserId(rs.getString("userId"));
						grade.setGrade(rs.getFloat("grade"));
						
						list.add(grade);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			// ���ῡ �������� �� �۾�
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;
	}
	
	public boolean InsertGrade(Grade grade) {
		boolean result = false;
		
		if(this.connect()) {
			try {
				String sql = "INSERT INTO grade VALUES (?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, grade.getClassIdNum());
				pstmt.setString(2, grade.getUserId());
				pstmt.setFloat(3, grade.getGrade());
				
				int r = pstmt.executeUpdate();
				
				if(r>0) {
					result = true;
				}
				// �����ͺ��̽� ���� ��ü ����
				pstmt.close();
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ ����");
			System.exit(0);
		}
		return result;
	}

	public boolean deleteGrade(Grade grade) {
		boolean result = false;

		if(this.connect()) {
			try {
				String sql = "DELETE from grade WHERE classIdNum='"+grade.getClassIdNum()+"' and userID='"+grade.getUserId()+"'";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				int r = pstmt.executeUpdate();

				if(r>0) {
					result = true;
				}
				pstmt.close();
				this.close();
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("�����ͺ��̽� ���ῡ ����");
			System.exit(0);
		}
		return result;
	}

	public List getStudentReport(Grade grade) { //�л��� ����ǥ ��ȸ
		List<Grade> list = null;
		Grade grade1 = new Grade();
		String sql = "SELECT * FROM grade where userId='"+ grade.getUserId()+"'";
		if(connect()) {
			try {
				stmt = conn.createStatement();
				if(stmt != null) {
					rs = stmt.executeQuery(sql);

                    list = new ArrayList<>();

					while(rs.next()) {
						grade1.setClassIdNum(rs.getString("classIdNum"));
						grade1.setGrade(rs.getFloat("grade"));

						list.add(grade);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("�����ͺ��̽� ���ῡ �����߽��ϴ�.");
			System.exit(0);
		}
		return list;
	}
}
