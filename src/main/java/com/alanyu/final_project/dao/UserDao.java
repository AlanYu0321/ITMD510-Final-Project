package com.alanyu.final_project.dao;

import com.alanyu.final_project.models.User;
import com.alanyu.final_project.rowMapper.UserRowMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class UserDao {

	// HikariCP DataSource
	private static HikariDataSource dataSource;

	// Initialize the HikariCP DataSource
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(
				"jdbc:mysql://localhost:3306/mall?serverTimezone=America/Chicago&characterEncoding=utf-8");
		config.setUsername("root");
		config.setPassword("password");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");

		// Optional HikariCP configuration settings
		config.setMaximumPoolSize(10);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(600000);
		config.setMaxLifetime(1800000);

		dataSource = new HikariDataSource(config);
	}

	public User getUserById(Integer userId) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_GET_USER_BY_ID")
				.returningResultSet("o_user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_user_id", userId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<User> userList = (List<User>) out.get("o_user");

		return userList.stream().findAny().orElse(null);
	}

	public List<User> getUserList() {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_GET_USER_LIST")
				.returningResultSet("o_user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource();
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<User> userList = (List<User>) out.get("o_user");

		return userList;
	}

	public User getUserByAccount(String account) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_GET_USER_BY_ACCOUNT")
				.returningResultSet("o_user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_account", account);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<User> userList = (List<User>) out.get("o_user");

		return userList.stream().findAny().orElse(null);
	}

	public Integer insertUser(User user) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_INSERT_USER")
				.returningResultSet("user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_user_id", user.getUserId())
				.addValue("i_account", user.getAccount()).addValue("i_password", user.getPassword())
				.addValue("i_first_name", user.getFirst_name()).addValue("i_last_name", user.getLast_name())
				.addValue("i_admin_access", user.getAdmin_access())
				.addValue("i_create_date", user.getCreateDate())
				.addValue("i_amend_date", user.getAmendDate());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		Integer userId = (Integer) out.get("o_user_id");

		return userId;
	}

	public void deleteUser(Integer UserId) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_DELETE_USER_BY_ID")
				.returningResultSet("user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_user_id", UserId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
	}

	public User updateUserById(User user) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
						"SP_UPDATE_USER")
				.returningResultSet("o_user", new UserRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_user_id", user.getUserId())
				.addValue("i_account", user.getAccount()).addValue("i_password", user.getPassword())
				.addValue("i_first_name", user.getFirst_name()).addValue("i_last_name", user.getLast_name())
				.addValue("i_admin_access", user.getAdmin_access())
				.addValue("i_create_date", user.getCreateDate())
				.addValue("i_amend_date", user.getAmendDate());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<User> userList = (List<User>) out.get("o_user");

		return userList.stream().findAny().orElse(null);
	}


}
