package spring.web.db.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import spring.web.util.GetConfig;

public class DeleteMysql extends HttpServlet {
	private static String jndiname = GetConfig.getResourceBundle("jndi.name");
	private static String sql = GetConfig.getResourceBundle("delete.msg.all");
	private DataSource ds;

	public void deleteMsg() {
		Connection con = null;

		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(jndiname);
			con = ds.getConnection();
			Statement stmt = con.createStatement();

			System.out.println("Execute SQL: " + sql);
			stmt.executeUpdate(sql);

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}