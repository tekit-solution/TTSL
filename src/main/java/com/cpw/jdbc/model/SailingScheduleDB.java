package com.cpw.jdbc.model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class SailingScheduleDB {

	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
          
	List<Sail_schedule> sailingList=readExcel();
	

	getDbConnection(sailingList);

	 

	}
	
	public static List<Sail_schedule> readExcel(){
		String FILE_PATH = "C:\\Users\\fission\\Downloads\\sailingschedulechennai.xls";
		List<Sail_schedule> scheduleList = new ArrayList<>();

		FileInputStream fis = null;

		try {

			fis = new FileInputStream(FILE_PATH);



			// Using XSSF for xlsx format, for xls use HSSF

			HSSFWorkbook workbook = new HSSFWorkbook(fis);



			int numberOfSheets = workbook.getNumberOfSheets();



			//looping over each workbook sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row;

			row = sheet.getRow(0);

			Iterator<Row> rows = sheet.rowIterator();
			Sail_schedule sailingSchedule = null;


			while (rows.hasNext()) {
				sailingSchedule = new Sail_schedule();

				row = (HSSFRow) rows.next();
				if(row!=null){
					HSSFCell origin_Location = row.getCell(0);
					HSSFCell port_Of_Loading = row.getCell(1);
					HSSFCell terminal = row.getCell(2);
					HSSFCell port_Of_Discharge = row.getCell(3);
					HSSFCell sector = row.getCell(4);
					HSSFCell service = row.getCell(5);
					HSSFCell vessel = row.getCell(6);
					HSSFCell voy = row.getCell(7);
					HSSFCell from_Eta = row.getCell(8);
					HSSFCell from_Etd = row.getCell(9);
					HSSFCell cfs_Cut_off = row.getCell(10);
					HSSFCell time = row.getCell(11);

					HSSFCell last_Stuffing = row.getCell(12);
					HSSFCell day = row.getCell(13);
					HSSFCell transit = row.getCell(14);
					HSSFCell remark = row.getCell(15);
					HSSFCell m_Vessel = row.getCell(16);
					HSSFCell voyy = row.getCell(17);
					HSSFCell to_Etd = row.getCell(18);
					HSSFCell to_Eta = row.getCell(19);


					if (origin_Location != null) {

						{
							if(origin_Location.getCellType()==HSSFCell.CELL_TYPE_STRING && ! origin_Location.getStringCellValue().trim().isEmpty() ){
								sailingSchedule.setOrigin_Location(origin_Location.getStringCellValue());
							}else {
								//sailingSchedule.setOrigin_Location(origin_Location.getNumericCellValue()+"");
							}
						}}
					if(port_Of_Loading!=null ){


						if(port_Of_Loading.getCellType()==HSSFCell.CELL_TYPE_STRING && ! port_Of_Loading.getStringCellValue().trim().isEmpty() ){
							sailingSchedule.setPort_Of_Loading(port_Of_Loading.getStringCellValue());
						}else {
							//sailingSchedule.setPort_Of_Loading(port_Of_Loading.getNumericCellValue()+"");
						}
					}
					if(terminal!=null){

						if(terminal.getCellType()==HSSFCell.CELL_TYPE_STRING && ! terminal.getStringCellValue().trim().isEmpty() ){
							sailingSchedule.setTerminal(terminal.getStringCellValue());
						}else {
							//sailingSchedule.setTerminal(terminal.getNumericCellValue()+"");
						}
					}
					if(port_Of_Discharge!=null){

						if(port_Of_Discharge.getCellType()==HSSFCell.CELL_TYPE_STRING && ! port_Of_Discharge.getStringCellValue().trim().isEmpty() ){
							sailingSchedule.setPort_Of_Discharge(port_Of_Discharge.getStringCellValue());
						}else {
							//sailingSchedule.setPort_Of_Discharge(port_Of_Discharge.getNumericCellValue()+"");
						}
					}
					if(sector!=null){
						if(sector.getCellType()==HSSFCell.CELL_TYPE_STRING && ! sector.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setSector(sector.getStringCellValue());
						}else {
							//sailingSchedule.setSector(sector.getNumericCellValue()+"");
						}
					}
					if(service!=null){
						if(service.getCellType()==HSSFCell.CELL_TYPE_STRING && ! service.getStringCellValue().trim().isEmpty() ){
							sailingSchedule.setService(service.getStringCellValue());
						}else {
							//	sailingSchedule.setService(sector.getNumericCellValue()+"");
						}
					}
					if(vessel!=null ){
						if(vessel.getCellType()==HSSFCell.CELL_TYPE_STRING && ! vessel.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setVessel(vessel.getStringCellValue());
						}
						if(vessel.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
							sailingSchedule.setVessel(vessel.getStringCellValue());
						}
					}
					if(voy!=null){
						if(voy.getCellType()==HSSFCell.CELL_TYPE_STRING && ! voy.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setVoy(voy.getStringCellValue());
						}else {
							sailingSchedule.setVoy(voy.getNumericCellValue()+"");
						}
					}
					if(from_Eta!=null){
						/*sailingSchedule.setFrom_Eta(from_Eta.getStringCellValue());*/
						if(!from_Eta.getStringCellValue().equals("ETA"))
						if(from_Eta.getCellType()==HSSFCell.CELL_TYPE_STRING && ! from_Eta.getStringCellValue().trim().isEmpty() ){
							
							String fromEta=from_Eta.getStringCellValue();
							
							//System.out.println(from_Eta.getStringCellValue());
							//System.out.println(fromEta);
							  long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(fromEta).getTime();  
							 // System.out.println("FROM ETA"+date1);  
							sailingSchedule.setFrom_Eta(date1);
							}
						if(from_Eta.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
							String fromEta=from_Eta.getStringCellValue();
							 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(fromEta).getTime();  
							 // System.out.println("FROM ETA"+date1);  
							sailingSchedule.setFrom_Eta(date1);
							/*sailingSchedule.setFrom_Eta(from_Eta.getStringCellValue());*/
						}
					}
					if(from_Etd!=null){
                        if(!from_Etd.getStringCellValue().equals("ETD")){
						if(from_Etd.getCellType()==HSSFCell.CELL_TYPE_STRING && ! from_Etd.getStringCellValue().trim().isEmpty()){
							String fromEtd=from_Etd.getStringCellValue();
							 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(fromEtd).getTime();  
							//  System.out.println("FROM ETD"+date1);  
							sailingSchedule.setFrom_Etd(date1);
							
							
							//sailingSchedule.setFrom_Etd(from_Etd.getStringCellValue());
						}
						if(from_Etd.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
							String fromEtd=from_Etd.getStringCellValue();
							 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(fromEtd).getTime();  
							 
							  //System.out.println("FROM ETD"+date1);  
							sailingSchedule.setFrom_Etd(date1);
							//sailingSchedule.setFrom_Etd(from_Etd.getStringCellValue());
						}

					}}
					if(cfs_Cut_off!=null){
						if(cfs_Cut_off.getCellType()==HSSFCell.CELL_TYPE_STRING && ! cfs_Cut_off.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setCfs_Cut_off(cfs_Cut_off.getStringCellValue());
						}else {
							//sailingSchedule.setCfs_Cut_off(cfs_Cut_off.getNumericCellValue()+" ");
						}
					}
					if(time!=null){
						if(time.getCellType()==HSSFCell.CELL_TYPE_STRING && ! time.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setTime(time.getStringCellValue());
						}else {
							//sailingSchedule.setTime(time.getNumericCellValue()+"");
						}
					}
					if(last_Stuffing!=null){
						if(last_Stuffing.getCellType()==HSSFCell.CELL_TYPE_STRING && ! last_Stuffing.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setLast_Stuffing(last_Stuffing.getStringCellValue());
						}else {
							//sailingSchedule.setLast_Stuffing(last_Stuffing.getNumericCellValue()+"");
						}
					}
					if(day!=null){

						if(day.getCellType()==HSSFCell.CELL_TYPE_STRING && ! day.getStringCellValue().trim().isEmpty()){
							sailingSchedule.setDay(day.getStringCellValue());

						}
						if(transit!=null){
							if(transit.getCellType()==HSSFCell.CELL_TYPE_STRING && ! transit.getStringCellValue().trim().isEmpty()){
								sailingSchedule.setTransit(transit.getStringCellValue());
							}else {
								//sailingSchedule.setTransit(transit.getNumericCellValue()+"");
							}
						}
						if(remark!=null){
							if(remark.getCellType()==HSSFCell.CELL_TYPE_STRING && ! remark.getStringCellValue().trim().isEmpty()){
								sailingSchedule.setRemark(remark.getStringCellValue());
							}else {
								//sailingSchedule.setRemark(remark.getNumericCellValue()+"");
							}
						}else {
							sailingSchedule.setRemark("");
						}
						if(m_Vessel!=null){
							if(m_Vessel.getCellType()==HSSFCell.CELL_TYPE_STRING && ! m_Vessel.getStringCellValue().trim().isEmpty()){
								sailingSchedule.setM_Vessel(m_Vessel.getStringCellValue());
							}else {
								//sailingSchedule.setM_Vessel(m_Vessel.getNumericCellValue()+"");
							}
						}else {
							sailingSchedule.setM_Vessel("");
						}
						if(voyy!=null){
							if(voyy.getCellType()==HSSFCell.CELL_TYPE_STRING && ! voyy.getStringCellValue().trim().isEmpty()){
								sailingSchedule.setVoyy(voyy.getStringCellValue());
							}else {
								//sailingSchedule.setVoyy(voyy.getNumericCellValue()+"");
							}
						}else {
							sailingSchedule.setVoyy("");
						}
						if(to_Eta!=null){
							if(!to_Eta.getStringCellValue().equals("ETA")){
							if(to_Eta.getCellType()==HSSFCell.CELL_TYPE_STRING && ! to_Eta.getStringCellValue().trim().isEmpty()){
								//sailingSchedule.setTo_Eta(to_Eta.getStringCellValue());
								String toEta=to_Eta.getStringCellValue();
								//System.out.println(toEta);
								
								 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(toEta).getTime();
								// System.out.println("TO ETA"+date1);  
									sailingSchedule.setTo_Eta(date1);
								
							}else {
							//	sailingSchedule.setTo_Eta(to_Eta.getNumericCellValue()+"");
								if(to_Eta.getNumericCellValue()!=0){
								String toEta=to_Eta.getNumericCellValue()+"";
								 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(toEta).getTime();
								// System.out.println("TO ETA"+date1);  
									sailingSchedule.setTo_Eta(date1);
							}}

						}}/*else {
							sailingSchedule.setTo_Eta("");
						}*/
						if(to_Etd!=null){
							if(!to_Etd.getStringCellValue().equals("ETD")){
							if(to_Etd.getCellType()==HSSFCell.CELL_TYPE_STRING && ! to_Etd.getStringCellValue().trim().isEmpty()){
								//sailingSchedule.setTo_Etd(to_Etd.getStringCellValue());
								String toEtd=to_Etd.getStringCellValue();
								 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(toEtd).getTime();
								 
								// System.out.println("TO ETD"+date1);  
									sailingSchedule.setTo_Etd(date1);
							}else {
								//sailingSchedule.setTo_Etd(to_Etd.getNumericCellValue()+"");
								if(to_Etd.getNumericCellValue()!=0){
								String toEtd=to_Etd.getNumericCellValue()+"";
								 long date1=new SimpleDateFormat("dd.MM.yy",Locale.getDefault()).parse(toEtd).getTime();
								// System.out.println("TO ETD"+date1);  
									sailingSchedule.setTo_Etd(date1);
								
							}}

						}}/*else {
							sailingSchedule.setTo_Etd("");
						}*/



					
						if(sailingSchedule.getOrigin_Location()!=null){
							if( !sailingSchedule.getOrigin_Location().equals("ORIGIN/LOCATION")){

								scheduleList.add(sailingSchedule);

							}
						}}
				}	
			}


			for(Sail_schedule sail:scheduleList)
			{
				System.out.println(sail);

			}
			fis.close();
			

		}	 catch (Exception e) {

			e.printStackTrace();

		}
		
		
		return scheduleList;
		
	}
	
	public static void getDbConnection(List<Sail_schedule> scheduleList) throws ClassNotFoundException, SQLException{
		
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con=DriverManager.getConnection("jdbc:sqlserver://114.79.173.10:1433;DatabaseName=CPW", "sa", "Sqlserver#346");
		for(Sail_schedule sailingSchedule :scheduleList) {
		String sql="INSERT INTO SAIL_SCHEDULE_DETAIL (ORIGIN_LOCATION,PORT_OF_LOADING,TERMINAL,"
				+ "PORT_OF_DISCHARGE,SECTOR,SERVICE,VESSEL,VOY,FROM_ETA,FROM_ETD,CFS_CUT_OFF,"
				+ "TIME,LAST_STUFFING,DAY,TRANSIT,REMARK,M_VESSEL,VOYY,TO_ETA,TO_ETD) VALUES(?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("sailing origin" + sailingSchedule.getOrigin_Location());
		ps.setString(1, sailingSchedule.getOrigin_Location());
		ps.setString(2, sailingSchedule.getPort_Of_Loading());
		ps.setString(3, sailingSchedule.getTerminal());
		ps.setString(4, sailingSchedule.getPort_Of_Discharge());
		ps.setString(5, sailingSchedule.getSector());
		ps.setString(6, sailingSchedule.getService());
		ps.setString(7, sailingSchedule.getVessel());
		ps.setString(8, sailingSchedule.getVoy());
		
		ps.setLong(9, sailingSchedule.getFrom_Eta());
		
		ps.setLong(10, sailingSchedule.getFrom_Etd());
		ps.setString(11, sailingSchedule.getCfs_Cut_off());
		ps.setString(12, sailingSchedule.getTime());
		ps.setString(13, sailingSchedule.getLast_Stuffing());
		ps.setString(14, sailingSchedule.getDay());
		ps.setString(15, sailingSchedule.getTransit());
		ps.setString(16, sailingSchedule.getRemark());
		ps.setString(17, sailingSchedule.getM_Vessel());
		ps.setString(18, sailingSchedule.getVoyy());
		
		ps.setLong(19,sailingSchedule.getTo_Eta());
		
		ps.setLong(20,sailingSchedule.getTo_Etd());
		
		ps.execute();
		

			
		}
		
		
	}



}



