package com.cpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cpw.controller.Notification;
import com.cpw.dao.mapper.NotificationMapper;
import com.cpw.model.LoginRequest;

public class NotificationDAOImpl implements NotificationDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);

	}

	@Override
	public int insertDetails(LoginRequest notification) {
		logger.info("Entering into NotificationDao");
		CpwTemplete<LoginRequest> cpwTemplete = new CpwTempleteImpl<LoginRequest>();
		String sql="SELECT * FROM TOKEN_DETAILS WHERE USER_ID=?";
		LoginRequest notify=null;
		try {
			notify=jdbcTemplateObject.queryForObject(sql, new Object[] {notification.getUserName()},new NotificationMapper());
		} catch (Exception e) {
			notify=null;
		}try{
			int count=-1;
			System.out.println(notify);

			if(notify!=null && notify.getTokenId()!=null){

				logger.info("Enter into update details");
				String updateSql="UPDATE TOKEN_DETAILS SET TOKEN_ID=? WHERE USER_ID=?";

				return jdbcTemplateObject.update(updateSql,notification.getTokenId(),notification.getUserName());

			}
			else {
				System.out.println("Enter into insert details");
				String insertSql="INSERT INTO TOKEN_DETAILs (TOKEN_ID,USER_ID) VALUES (?,?)";
				Object[] values = new Object[2];
				values[0] = notification.getTokenId();
				values[1] = notification.getUserName();

				count = cpwTemplete.upsert(insertSql, values, jdbcTemplateObject);
				logger.debug("Record creation status: " + count);
				return count;

			}}catch(Exception e){
				return 0;
			}
	}

	@Override
	public List<LoginRequest> tokenList(String userId,long id) {
		logger.debug("Enter into tokenList");
		try{
			String sql	="SELECT * FROM TOKEN_DETAILS WHERE USER_ID !='"+userId+"'";

			System.out.println("user Id" +userId);
			logger.debug("sql" +sql);
			List<LoginRequest> tokenList =jdbcTemplateObject.query(sql, new RowMapper<LoginRequest>() {

				@Override
				public LoginRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
					LoginRequest loginrequest=new LoginRequest();
					loginrequest.setUserName(rs.getString("USER_ID"));
					loginrequest.setTokenId(rs.getString("TOKEN_ID"));
					return loginrequest;
				}
			});
			Notification notificaton=new Notification();
			System.out.println("tokenList Size" +tokenList.size());
			for(int i=0;i<tokenList.size();i++){
				try {
					
					String server_key = "AAAAiMmhW20:APA91bHuZ-XO3agCZr-YYyMJgNPuLKiNU6ZkofaOtCO32Vs7w5FzrmrDjK35dfmZhqhH-Bce4o3bukj5bF6h5XKS3ts5zb9OLI_kiIuJR22ods9UYsul0bR0VixjVTfJk3Pd9W1It-YU";
					notificaton.send_FCM_Notification(tokenList.get(i).getTokenId(), server_key, "FCM Notification",id);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return tokenList;
		}catch(Exception e){
			logger.error("Exception" +e);
			return null;

		}
	}

}
