package cn.itclass.register;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import cn.itclass.utils.DataSourceUtils;

public class Register extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取数据
		// String uesername = request.getParameter("username");
		// String password = request.getParameter("password");

		// 2、将散装的数据封装到javaBean中
		// User user = new User();
		// user.setUsername(uesername);太麻烦，使用BeanUtils

		// 设置编码,只适合post方式
		request.setCharacterEncoding("UTF-8");

		// get方式的乱码解决办法,post同样适用  以下三行为get提交方式的防乱码
		// 但是一般来说，注册不会用到get方式提交，所以我们采用上面的方法，更加简便
		//String uesername = request.getParameter("username");
		// 先用ISO8859-1编码，然后在使用UTF8解码
		//uesername = new String(uesername.getBytes("ISO8859-1"),"UTF-8");

		// 使用BeanUtils进行自动映射封装
		// 工作原理：将map中的数据，根据key与实体的属性的对应关系封装
		// 只要key的名字与实体的属性的名字一样，就自动封装到实体中
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// 现在这个位置，user对象已经封装好了
		// 需要手动封装uid---uuid,随机的不重复的字符串,32位，java生成后是36位
		// 这个地方是给主键uid赋值，数据库可以用自增长，但是在正式项目中，很少会用到自增长，基本都是采用uuid的方式进行赋值。
		user.setUid(UUID.randomUUID().toString());

		// 3、将参数传给一个业务操作方法

		try {
			regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 到此处认为注册成功，跳转到登录页面----转发或者重定向
		// request.getRequestDispatcher("/WEB15/LoginConfig.jsp").forward(request, response);转发不会改变地址
		// 如果用户刷新，就会重新刷新register，增加服务器负担，而且会出问题，重定向更合适

		// response.sendRedirect("/WEB_TEST/login.jsp");//如果之后改了项目名，就会出问题

		response.sendRedirect(request.getContextPath() + "/login.jsp");//用getContextPath()动态获取地址

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 注册的方法
		public void regist(User user) throws SQLException {
			// 操作数据库
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
			runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), null,
					user.getBirthday(), user.getSex(), null, null);
		}
}