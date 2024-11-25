package com.alanyu.final_project.dao;

import com.alanyu.final_project.models.Order;
import com.alanyu.final_project.rowMapper.OrderRowMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class OrderDao extends DBConnect{

	public Order getOrderById(Integer orderId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_GET_ORDER_BY_ID").returningResultSet("o_order", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_order_id", orderId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Order> orderList = (List<Order>) out.get("o_order");

		return orderList.stream().findAny().orElse(null);
	}

	public List<Order> getOrderByCustId(Integer custId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_GET_ORDER_BY_CUST_ID").returningResultSet("o_order", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_cust_id", custId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Order> orderList = (List<Order>) out.get("o_order");

		return orderList;
	}

	public List<Order> getAllProduct() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_GET_ALL_ORDER").returningResultSet("o_order_list", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource();
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Order> orderList = (List<Order>) out.get("o_order_list");

		return orderList;

	}

	public Integer insertOrder(Order order) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_INSERT_ORDER").returningResultSet("o_order", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_product_name",
						order.getProductName()).addValue("i_product_id", order.getProductId())
				.addValue("i_price", order.getPrice())
				.addValue("i_quantity", order.getQuantity())
				.addValue("i_cust_name", order.getCustName()).addValue("i_cust_id", order.getCustId());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		Integer orderId = (Integer) out.get("o_order_id");

		return orderId;
	}

	public Order deleteProduct(Integer orderId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_DELETE_ORDER_BY_ID").returningResultSet("o_order", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_order_id", orderId);
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Order> orderList = (List<Order>) out.get("o_order");
		if (null == orderList) {
			return null;
		} else {
			return orderList.get(0);
		}

	}

	public Order updateProductById(Order order) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName(
				"SP_UPDATE_ORDER").returningResultSet("o_order_list", new OrderRowMapper());

		SqlParameterSource in = new MapSqlParameterSource().addValue("i_order_id", order.getOrderId())
				.addValue("i_product_name",
						order.getProductName()).addValue("i_product_id", order.getOrderId())
				.addValue("i_price", order.getPrice())
				.addValue("i_quantity", order.getQuantity())
				.addValue("i_cust_id", order.getCustId())
				.addValue("i_cust_name", order.getCustName());
		Map<String, Object> out = simpleJdbcCall.execute(in);
		List<Order> orderList = (List<Order>) out.get("o_order_list");
		return orderList.stream().findAny().orElse(null);
	}
}
