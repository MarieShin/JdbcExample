import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcQuerySample {
	public static void main(String[] args) {

		// 1. 데이터베이스에 접속하기
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "1234");
			System.out.println("connection ok...");

			String sql = "select name, CountryCode, District, Population from city where Population > 1900000 and Population < 2000000;";

			PreparedStatement stmt = conn.prepareStatement(sql);	// 실행속도를 높이기 위해서 쿼리자체를 컴파일함
			ResultSet rs = stmt.executeQuery(); 					// execute함수에 의해서 db로 전달

			while (rs.next()) {
				String name = rs.getString("name");
				String countryCode = rs.getString("CountryCode");
				String district = rs.getString("District");
				String population = rs.getString("Population");
			
				System.out.println("[" + name + " " + countryCode + " " + district + " " + population + "]");
			}

		} catch (SQLException e) {
			System.out.println("connection fail...");
		} finally { // 반드시 실행된다.
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
			System.out.println("connection closed...");
		}

	}

}
