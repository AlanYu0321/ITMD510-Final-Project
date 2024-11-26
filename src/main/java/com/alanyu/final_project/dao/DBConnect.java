package com.alanyu.final_project.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnect {

	// HikariCP DataSource
	public static HikariDataSource dataSource;

	// Initialize the HikariCP DataSource
	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(
//				"jdbc:mysql://localhost:3306/mall?serverTimezone=America/Chicago&characterEncoding=utf-8");
				"jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false");
		config.setUsername("fp510");
		config.setPassword("510");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");

		// Optional HikariCP configuration settings
		config.setMaximumPoolSize(10);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(600000);
		config.setMaxLifetime(1800000);

		dataSource = new HikariDataSource(config);
	}

}
