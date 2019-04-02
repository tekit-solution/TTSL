package com.cpw.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.Participants;

public class ParticipationMapper implements RowMapper<Participants>{

	@Override
	public Participants mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Participants participation=new Participants();
		  participation.setParticipant(rs.getLong("PARTICIPANT"));
		  participation.setEventId(rs.getLong("EVENT_ID"));
		  participation.setType(rs.getString("TYPE"));
		return participation;
	}

}
