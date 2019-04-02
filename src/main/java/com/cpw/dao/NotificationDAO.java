package com.cpw.dao;

import java.util.List;

import javax.sql.DataSource;

import com.cpw.model.LoginRequest;

public interface NotificationDAO {
	public void setDataSource(DataSource ds);
	public int insertDetails(LoginRequest notification);
	public List<LoginRequest> tokenList(String userId,long id);


}
