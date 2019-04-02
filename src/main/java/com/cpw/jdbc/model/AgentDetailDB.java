package com.cpw.jdbc.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AgentDetailDB {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<AgentDetail> agentList=readAgentExcel();
		

		getDbConnection(agentList);

	}
	public static List<AgentDetail> readAgentExcel(){
		String FILE_PATH = "C:\\Users\\fission\\Downloads\\CopyAgentDetailFormat.xlsx";
		List<AgentDetail> agentList=new ArrayList<>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(FILE_PATH);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int numberOfSheets = workbook.getNumberOfSheets();
			
			//System.out.println("number of sheets" +numberOfSheets);
				//System.out.println("SHEET NAME" +workbook.getSheetAt(i).getSheetName());
				XSSFSheet sheet = workbook.getSheetAt(0);
				XSSFRow row;
				
				
				row = sheet.getRow(0);
				Iterator<Row> rows = sheet.rowIterator();
				AgentDetail agentDetail=null;
				while (rows.hasNext()) {
					agentDetail=new AgentDetail();
					row = (XSSFRow) rows.next();
					if(row!=null){
						XSSFCell port=row.getCell(0);
						XSSFCell agentAddress=row.getCell(1);
						XSSFCell lclDetail=row.getCell(2);
						XSSFCell lclDetail1=row.getCell(3);
						XSSFCell hbl=row.getCell(4);
						XSSFCell notes=row.getCell(5);
						if(port!=null){
							if(port.getCellType()==XSSFCell.CELL_TYPE_STRING && ! port.getStringCellValue().trim().isEmpty() ){
								agentDetail.setPort(port.getStringCellValue());
							}
						}
						if(agentAddress!=null){
							if(agentAddress.getCellType()==XSSFCell.CELL_TYPE_STRING && ! agentAddress.getStringCellValue().trim().isEmpty() ){
								agentDetail.setAgentAddress(agentAddress.getStringCellValue());
							}

						}
						if(lclDetail!=null){
							if(lclDetail.getCellType()==XSSFCell.CELL_TYPE_STRING && ! lclDetail.getStringCellValue().trim().isEmpty() ){
								agentDetail.setLclDetail(lclDetail.getStringCellValue());
							
						}
						
					}
						if(lclDetail1!=null){
							if(lclDetail1.getCellType()==XSSFCell.CELL_TYPE_STRING && ! lclDetail1.getStringCellValue().trim().isEmpty() ){
								agentDetail.setLalDetail1(lclDetail1.getStringCellValue());
							
						}
						
					}
						if(hbl!=null){
							if(hbl.getCellType()==XSSFCell.CELL_TYPE_STRING && ! hbl.getStringCellValue().trim().isEmpty() ){
								agentDetail.setHbl(hbl.getStringCellValue());
							
						}
						
					}
						if(notes!=null && notes.getStringCellValue()!=null ){
							if(notes.getCellType()==XSSFCell.CELL_TYPE_STRING && ! notes.getStringCellValue().trim().isEmpty() ){
								
								agentDetail.setNotes(notes.getStringCellValue());
							
						}
							
					}
					
						if(agentDetail.getPort()!=null  ){
							
						if(!agentDetail.getPort().equals("Port")  ){
							if(!agentDetail.getPort().equals("Updated:")){
							
					
						
						agentList.add(agentDetail);
						}
							}
						}
					}
				}
						
						
						
				
		
			for(AgentDetail agentDtl:agentList)
			{
				System.out.println(agentDtl);

			}

			fis.close();
			

						} catch (Exception e) {
			e.printStackTrace();
		}



		return agentList;

	}
public static void getDbConnection(List<AgentDetail> agentList) throws ClassNotFoundException, SQLException{
		
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con=DriverManager.getConnection("jdbc:sqlserver://114.79.173.10:1433;DatabaseName=CPW", "sa", "Sqlserver#346");
		for(AgentDetail agentDetail :agentList) {
		String sql="INSERT INTO AGENT_INFO_DETAIL (PORT,AGENT_ADDRESS,LCL_DETAIL,LCL_DETAIL1,"
				+ "HBL,NOTE) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, agentDetail.getPort());
			ps.setString(2, agentDetail.getAgentAddress());
			ps.setString(3, agentDetail.getLclDetail());
			ps.setString(4, agentDetail.getLalDetail1());
			ps.setString(5, agentDetail.getHbl());
			ps.setString(6, agentDetail.getNotes());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ps.execute();
		

			
		}
		
		
	}




}
