package com.cpw.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cpw.jdbc.model.PdfCreation;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCreationDAOImpl implements PdfCreationDAO{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JdbcTemplate jdbcTemplateObject;
	static	PdfWriter writer;
	static Image IMG = null;
	String shipper_name;
	String consigneeName;
	String porName;
	String polName;
	String podName;
	String fdcName;
	String noOfOrginals;
	Date sobDate;
	String shipper_Addresss	;
	String consignee_Address;
	String firstNotify_Address;
	String mul_DocNo;
	String description_Of_Good;
	double gr_Wt;
	double measurment;
	String marks_and_No;
	String containor_No;
	String name;
	String actualSeal;
	String customeSeal;
	String sB_No;
	Date sB_date;
	String branch_Add;
	String loc_name;
	String age_name;
	String age_address;
	String notify_name;
	Date date_Of_Issue;
	String vessel;
	String voyage_no;
	String branch_name;
	String frieght_payable;
	String company_Name;
	List<String> results = new ArrayList<String>();


	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}
	public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		Image img = Image.getInstance(path);
		PdfPCell cell = new PdfPCell(img, true);
		return cell;
	}

	@Override
	public  ByteArrayInputStream pdfData(long bookingId,long userId) {
		logger.info("Enter into pdfDAOImpl");
		String sql	="SELECT DBL_SHIPPER_NAME,DBL_CONSIGNEE_NAME,DBL_POR_NAME,DBL_POL_NAME,DBL_POD_NAME,DBL_FDC_NAME,DBL_NO_OF_ORIGINALS,DBL_SOB_DATE ,DBL_NOTIFY_NAME,DBL_DATE_OF_ISSUE FROM PARAMETRES WHERE Booking_Id=? And PARAMETRES_TYPE='BL' and DBL_ORIGINAL_NON_NEGOTIABLE= 'OR'";

		List<PdfCreation> pdfCreation=jdbcTemplateObject.query(sql,new Object[]{bookingId}, new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				logger.info("Enter into Pdf Document");

				shipper_name =resultSet.getString("DBL_SHIPPER_NAME");
				logger.debug("Shipper Name is" +shipper_name);
				consigneeName = resultSet.getString("DBL_CONSIGNEE_NAME");
				porName = resultSet.getString("DBL_POR_NAME");
				polName = resultSet.getString("DBL_POL_NAME");
				podName	 = resultSet.getString("DBL_POD_NAME");
				fdcName	 = resultSet.getString("DBL_FDC_NAME");
				noOfOrginals = resultSet.getString("DBL_NO_OF_ORIGINALS");
				sobDate	=  resultSet.getDate("DBL_SOB_DATE");
				notify_name=resultSet.getString("DBL_NOTIFY_NAME");
				date_Of_Issue=resultSet.getDate("DBL_DATE_OF_ISSUE");


				PdfCreation pdf=new PdfCreation();

				pdf.setShipper(shipper_name);
				pdf.setConsignee(consigneeName);
				pdf.setPlaceOfReciept(porName);
				pdf.setPortOfLoading(polName);
				pdf.setPortOfDischarge(podName);
				pdf.setPlaceOfDelivery(fdcName);
				pdf.setNoOfOriginalBill(noOfOrginals);
				pdf.setShippedOnBoard(sobDate);


				return pdf;
			}
		});

		String sql1="SELECT SHIPPER_ADDRESS,CONSIGNEE_ADDRESS,FIRST_NOTIFY_ADDRESS,BOOKING_NO,COMMODITY_DESC,GR_WT,VOLUME,MARKS_NOS,AGENT_NAME,AGENT_ADDRESS,vessel_name,voyage_no FROM BOOKING_HDR WHERE BOOKING_ID=?";
		jdbcTemplateObject.query(sql1, new Object[]{bookingId} , new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				shipper_Addresss	=resultSet.getString("SHIPPER_ADDRESS");
				consignee_Address=resultSet.getString("CONSIGNEE_ADDRESS");
				firstNotify_Address=resultSet.getString("FIRST_NOTIFY_ADDRESS");
				mul_DocNo=resultSet.getString("BOOKING_NO");
				description_Of_Good=resultSet.getString("COMMODITY_DESC");
				gr_Wt=resultSet.getDouble("GR_WT");
				measurment=resultSet.getDouble("VOLUME");
				marks_and_No=resultSet.getString("MARKS_NOS");
				age_name=resultSet.getString("AGENT_NAME");
				age_address=resultSet.getString("AGENT_ADDRESS");
				vessel=resultSet.getString("vessel_name");
				voyage_no=resultSet.getString("voyage_no");

				PdfCreation pdf=new PdfCreation();
				pdf.setShipperAddress(shipper_Addresss);
				pdf.setConsigneeAddress(consignee_Address);
				pdf.setFristNotifyAddrerss(firstNotify_Address);
				pdf.setMulti_trans_docNo(mul_DocNo);
				pdf.setDescriptionOfGoods(description_Of_Good);
				pdf.setGrossWeight(gr_Wt);
				pdf.setMeasurement(measurment);
				pdf.setMarkAndNumber(marks_and_No);
				pdf.setAgent_name(age_name);
				pdf.setAgent_address(age_address);

				return pdf;
			}
		});
		String sql2="select 'Container No: '+ C.Container_No + ' ' +D.NAME +'A/Seal No: '+ C.Actual_Seal +'C/Seal No: '+Customs_Seal from cfs_received_dtl A"
				+" left join CLP_DTL B"
				+" on A.cfs_received_id=B.CLP_DTL_ID"
				+" and A.SRL_NO=B.CLP_DTL_SRL_NO"
				+" left join procurement_dtl C"
				+" on B.CLP_HDR_ID=C.PROCURMENT_ID"
				+" and B.CLP_HDR_SRL_NO=C.SERIAL_NO"
				+" left join Unit_Master D on C.Container_Type_Id=D.Unit_Id"
				+" where A.Booking_Id = ?";


		jdbcTemplateObject.query(sql2, new Object[]{bookingId} , new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				containor_No=resultSet.getString("");
				logger.debug("Container NO is:" +containor_No);



				PdfCreation pdf=new PdfCreation();
				pdf.setContainerNo(containor_No);

				return pdf;
			}
		});
		
		String sql3="select SHIPPING_BILL_NO + ' ' +cast(SHIPPING_BILL_DATE as varchar) from CFS_RECEIVED_DTL WHERE BOOKING_ID=?";
		jdbcTemplateObject.query(sql3, new Object[]{bookingId}, new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				sB_No	=resultSet.getString("");


				PdfCreation pdf=new PdfCreation();
				pdf.setsB_No(sB_No);


				return pdf;
			}
		});
		String sql4="SELECT LOCATION_MASTER.LOC_NAME,LOCATION_MASTER.BRANCH_ADD,LOCATION_MASTER.BRANCH_NAME FROM LOCATION_MASTER INNER JOIN BOOKING_HDR ON LOCATION_MASTER.LOC_ID=BOOKING_HDR.LOC_ID WHERE BOOKING_ID=?";
		jdbcTemplateObject.query(sql4, new Object[]{bookingId},new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				loc_name=resultSet.getString("LOC_NAME");
				branch_Add=resultSet.getString("BRANCH_ADD");
				branch_name=resultSet.getString("BRANCH_NAME");
				PdfCreation pdf=new PdfCreation();
				pdf.setLocationMaster(loc_name);
				pdf.setBranchAddrerss(branch_Add);

				return pdf;
			}
		});
		String sql5="SELECT CLAUSE_MASTER.CLAUSE_NAME FROM CLAUSE_MASTER INNER JOIN JOB_STAMP_CLAUSE ON JOB_STAMP_CLAUSE.CLAUSE_ID=CLAUSE_MASTER.CLAUSE_ID INNER JOIN BOOKING_HDR ON BOOKING_HDR.BOOKING_ID=JOB_STAMP_CLAUSE.BOOKING_ID WHERE BOOKING_HDR.BOOKING_ID=?";
		jdbcTemplateObject.query(sql5, new Object[]{bookingId},new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {

				while(resultSet.next()){
					results.add(resultSet.getString("CLAUSE_NAME"));

				}

				return null;
			}
		});
		String sql6="select CASE WHEN DBL_FREIGHT_PAYABLE_AT = 1 then DBL_POR_NAME else DBL_FDC_NAME END as FREIGHT_DETAIL FROM PARAMETRES WHERE BOOKING_ID=?";
		jdbcTemplateObject.query(sql6, new Object[]{bookingId}, new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				frieght_payable=resultSet.getString("FREIGHT_DETAIL");
				return null;
			}
		});
		String sql7="SELECT NAME FROM COMPANY_MASTER WHERE COMPANY_ID=(SELECT COMPANY_ID FROM LOCATION_MASTER WHERE LOC_ID = (SELECT LOC_ID FROM USER_LOCATIONS WHERE USER_ID = ? AND DEFAULT_LOC = 1 )) ";
		jdbcTemplateObject.query(sql7,new Object[]{userId},new RowMapper<PdfCreation>() {

			@Override
			public PdfCreation mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				company_Name=resultSet.getString("NAME");
				return null;
			}
		});

		Document document = new Document();//PageSize.A4.rotate()
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ResourceBundle rb = ResourceBundle.getBundle("path");
		String pdfImagePath =rb.getString("pdfImagePath");
		try {
			try {
				IMG = Image.getInstance(pdfImagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Enter into Pdf Document");
			String pdfPath =rb.getString("pdfPath");
			writer=PdfWriter.getInstance(document, new FileOutputStream(pdfPath));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String shipperAddress = "CAMBRIDGE TIME SPARES\n79 EBRAHIM REHMATULLA ROADMUMBAI 400\n003 (INDIA)TEL 009123736325 FAX\n00912223738723";
		String shippersRefernce ="Shipper's Refernce";
		String consigneeDetail = "Consignee (if 'To Order' as indicate)";
		String notifyParty = "Notify Party(No Claim shall attach for failure to notify)";
		String placeOfRec ="    Place of Received";
		String portOfLoading = "    Port of Loading";
		String vessel = "    Vessel";
		String portOfDischarge ="    Port of Discharge";
		String freightPayable = "        Freight Payable at :";
		String placeOfDelivery = "    Place of Deliver";
		String noOfOriginalBillsOfLading = "    No. of Original Bills of Lading";
		String marksAndNumber = "Marks and Number";
		String descriptionOfGoods = "Number and Kind of Packeges/Description of goods"; 
		String grossWeight ="Gross Weight";
		String measurement = "Measurement";
		String placeAndDateOfIssue = "\n\n\nPlace and date of issue : ";
		String authorizedSignatory = "Authorized Signatory\n\n";

		//document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES, 6);
		Font text_font=FontFactory.getFont(FontFactory.TIMES,8);
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);


		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Top1 Left\n\n\n\n\n\n"));
		cell.setRowspan(6);
		cell.setColspan(4);
		Paragraph shipper = new Paragraph("Shipper\n", font);
		shipper.setIndentationLeft(5);
		shipper.setAlignment(Element.ALIGN_TOP);
		cell.addElement(shipper);
		Paragraph shippername=new Paragraph(shipper_name+"\n"+shipperAddress, text_font);
		shippername.setIndentationLeft(20);
		cell.addElement(shippername);
		shippername.setAlignment(Element.ALIGN_TOP);
		table.addCell(cell);//r-8,c-2
		cell = new PdfPCell(new Phrase("Multimodal Transport Document Registration No. ", font));
		cell.setPaddingLeft(5);
		cell.setColspan(4);
		table.addCell(cell);//c-2

		Font font1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);

		Phrase phares=new Phrase("Multimodal Transport Document No. "+"   ", font);
		phares.add(new Chunk(mul_DocNo, font1));
		cell = new PdfPCell(phares);
		cell.setPaddingLeft(5);
		cell.setColspan(4);
		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase(shippersRefernce,font));
		cell.setColspan(4);
		cell.setPadding(5);
		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase("Top Right-1\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));
		cell.setColspan(4);
		cell.setRowspan(15);
		IMG.setWidthPercentage(0);
		Phrase listOfDots = new Phrase();
		listOfDots.add(new Chunk(IMG, 75, 0));
		listOfDots.add(new Chunk(" "));
		cell.addElement(listOfDots);

		Font companyAddress1 = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD|Font.UNDERLINE);
		Paragraph p = new Paragraph(branch_name+"\n\n", companyAddress1);
		p.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(p);
		Font companyAddress2 = FontFactory.getFont(FontFactory.TIMES, 7);
		Paragraph p2 = new Paragraph(branch_Add,companyAddress2);
		p2.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(p2);

		Font companyAddress4 = FontFactory.getFont(FontFactory.TIMES, 10, Font.BOLD);
		Paragraph p4 = new Paragraph("ORIGINAL\n", companyAddress4);
		p4.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(p4);

		table.addCell(cell);//c-2

		cell = new PdfPCell(new Phrase("Top Left1\n\n\n\n\n\n"));
		cell.setRowspan(6);
		cell.setColspan(4);

		Paragraph consigneeDetailPara = new Paragraph(consigneeDetail+"  "+"\n\n", font);
		consigneeDetailPara.setIndentationLeft(5);
		shipper.setAlignment(Element.ALIGN_TOP);
		cell.addElement(consigneeDetailPara);
		Paragraph consignee_add=new Paragraph(consigneeName+"\n"+consignee_Address, text_font);
		consignee_add.setIndentationLeft(20);
		cell.addElement(consignee_add);
		table.addCell(cell);//r-8,c-2
		cell = new PdfPCell(new Phrase("Top Left2\n\n\n\n"));
		cell.setRowspan(6);
		cell.setColspan(4);
		Paragraph notifyPartyPara = new Paragraph(notifyParty+"\n\n", font);
		notifyPartyPara.setIndentationLeft(5);
		notifyPartyPara.setAlignment(Element.ALIGN_TOP);
		cell.addElement(notifyPartyPara);
		Paragraph firstNotify=new Paragraph(notify_name+"\n"+firstNotify_Address, text_font);
		firstNotify.setIndentationLeft(20);
		cell.addElement(firstNotify);
		table.addCell(cell);//r-8,c-2

		cell = new PdfPCell(new Phrase("Top Right11\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph placeOfRecPara = new Paragraph(placeOfRec+"\n", font);
		placeOfRecPara.setAlignment(Element.ALIGN_CENTER );
		cell.addElement(placeOfRecPara);
		Paragraph porname=new Paragraph(porName, text_font);
		porname.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(porname);

		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase("Top Right12\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph portOfLoadingPara = new Paragraph(portOfLoading+"\n", font);
		portOfLoadingPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(portOfLoadingPara);
		Paragraph polname=new Paragraph(polName, text_font);
		polname.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(polname);
		table.addCell(cell);//c-2

		cell = new PdfPCell(new Phrase("Top Right13\n Test"));
		cell.setPaddingLeft(5);
		cell.setRowspan(2);
		cell.setColspan(4);
		Paragraph freightPayablePara = new Paragraph(freightPayable+"\n", font);
		freightPayablePara.setAlignment(Element.ALIGN_TOP);
		cell.addElement(freightPayablePara);

		Paragraph frieght_Detail=new Paragraph("     "+frieght_payable, text_font);
//		cell.setPaddingLeft(5);
		//frieght_Detail.setAlignment(Element.ALIGN_LEFT);
		cell.setPaddingLeft(5);
		cell.addElement(frieght_Detail);
        
		table.addCell(cell);//c-2

		cell = new PdfPCell(new Phrase("Top Right15\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph vesselPara = new Paragraph(vessel+"\n", font);
		vesselPara.add(new Paragraph(vessel+"/"+voyage_no, text_font));
		vesselPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(vesselPara);
		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase("Top Right16\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph portOfDischargePara = new Paragraph(portOfDischarge+"\n", font);

		portOfDischargePara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(portOfDischargePara);
		Paragraph pod=new Paragraph(podName, text_font);

		pod.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(pod);
		table.addCell(cell);//c-2

		cell = new PdfPCell(new Phrase("Top Right17\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph placeOfDeliveryPara = new Paragraph(placeOfDelivery+"\n", font);

		placeOfDeliveryPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(placeOfDeliveryPara);
		Paragraph fdc=new Paragraph(fdcName, text_font);

		fdc.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(fdc);
		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase("Top Right18\n Test"));
		cell.setRowspan(2);
		cell.setColspan(2);
		Paragraph noOfOriginalBillsOfLadingPara = new Paragraph(noOfOriginalBillsOfLading+"\n", font);

		noOfOriginalBillsOfLadingPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(noOfOriginalBillsOfLadingPara);
		Paragraph noOrginal=new Paragraph(noOfOrginals, text_font);

		noOrginal.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(noOrginal);
		table.addCell(cell);//c-2


		cell = new PdfPCell(new Phrase("Top Right19\n\n\n\n\n\n Test"));


		cell.setRowspan(20);
		cell.setColspan(2);
		cell.setBorderWidthRight(0f);
		cell.setBorderWidthTop(0f);
		cell.setBorderWidthBottom(0f);
		cell.setFixedHeight(100f);
		Paragraph marksAndNumberPara=new Paragraph(marksAndNumber+"\n",font );
		marksAndNumberPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(marksAndNumberPara);
		Paragraph containerNo=new Paragraph(marks_and_No+"\n"+"Container No:\n"+containor_No+"\n"+"S.B.No.\n"+sB_No+"\n\n\n\n\n\n\nDELIVERY AGENT ADDRESS:\n"+age_name+"\n"+age_address+"\n\n", text_font);

		containerNo.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(containerNo);


		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Top Right20\n\n\n\n\n\n Test"));


		cell.setRowspan(20);
		cell.setColspan(2);
		cell.setBorder(0);

		Paragraph discriptionOfGoodPara=new Paragraph(descriptionOfGoods+"\n"+description_Of_Good, font);
		discriptionOfGoodPara.setAlignment(Element.ALIGN_TOP);
		cell.addElement(discriptionOfGoodPara);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("Top Right21\n\n\n\n\n\n Test"));
		cell.setBorder(Rectangle.NO_BORDER);

		cell.setRowspan(20);
		cell.setColspan(3);
		cell.setBorder(0);
		//cell.setFixedHeight(100f);
		Paragraph grossWeightPara=new Paragraph(grossWeight+"\n\n", font);
		grossWeightPara.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(grossWeightPara);
		/*Paragraph clause_name_dtl=new Paragraph(string, font1)*/
		Paragraph grWt=new Paragraph(gr_Wt+"\n\n\n\n\n", text_font);
		grWt.setAlignment(Element.ALIGN_CENTER);
		cell.addElement(grWt);
		for(String clause_name:results){
			Paragraph clauseName=new Paragraph(clause_name+"\n\n\n", text_font);
			cell.addElement(clauseName);
		}
		Paragraph shippedOnBill=new Paragraph("SHIPPED ON BOARD\n"+sobDate+"\n\n\n", text_font);
		cell.addElement(shippedOnBill);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Top Right22\n\n\n\n\n\n Test"));
		//cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(20);
		cell.setColspan(1);
		cell.setBorderWidthLeft(0f);
		cell.setBorderWidthTop(0f);
		cell.setBorderWidthBottom(0f);
		//cell.setFixedHeight(100f);
		Paragraph measurementPara=new Paragraph(measurement+"\n\n", font);
		measurementPara.setAlignment(Element.ALIGN_TOP);
		cell.addElement(measurementPara);
		Paragraph measurementDb=new Paragraph(measurment+"\n", text_font);
		measurementDb.setAlignment(Element.ALIGN_TOP);
		cell.addElement(measurementDb);
		table.addCell(cell);


		cell = new PdfPCell(new Phrase("In accepting this bill of lading. The Merchant expressly to be bound by all be terms, condition, clauses and exceptions on\n"
				+ " both sides of the bill of lading whether types, printed or otherwise.  For Freight prepaid bill of lading, delivery of Cargo is\n"
				+ " subject to realisation of freight cheque.  (Above particulars as declared by shipper)\n\n\n", font));
		cell.setRowspan(3);
		cell.setColspan(5);
		table.addCell(cell);//c-2

		cell = new PdfPCell(new Phrase("Top Right16\n\n\n"));
		cell.setRowspan(9);
		cell.setColspan(3);

		Paragraph placeAndDateOfIssuePara = new Paragraph(placeAndDateOfIssue, font);
		placeAndDateOfIssuePara.add(new Paragraph(loc_name+"/"+date_Of_Issue+"\n\n\nfor "+company_Name+"\n\n\n"+authorizedSignatory+"\n", text_font));
		placeAndDateOfIssuePara.setAlignment(Element.ALIGN_TOP);
		cell.addElement(placeAndDateOfIssuePara);
		table.addCell(cell);//c-2
		cell = new PdfPCell(new Phrase("Takes in charge in apparently good condition herein at the receipt for transport and delivery as mentioned above, unless\n"
				+ "otherwise stated. The MTO accordance with the provisions contained in the MTD undertakes to perform or the performance\n"
				+ "of the multimodal transport from the place at which the goods are taken in charges, to place designated for delivery and\n"
				+ "assumes responsibility for such transport. One of the MTD must be surrounded, duty endorsed in exchange for the goods.\n"
				+ "In witness where of the original MTD all of this tenor and date have been signed in the number indicated below one of which\n"
				+ "being accomplished the other(s) to be void. Terms and condition overleaf. Demurrage/ Detention charges at port of\n"
				+ "destination payable by consignee as per line tariff. No of packages mentioned above, have been received in apparent good\n"
				+ "condition, carriers does not certify the contents of the packages and is not responsible for any shortages there in. By\n"
				+ "accepting this bill of lading, the shipper accepts his responsibility towards the carrier for payment of freight (in case of freight\n"
				+ "collect shipment), accrued Ground rent, reshipment or disposal costs (as the case may be) if the consignee fails to take\n"
				+ "delivery of cargo within 90 days from the date cargo reached destination. The carrier reserves the right to repack the goods\n"
				+ "if the same are not in seaworthy. The packing condition will be certified by the local bonded warehouse or competent\n"
				+ "surveyor, and the shipper by virtue of accepting this bill landing accepts the liability towards the cost for the same.", font));
		cell.setRowspan(6);
		cell.setColspan(5);
		table.addCell(cell);//c-2
		document.open();
		/*Paragraph paragraph1 = new Paragraph("BILL OF LADING\n");

		document.add(paragraph1);*/
		/*Paragraph paragraph2 = new Paragraph("\n");
		document.add(paragraph2);*/
		PdfWriter.getInstance(document, out);
		document.open();
		Paragraph paragraph1 = new Paragraph("BILL OF LADING\n");

		document.add(paragraph1);
		Paragraph paragraph2 = new Paragraph("\n");
		document.add(paragraph2);
		document.add(table);
		document.close();


		System.out.println("pdfcraetion " +pdfCreation);

		return new ByteArrayInputStream(out.toByteArray());
	}

}
