package com.cpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cpw.dao.mapper.EventMapper;
import com.cpw.jdbc.model.Event;
import com.cpw.jdbc.model.Participants;
import com.cpw.services.NotificationImpl;


public class EventDAOImpl implements EventDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	List<Event>	EventParticipantList;
	int countParticipant =0;


	@Override
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject=new JdbcTemplate(ds);


	}

	@Override
	public int upsertEvent(Event event,String userId) {


		logger.info("Entering into event DAO");

		CpwTemplete<Event> cpwTemplete = new CpwTempleteImpl<Event>();
		String q1 = "SELECT *FROM EVENT WHERE EVENT_ID=?";


		Event e = null;

		try {
			e = jdbcTemplateObject.queryForObject(q1, new Object[] { event.getEventId()},new EventMapper());

		} catch (EmptyResultDataAccessException e1) {
			e = null;
		}
		try {

			int count = -1;
			if (e!=null && e.getEventId() == event.getEventId()) {
				logger.debug("UPDATE values" + event.getEventId());
				StringBuilder sb=new StringBuilder();
				if(event.getNoOfParticipants()==event.getParticipants().size()){
					sb.append("DELETE FROM PARTICIPATION WHERE EVENT_ID="+event.getEventId()+"");
					List<Participants> list=event.getParticipants();

					if(list.size()==event.getNoOfParticipants()){
						for(Participants participants:list){

							if(participants.getEventId()==event.getEventId()&& event.getNoOfParticipants()!=0){
								sb.append("INSERT INTO PARTICIPATION(PARTICIPANT,EVENT_ID,TYPE)VALUES("+participants.getParticipant()+","+participants.getEventId()+",'"+participants.getType()+"')");
								countParticipant++;

							}}
						if(countParticipant==event.getNoOfParticipants()){
							sb.append( "UPDATE EVENT SET TITLE=?,LOCATION=?,ALL_DAY=?,FROM_DATE=?,TO_DATE=?,"
									+ "HOST=?,NO_OF_PARTICIPANTS=?,CONTACT=?,ACCOUNT=?,REPEAT=?,DESCRIPTION=?,"
									+ "REMINDER=?,CREATED_BY=?,CREATED_TIME=?,MODIFY_BY=?,MODIFY_TIME=?,"
									+ "NOTES=?,ATTACHMENTS=?,CONTACT_ID=?,ACCOUNT_ID=?,FROM_TIME=?,"
									+ "TO_TIME=?,LEAD_ID=?,LEAD_NAME=? WHERE EVENT_ID=? ");
							count= jdbcTemplateObject.update(sb.toString(), event.getTitle(), 
									event.getLocation(),
									event.isAllDay(),
									event.getFromDate(),
									event.getToDate(), 
									event.getHost(),
									event.getNoOfParticipants(),
									event.getContact(),
									event.getAccount(),
									event.getRepeat(),
									event.getDescription(),
									event.getReminder(),
									event.getCreatedBy(),
									event.getCreatedTime(),
									event.getModifyBy(),
									event.getModifyTime(),
									event.getNotes(),
									event.getAttachments(),
									event.getContactId(),
									event.getAccountId(),
									event.getFromTime(),
									event.getToTime(),
									event.getLeadId(),
									event.getLeadName(),
									event.getEventId());

						}  }	
				}else {
					logger.info("Invalid Values inserted in participants");
				}}else {
					StringBuilder sb = new StringBuilder();
					Participants participants;


					if(	event.getNoOfParticipants()==event.getParticipants().size()){       
						List<Participants> list=event.getParticipants();


						logger.debug("Participants List" +list+list.size()+"No of participants"+event.getNoOfParticipants());
						if(list.size()==event.getNoOfParticipants()){
							for(int i=0;i<list.size();i++){
								participants=list.get(i);
								if(participants.getEventId()==event.getEventId()&& event.getNoOfParticipants()!=0)
								{
									sb.append("INSERT INTO PARTICIPATION(PARTICIPANT,EVENT_ID,TYPE)VALUES("+participants.getParticipant()+","+participants.getEventId()+",'"+participants.getType()+"')");
									countParticipant++;
								}



							}




							Object[] values = new Object[25];
							values[0]  = event.getEventId();
							values[1]  = event.getTitle();
							values[2]  = event.getLocation();
							values[3]  = event.isAllDay();
							values[4]  = event.getFromDate();
							values[5]  = event.getToDate();
							values[6]  = event.getHost();
							values[7]  = event.getNoOfParticipants();
							values[8]  = event.getContact();
							values[9]  = event.getAccount();
							values[10] = event.getRepeat();
							values[11] = event.getDescription();
							values[12] = event.getReminder();
							values[13] = event.getCreatedBy();
							values[14] = event.getCreatedTime();
							values[15] = event.getModifyBy();
							values[16] = event.getModifyTime();
							values[17] = event.getNotes();
							values[18] = event.getAttachments();
							values[19]=event.getContactId();
							values[20]=event.getAccountId();
							values[21]=event.getFromTime();
							values[22]=event.getToTime();
							values[23]=event.getLeadId();
							values[24]=event.getLeadName();

							if(countParticipant==event.getNoOfParticipants()){

								sb.append( "INSERT INTO EVENT (EVENT_ID,TITLE,LOCATION,ALL_DAY,FROM_DATE,"
										+ "TO_DATE,HOST,NO_OF_PARTICIPANTS,CONTACT,ACCOUNT,REPEAT,DESCRIPTION,"
										+ "REMINDER,CREATED_BY,CREATED_TIME,"
										+ "MODIFY_BY,MODIFY_TIME,NOTES,ATTACHMENTS,CONTACT_ID,ACCOUNT_ID,FROM_TIME,TO_TIME,LEAD_ID,LEAD_NAME)"
										+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");



								logger.debug("INSERT values" + values[1]);	

								count = cpwTemplete.upsert(sb.toString(),values,jdbcTemplateObject);
							}
						}
						logger.debug("Record creation status: " + count);

					}else {
						logger.info("Invalid Values Inserted of participants");
					}
				}
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,event.getEventId());
			return count;
		}catch (DataAccessException e1) {

			logger.debug("No Values inserted " +e1);
			e1.printStackTrace();
			return 0;
		}}



	@Override
	public List<Event> eventList(long eventId) {


		logger.debug("Entering into eventList " + eventId);
		try {
			CpwTemplete<Event> cpwTemplete = new CpwTempleteImpl<Event>();
			
              if(eventId==-1){
            	  final String sql="SELECT * FROM EVENT AS e";
            	  List<Event> eventList=cpwTemplete.getRecordList(sql, jdbcTemplateObject, new RowMapper<Event>() {

					@Override
					public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
						 Event event=new Event();
							event.setEventId(rs.getLong("EVENT_ID"));
							event.setTitle(rs.getString("TITLE"));
							event.setLocation(rs.getString("LOCATION"));
							event.setAllDay(rs.getBoolean("ALL_DAY"));
							event.setFromDate(rs.getLong("FROM_DATE"));
							event.setToDate(rs.getLong("TO_DATE"));
							event.setHost(rs.getString("HOST"));
							event.setNoOfParticipants(rs.getInt("NO_OF_PARTICIPANTS"));
							event.setContact(rs.getString("CONTACT"));
							event.setAccount(rs.getString("ACCOUNT"));
							event.setRepeat(rs.getString("REPEAT"));
							event.setDescription(rs.getString("DESCRIPTION"));
							event.setReminder(rs.getString("REMINDER"));
							event.setCreatedBy(rs.getString("CREATED_BY"));
							event.setCreatedTime(rs.getLong("CREATED_TIME"));
							event.setModifyBy(rs.getString("MODIFY_BY"));
							event.setModifyTime(rs.getLong("MODIFY_TIME"));
							event.setNotes(rs.getString("NOTES"));
							event.setAttachments(rs.getString("ATTACHMENTS"));
							event.setContactId(rs.getLong("CONTACT_ID"));
							event.setAccountId(rs.getLong("ACCOUNT_ID"));
							event.setFromTime(rs.getLong("FROM_TIME"));
							event.setToTime(rs.getLong("TO_TIME"));
							event.setLeadId(rs.getLong("LEAD_ID"));
							event.setLeadName(rs.getString("LEAD_NAME"));
							 
			            	  for(int i=0;i<event.getNoOfParticipants();i++){
			            		 // Event eventRow=eventList.get(i);
			      			String sql1="SELECT * FROM PARTICIPATION WHERE EVENT_ID="+event.getEventId();
			            List<Participants>	parti= jdbcTemplateObject.query(sql1,new RowMapper<Participants>() {

								@Override
								public Participants mapRow(ResultSet rs, int rowNum) throws SQLException {
									//Event event=new Event();
									Participants p=new Participants();
									p.setParticipant(rs.getLong("PARTICIPANT"));
									p.setEventId(rs.getLong("EVENT_ID"));
									p.setType(rs.getString("TYPE"));
							/*List<Participants> participant=new ArrayList<Participants>();
								
								participant.add(p);
								System.out.println("participant id" +participant);
								eventRow.setParticipants(participant);*/
									return p;
								}
							});
			      		
			      			event.setParticipants(parti);
			      		
								
			            	  }
						return event;
					}
				});
            	 
            	  System.out.println("Event List" +eventList);
            	  
            	  return eventList;
 }else{
			final String sql="SELECT * FROM EVENT WHERE EVENT_ID>?";
			// RowMapper<Event> mapper = (RowMapper<Event>) new EventMapper();
			List<Event>	eventList=jdbcTemplateObject.query(sql,new Object[]{eventId},new RowMapper<Event>() {

				@Override
				public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
					 Event event=new Event();
						event.setEventId(rs.getLong("EVENT_ID"));
						event.setTitle(rs.getString("TITLE"));
						event.setLocation(rs.getString("LOCATION"));
						event.setAllDay(rs.getBoolean("ALL_DAY"));
						event.setFromDate(rs.getLong("FROM_DATE"));
						event.setToDate(rs.getLong("TO_DATE"));
						event.setHost(rs.getString("HOST"));
						event.setNoOfParticipants(rs.getInt("NO_OF_PARTICIPANTS"));
						event.setContact(rs.getString("CONTACT"));
						event.setAccount(rs.getString("ACCOUNT"));
						event.setRepeat(rs.getString("REPEAT"));
						event.setDescription(rs.getString("DESCRIPTION"));
						event.setReminder(rs.getString("REMINDER"));
						event.setCreatedBy(rs.getString("CREATED_BY"));
						event.setCreatedTime(rs.getLong("CREATED_TIME"));
						event.setModifyBy(rs.getString("MODIFY_BY"));
						event.setModifyTime(rs.getLong("MODIFY_TIME"));
						event.setNotes(rs.getString("NOTES"));
						event.setAttachments(rs.getString("ATTACHMENTS"));
						event.setContactId(rs.getLong("CONTACT_ID"));
						event.setAccountId(rs.getLong("ACCOUNT_ID"));
						event.setFromTime(rs.getLong("FROM_TIME"));
						event.setToTime(rs.getLong("TO_TIME"));
						event.setLeadId(rs.getLong("LEAD_ID"));
						event.setLeadName(rs.getString("LEAD_NAME"));
						 
		            	  for(int i=0;i<event.getNoOfParticipants();i++){
		            		 // Event eventRow=eventList.get(i);
		      			String sql1="SELECT * FROM PARTICIPATION WHERE EVENT_ID="+event.getEventId();
		            List<Participants>	parti= jdbcTemplateObject.query(sql1,new RowMapper<Participants>() {

							@Override
							public Participants mapRow(ResultSet rs, int rowNum) throws SQLException {
								//Event event=new Event();
								Participants p=new Participants();
								p.setParticipant(rs.getLong("PARTICIPANT"));
								p.setEventId(rs.getLong("EVENT_ID"));
								p.setType(rs.getString("TYPE"));
						/*List<Participants> participant=new ArrayList<Participants>();
							
							participant.add(p);
							System.out.println("participant id" +participant);
							eventRow.setParticipants(participant);*/
								return p;
							}
						});
		      		
		      			event.setParticipants(parti);
		      		
							
		            	  }
					return event;
				}
			});
		 

			return eventList;
 }
		} catch (EmptyResultDataAccessException e) {
			logger.error("No eventList in system");
			return null;
		}


	}

	
	@Override
	public int removeEvent(long eventId,String userId) {
		logger.debug("Entering into remove event");

		String sql="DELETE FROM EVENT WHERE EVENT_ID=?";

		try {
			logger.debug("list in the system");
			int i =jdbcTemplateObject.update(sql, eventId);
			System.out.println(i);
			NotificationImpl notificationDAO=new NotificationImpl();
		      notificationDAO.tokenList(userId,eventId);
			return i;
		} catch (DataAccessException e) {

			logger.error("No Event available in system coresponding to Event id: " + e);
			return 0;
		}


	}

	@Override
	public int removeParticipant(long eventId) {
		logger.debug("Entering into remove participant" +eventId);
  
           
		String sql="DELETE FROM PARTICIPATION WHERE EVENT_ID=?";

		try {
			logger.debug("list in the system");
			int i =jdbcTemplateObject.update(sql, eventId);

			return i;
		} catch (DataAccessException e) {

			logger.error("No Event available in system coresponding to Event id: " + e);
			return 0;
		}



	}
	public int removeType(long participantId){
		logger.info("Enetring remove participant List");
		
		String sql="DELETE FROM PARTICIPATION WHERE PARTICIPANT=?";
		try {
			int i=jdbcTemplateObject.update(sql, participantId);
			String sql1="SELECT * FROM PARTICIPATION WHERE EVENT_ID=?";
		List<Participants> participants	=jdbcTemplateObject.query(sql1,new Object[]{participantId}, new RowMapper<Participants>() {

				@Override
				public Participants mapRow(ResultSet rs, int rowNum) throws SQLException {
					Participants participant=new Participants();
					participant.setParticipant(rs.getLong("PARTICIPANT"));
					participant.setEventId(rs.getLong("EVENT_ID"));
					participant.setType(rs.getString("TYPE"));
					return participant;
				}
			});
		logger.debug("Size of participant List" +participants.size());
		String updateSql="UPDATE EVENT SET NO_OF_PARTICIPANTS="+participants.size()+" WHERE EVENT_ID=?";
		jdbcTemplateObject.update(updateSql, participantId);
			
			
			
			
			
			
			return i;
			
		} catch (Exception e) {
			logger.error("NO participantList in system corresponding to participantId:"+e);
		     return 0;
		}
		
	}




}
