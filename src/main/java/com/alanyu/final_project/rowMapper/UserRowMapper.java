package com.alanyu.final_project.rowMapper;

import com.alanyu.final_project.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setAccount(rs.getString("account"));
		user.setPassword(rs.getString("password"));
		user.setFirst_name(rs.getString("first_name"));
		user.setLast_name(rs.getString("last_name"));
		user.setAdmin_access(rs.getBoolean("admin_access"));
		user.setCreateDate(rs.getTimestamp("create_date"));
		user.setAmendDate(rs.getTimestamp("amend_date"));

		return user;
	}
}
