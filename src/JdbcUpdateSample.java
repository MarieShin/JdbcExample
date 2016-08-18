import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUpdateSample {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. establish connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "1234");
			// 2. prepare query for mysql
			String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?,?,?,?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "shin");
			pstmt.setString(2, "USA");	// CountryCode가 foreign키로 적용되어 있음 --> 임시적으로 지정해준다.
			pstmt.setString(3, "kasan");
			pstmt.setInt(4, 30000);
			pstmt.executeUpdate();
			
//			conn.commit();	// 자바는 자동적으로 커밋이 적용된다.

			System.out.println("insert ok...");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert fail...");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("insert close...");
		}
	}
}
