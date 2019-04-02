package com.cpw.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.SyncAllDetailDaoImpl;
import com.cpw.model.SyncAllDetailRequest;

public class SyncAllDeatilImpl {
	public SyncAllDeatilImpl() {
		// TODO Auto-generated constructor stub
	}
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	private final SyncAllDetailDaoImpl syncDao=(SyncAllDetailDaoImpl)context.getBean("syncAllDetailDaoImpl");
	String msg = "null";
	String msg1 ="null";
		public String syncAllDetail(SyncAllDetailRequest syncAll)
	{
		logger.info("Entering into syncImpl");

           if(syncAll!=null){

		msg =syncDao.insertAccount(syncAll);
	    if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Account" + msg1);
	    }

		msg = syncDao.insertLead(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Lead" + msg1);
	    }
		msg=syncDao.insertContact(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Contact" + msg1);
	    }
		msg=syncDao.insertDeal(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Deal" + msg1);
	    }

		msg=syncDao.insertTask(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Task" + msg1);
	    }
		msg = syncDao.insertEvent(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Event" + msg1);
		}
		if(msg1.equals("Matched") ){
		msg1=syncDao.insertParticipant();
		}
		msg=syncDao.insertCall(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("Call" + msg1);
	    }
		msg=syncDao.insertCustChallenge(syncAll);
		if(!msg.equals("null")){
	    	msg1 = msg;
	    	logger.debug("CustomerChallenge" + msg1);
	    }
		msg=syncDao.insertImage(syncAll);
		if(!msg.equals("null")){
			msg1=msg;
			logger.debug("ImageStore" +msg1);
		}
           }

		return msg1;
  




		

	}

}
