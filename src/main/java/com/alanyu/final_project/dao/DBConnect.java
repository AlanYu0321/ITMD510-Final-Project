package com.alanyu.final_project.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

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

	public static void initialize() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

//		String sql = "create table tyu17_mall_order\n"
//				+ "(\n"
//				+ "    order_id     int auto_increment\n"
//				+ "        primary key,\n"
//				+ "    product_name varchar(128) not null,\n"
//				+ "    product_id   int          not null,\n"
//				+ "    price        int          not null,\n"
//				+ "    quantity     int          not null,\n"
//				+ "    total_price  int as ((`price` * `quantity`)) stored,\n"
//				+ "    cust_name    varchar(64)  null,\n"
//				+ "    user_id      int          not null,\n"
//				+ "    create_date  timestamp    not null,\n"
//				+ "    amend_date   timestamp    not null\n"
//				+ ");\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql);
//
//		String sql2 = "create table tyu17_mall_user\n"
//				+ "(\n"
//				+ "    user_id      int auto_increment,\n"
//				+ "    account      varchar(32)  not null,\n"
//				+ "    password     varchar(256) not null,\n"
//				+ "    create_date  timestamp    not null,\n"
//				+ "    amend_date   timestamp    not null,\n"
//				+ "    admin_access tinyint(1)   null,\n"
//				+ "    first_name   varchar(64)  not null,\n"
//				+ "    last_name    varchar(64)  not null,\n"
//				+ "    constraint user_id\n"
//				+ "        unique (user_id, account)\n"
//				+ ");\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql2);
//
//		String sql3 = "create table tyu17_product\n"
//				+ "(\n"
//				+ "    product_id   int auto_increment\n"
//				+ "        primary key,\n"
//				+ "    product_name varchar(128)  not null,\n"
//				+ "    category     varchar(32)   not null,\n"
//				+ "    image_url    varchar(256)  not null,\n"
//				+ "    price        int           not null,\n"
//				+ "    stock        int           not null,\n"
//				+ "    description  varchar(1024) null,\n"
//				+ "    create_date  timestamp     not null,\n"
//				+ "    amend_date   timestamp     not null\n"
//				+ ");\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql3);

//		String sql4 = "create\n"
//				+ "    procedure TYU17_SP_DELETE_ORDER_BY_ID(IN i_order_id int)\n"
//				+ "BEGIN\n"
//				+ "    DELETE\n"
//				+ "    FROM tyu17_mall_order\n"
//				+ "    WHERE order_id = i_order_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql4);
//
//		String sql5 = "create\n"
//				+ "    procedure TYU17_SP_DELETE_PRODUCT_BY_ID(IN i_product_id int)\n"
//				+ "BEGIN\n"
//				+ "    DELETE\n"
//				+ "    FROM tyu17_product\n"
//				+ "    WHERE product_id = i_product_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql5);
//
//		String sql6 = "create\n"
//				+ "    procedure TYU17_SP_DELETE_USER_BY_ID(IN i_user_id int)\n"
//				+ "BEGIN\n"
//				+ "    DELETE\n"
//				+ "    FROM tyu17_mall_user\n"
//				+ "    WHERE user_id = i_user_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql6);
//
//		String sql7 = "create\n"
//				+ "    procedure TYU17_SP_GET_ALL_ORDER()\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_order;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql7);
//
//		String sql8 = "create\n"
//				+ "    procedure TYU17_SP_GET_ALL_PRODUCT()\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_product;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql8);

//		String sql9 = "create\n"
//				+ "  procedure  TYU17_SP_GET_ORDER_BY_ID(IN i_order_id int)\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_order\n"
//				+ "    WHERE order_id = i_order_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql9);
//
//		String sql10 = "create\n"
//				+ "    procedure TYU17_SP_UPDATE_USER(IN i_user_id varchar(128),\n"
//				+ "                                                      IN i_account varchar(32),\n"
//				+ "                                                      IN i_password varchar(256),\n"
//				+ "                                                      IN i_first_name varchar(64),\n"
//				+ "                                                      IN i_last_name varchar(64),\n"
//				+ "                                                      IN i_admin_access tinyint(1))\n"
//				+ "BEGIN\n"
//				+ "\n"
//				+ "    UPDATE tyu17_mall_user\n"
//				+ "    SET account            = i_account,\n"
//				+ "        password           = i_password,\n"
//				+ "        first_name         = i_first_name,\n"
//				+ "        last_name          = i_last_name,\n"
//				+ "        admin_access       = i_admin_access,\n"
//				+ "        amend_date = CURRENT_TIMESTAMP()\n"
//				+ "    WHERE user_id = i_user_id;\n"
//				+ "\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_user\n"
//				+ "    WHERE user_id = i_user_id;\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql10);
//
//		String sql11 = "create\n"
//				+ "    procedure TYU17_SP_GET_PRODUCT_BY_ID(IN i_product_id int)\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_product\n"
//				+ "    WHERE product_id = i_product_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql11);
//
//		String sql12 = "create\n"
//				+ "    procedure TYU17_SP_GET_USER_BY_ACCOUNT(IN i_account varchar(32))\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_user\n"
//				+ "    WHERE account = i_account;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql12);
//
//		String sql13 = "create\n"
//				+ "    procedure TYU17_SP_GET_USER_BY_ID(IN i_user_id int)\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_user\n"
//				+ "    WHERE user_id = i_user_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql13);
//
//		String sql14 = "create\n"
//				+ "    procedure TYU17_SP_GET_USER_LIST()\n"
//				+ "BEGIN\n"
//				+ "SELECT *\n"
//				+ "FROM tyu17_mall_user;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql14);
//
//		String sql15 = "create\n"
//				+ "    procedure TYU17_SP_INSERT_ORDER(IN i_product_name varchar(128),\n"
//				+ "                                                       IN i_product_id int, IN i_price int,\n"
//				+ "                                                       IN i_quantity int,\n"
//				+ "                                                       IN i_cust_name varchar(64), IN i_user_id int,\n"
//				+ "                                                       OUT o_order_id int)\n"
//				+ "BEGIN\n"
//				+ "    INSERT INTO tyu17_mall_order (product_name, product_id, price, quantity, cust_name, user_id,\n"
//				+ "                            create_date, amend_date)\n"
//				+ "    VALUES (i_product_name, i_product_id, i_price, i_quantity, i_cust_name, i_user_id,\n"
//				+ "            CURRENT_TIMESTAMP(),\n"
//				+ "            CURRENT_TIMESTAMP());\n"
//				+ "\n"
//				+ "    SET o_order_id = LAST_INSERT_ID();\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql15);
//
//		String sql16 = "create\n"
//				+ "    procedure TYU17_SP_INSERT_PRODUCT(IN i_product_name varchar(128),\n"
//				+ "                                                         IN i_category varchar(32),\n"
//				+ "                                                         IN i_image_url varchar(256),\n"
//				+ "                                                         IN i_price int, IN i_stock int,\n"
//				+ "                                                         IN i_description varchar(1024),\n"
//				+ "                                                         OUT o_product_id int)\n"
//				+ "BEGIN\n"
//				+ "    INSERT INTO tyu17_product (product_name, category, image_url, price, stock, description, create_date,\n"
//				+ "                         amend_date)\n"
//				+ "    VALUES (i_product_name, i_category, i_image_url, i_price, i_stock, i_description, CURRENT_TIMESTAMP(),\n"
//				+ "            CURRENT_TIMESTAMP());\n"
//				+ "\n"
//				+ "    SET o_product_id = LAST_INSERT_ID();\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql16);
//
//		String sql17 = "create\n"
//				+ "    procedure TYU17_SP_INSERT_USER(IN i_account varchar(32),\n"
//				+ "                                                      IN i_password varchar(256),\n"
//				+ "                                                      IN i_first_name varchar(64),\n"
//				+ "                                                      IN i_last_name varchar(64),\n"
//				+ "                                                      IN i_admin_access tinyint(1),\n"
//				+ "                                                      OUT o_user_id int)\n"
//				+ "BEGIN\n"
//				+ "    INSERT INTO tyu17_mall_user (account, password, first_name, last_name, admin_access, create_date, amend_date)\n"
//				+ "    VALUES (i_account, i_password, i_first_name, i_last_name, i_admin_access, CURRENT_TIMESTAMP(),\n"
//				+ "            CURRENT_TIMESTAMP());\n"
//				+ "\n"
//				+ "    SET o_user_id = LAST_INSERT_ID();\n"
//				+ "END;\n"
//				+ "\n";
//
//		jdbcTemplate.execute(sql17);
//
//		String sql18 = "create\n"
//				+ "    procedure TYU17_SP_UPDATE_ORDER(IN i_order_id int,\n"
//				+ "                                                       IN i_product_name varchar(128),\n"
//				+ "                                                       IN i_product_id int, IN i_price int,\n"
//				+ "                                                       IN i_quantity int,\n"
//				+ "                                                       IN i_cust_name varchar(64), IN i_user_id int)\n"
//				+ "BEGIN\n"
//				+ "\n"
//				+ "    UPDATE tyu17_mall_order\n"
//				+ "    SET order_id = i_order_id,\n"
//				+ "        product_name = i_product_name,\n"
//				+ "        product_id = i_product_id,\n"
//				+ "        price        = i_price,\n"
//				+ "        quantity     = i_quantity,\n"
//				+ "        cust_name    = i_cust_name,\n"
//				+ "        user_id      = i_user_id,\n"
//				+ "        amend_date   = CURRENT_TIMESTAMP()\n"
//				+ "    WHERE order_id = i_order_id;\n"
//				+ "\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_order\n"
//				+ "    WHERE order_id = i_order_id;\n"
//				+ "END;\n"
//				+ "\n";
//		jdbcTemplate.execute(sql18);
//
//		String sql19 = "create\n"
//				+ "    procedure TYU17_SP_UPDATE_PRODUCT(IN i_product_id int,\n"
//				+ "                                                         IN i_product_name varchar(128),\n"
//				+ "                                                         IN i_category varchar(32),\n"
//				+ "                                                         IN i_image_url varchar(256),\n"
//				+ "                                                         IN i_price int, IN i_stock int,\n"
//				+ "                                                         IN i_description varchar(1024))\n"
//				+ "BEGIN\n"
//				+ "\n"
//				+ "    UPDATE tyu17_product\n"
//				+ "    SET product_name       = i_product_name,\n"
//				+ "        category           = i_category,\n"
//				+ "        image_url          = i_image_url,\n"
//				+ "        price              = i_price,\n"
//				+ "        stock              = i_stock,\n"
//				+ "        description        = i_description,\n"
//				+ "        amend_date = CURRENT_TIMESTAMP()\n"
//				+ "    WHERE product_id = i_product_id;\n"
//				+ "\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_product\n"
//				+ "    WHERE product_id = i_product_id;\n"
//				+ "END;\n"
//				+ "\n";
//		jdbcTemplate.execute(sql19);

//		String sql20 = "create\n"
//				+ " procedure  TYU17_SP_GET_ORDER_BY_USER_ID(IN i_user_id int)\n"
//				+ "BEGIN\n"
//				+ "    SELECT *\n"
//				+ "    FROM tyu17_mall_order\n"
//				+ "    WHERE user_id = i_user_id;\n"
//				+ "\n"
//				+ "END;\n"
//				+ "\n";
//		jdbcTemplate.execute(sql20);


	}

}
