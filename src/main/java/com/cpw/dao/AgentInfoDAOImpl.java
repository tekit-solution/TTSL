package com.cpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.AgentDetail;

public class AgentInfoDAOImpl implements AgentInfoDAO {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	@Override
	public void setDataSource(DataSource ds) {
		this.jdbcTemplateObject = new JdbcTemplate(ds);
		
	}

	@Override
	public List<AgentDetail> agentList(String port) {
		logger.debug("Entering into agentList" + port);
		try{
		final String trackingSql = "SELECT * FROM AGENT_INFO_DETAIL WHERE PORT=?";
		List<AgentDetail> agentList = jdbcTemplateObject.query(trackingSql, new Object[] { port },
				new RowMapper<AgentDetail>() {

					@Override
					public AgentDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
						AgentDetail agentDetail=new AgentDetail();
						agentDetail.setPort(rs.getString("PORT"));
						agentDetail.setAgentAddress(rs.getString("AGENT_ADDRESS"));
						agentDetail.setLclDetail(rs.getString("LCL_DETAIL"));
						agentDetail.setLalDetail1(rs.getString("LCL_DETAIL1"));
						agentDetail.setHbl(rs.getString("HBL"));
						agentDetail.setNotes(rs.getString("NOTE"));
						return agentDetail;
					}

		});
		return agentList;
		}catch(Exception e){
			logger.error("No agentList in system" +e);
			return null;
			}
		}

}
