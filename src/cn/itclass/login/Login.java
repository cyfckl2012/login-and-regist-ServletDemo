package cn.itclass.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itclass.register.User;
import cn.itclass.utils.DataSourceUtils;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0、谨慎起见，进行编码设置
		request.setCharacterEncoding("UTF-8");

		// 1、获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		// 2、调用一个业务方法，进行用户查询
		User login = null;
		try {
			login = login(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (login != null) {
			// 用户名和密码正确
			// 跳转到网站首页
			response.sendRedirect(request.getContextPath());
		} else {
			// 用户名或密码错误
			// 跳回当前页面，输出错误信息
			request.setAttribute("loginInfo", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	// 用户查询
	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
		return user;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}