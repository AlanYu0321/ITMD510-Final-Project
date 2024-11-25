package com.alanyu.final_project.models;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class User {

	private Integer userId;
	private String account;
	private String password;
	private String first_name;
	private String last_name;
	private Boolean admin_access;
	private Timestamp createDate;
	private Timestamp amendDate;

}
