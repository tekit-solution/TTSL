package com.cpw.dao;

import java.io.ByteArrayInputStream;

import javax.sql.DataSource;

public interface PdfCreationDAO {
 
	
	public void setDataSource(DataSource ds);
	
	public ByteArrayInputStream pdfData(long bookingId,long userId);
}
