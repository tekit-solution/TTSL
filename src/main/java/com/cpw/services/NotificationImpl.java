package com.cpw.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.NotificationDAOImpl;
import com.cpw.model.LoginRequest;

public class NotificationImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext context  = new ClassPathXmlApplicationContext("Beans.xml");
	NotificationDAOImpl notificationDAOImpl = (NotificationDAOImpl) context.getBean("notificationDAOImpl");
	public int insertDetails(LoginRequest notificationRequest)
	{
		logger.info("Entering into notification");
		int notificationDao=notificationDAOImpl.insertDetails(notificationRequest);
		
		return notificationDao;

	}
	/*private LoginRequest detail(LoginRequest notificationRequest)
	{
		Notification notification=new Notification();
		notification.setTokenId(notificationRequest.getTokenId());
		notification.setUserId(notificationRequest.getUserId());
		return notification;

	}*/
	public List<LoginRequest> tokenList(String userId,long id){
	List<LoginRequest> tokenList=notificationDAOImpl.tokenList(userId,id);
	/*	Notification notificaton=new Notification();
		System.out.println("tokenList Size" +tokenList.size());
		for(int i=0;i<tokenList.size();i++){
			try {
				
				String server_key = "AAAAiMmhW20:APA91bHuZ-XO3agCZr-YYyMJgNPuLKiNU6ZkofaOtCO32Vs7w5FzrmrDjK35dfmZhqhH-Bce4o3bukj5bF6h5XKS3ts5zb9OLI_kiIuJR22ods9UYsul0bR0VixjVTfJk3Pd9W1It-YU";
				notificaton.send_FCM_Notification(tokenList.get(i).getTokenId(), server_key, "FCM Notification");
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return tokenList;
		
	}
}
