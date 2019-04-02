package com.cpw.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.cpw.jdbc.model.Event;
import com.cpw.jdbc.model.Participants;

public class MainClass {
	public static void main(String[] args) {
		System.out.println("hello");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con=DriverManager.getConnection("jdbc:sqlserver://114.79.173.10:1433;DatabaseName=CPW", "sa", "Sqlserver#346");
			String sql="INSERT INTO EVENT (EVENT_ID,TITLE,LOCATION,ALL_DAY,FROM_DATE,"
					+ "TO_DATE,HOST,NO_OF_PARTICIPANTS,CONTACT,ACCOUNT,REPEAT,DESCRIPTION,"
					+ "REMINDER,CREATED_BY,CREATED_TIME,"
					+ "MODIFY_BY,MODIFY_TIME,NOTES,ATTACHMENTS,CONTACT_ID,ACCOUNT_ID)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
					+ "INSERT INTO PARTICIPATION(PARTICIPANT,EVENT_ID)VALUES"
					+ "(?,?)";
			PreparedStatement ps	=con.prepareStatement(sql);
			System.out.println("hello");      
			Event e=new Event();
			Participants p=new Participants();
			e.setEventId(44);
			e.setTitle("hello");
			e.setLocation("Indore");
			e.setAllDay(false);
			e.setFromDate(23);
			e.setToDate(22);
			e.setHost("abc");
			e.setNoOfParticipants(1);
			e.setContact("xyz");
			e.setAccount("abc");
			e.setRepeat("abc");
			e.setDescription("hello");
			e.setReminder("yes");
			e.setCreatedBy("surbhi");
			e.setCreatedTime(2);
			e.setModifyBy("surbhi");
			e.setModifyTime(3);
			e.setNotes("file");
			e.setAttachments("file");
			e.setContactId(2);
			e.setAccountId(1);


			p.setParticipant(1);
			p.setEventId(44);
			ps.setLong(1, e.getEventId());
			ps.setString(2, e.getTitle());
			ps.setString(3, e.getLocation());
			ps.setBoolean(4, e.isAllDay());
			ps.setLong(5, e.getFromDate());
			ps.setLong(6,e.getToDate());
			ps.setString(7, e.getHost());
			ps.setLong(8, e.getNoOfParticipants());
			ps.setString(9, e.getContact());
			ps.setString(10, e.getAccount());
			ps.setString(11, e.getRepeat());
			ps.setString(12, e.getDescription());
			ps.setString(13, e.getReminder());
			ps.setString(14,e.getCreatedBy());
			ps.setLong(15, e.getCreatedTime());
			ps.setString(16,e.getModifyBy());
			ps.setLong(17, e.getModifyTime());
			ps.setString(18, e.getNotes());
			ps.setString(19,e.getAttachments());
			ps.setLong(20, e.getContactId());
			ps.setLong(21, e.getAccountId());
			for(int i=0;i<=e.getNoOfParticipants();i++)
			{
				ps.setLong(22,p.getParticipant());
				ps.setLong(23,p.getEventId());

			}

			System.out.println("completed");
			ps.execute();
			ps.close();
			con.close();





		} catch (Exception e) {
			System.out.println("not success" +e);
		}


	}

}
