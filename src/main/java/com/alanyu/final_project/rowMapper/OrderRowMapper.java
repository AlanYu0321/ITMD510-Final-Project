package com.alanyu.final_project.rowMapper;

import com.alanyu.final_project.models.Order;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

		Order order = new Order();
		order.setOrderId(rs.getInt("order_id"));
		order.setProductName(rs.getString("product_name"));
		order.setProductId(Integer.valueOf(rs.getString("product_Id")));
		order.setPrice(rs.getInt("price"));
		order.setQuantity(rs.getInt("quantity"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setCustName(rs.getString("cust_name"));
		order.setUserId(rs.getInt("user_id"));
		order.setCreateDate(rs.getTimestamp("create_date"));
		order.setAmendDate(rs.getTimestamp("amend_date"));

		return order;
	}
}
