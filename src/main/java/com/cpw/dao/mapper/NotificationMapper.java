package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpw.model.LoginRequest;

public class NotificationMapper implements RowMapper<LoginRequest>{

	@Override
	public LoginRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoginRequest notification =new LoginRequest();
		 notification.setTokenId(rs.getString("TOKEN_ID"));
		notification.setUserName(rs.getString("USER_ID"));
		return notification;
	}

}
