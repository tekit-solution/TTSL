package com.cpw.services;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cpw.dao.PdfCreationDAOImpl;

public class PdfCreationImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ApplicationContext context  = new ClassPathXmlApplicationContext("Beans.xml");
	PdfCreationDAOImpl pdfDAOImpl = (PdfCreationDAOImpl) context.getBean("pdfCreationDaoImpl");

	public ByteArrayInputStream pdfData(long bookingId,long userId){
		logger.debug("Enter into pdfImpl" +bookingId);
ByteArrayInputStream bis	=pdfDAOImpl.pdfData(bookingId,userId);
		return bis;

	}
/*	private List<PdfCreationResponse> pdfData(List<PdfCreation> pdfCreation){
		List<PdfCreationResponse> pdfResponse=Collections.emptyList();
		if(pdfCreation!=null && !pdfCreation.isEmpty())
		{
			pdfResponse=new ArrayList<PdfCreationResponse>();
			for(PdfCreation pdf : pdfCreation)
			{
				PdfCreationResponse pdfResponseList=pdfData(pdf);
				pdfResponse.add(pdfResponseList);
			}
			pdfCreation.clear();
		}
		return pdfResponse;

	}
	private PdfCreationResponse pdfData(PdfCreation pdfCreation)
	{
		PdfCreationResponse response=new PdfCreationResponse();
		if(response!=null)
		{

			response.setShipper(pdfCreation.getShipper());
			response.setConsignee(pdfCreation.getConsignee());
			//response.setNotifyParty(pdfCreation.getNotifyParty());
			response.setPlaceOfReciept(pdfCreation.getPlaceOfReciept());
			response.setPortOfLoading(pdfCreation.getPortOfLoading());
			response.setPortOfDischarge(pdfCreation.getPortOfDischarge());
			response.setPlaceOfDelivery(pdfCreation.getPlaceOfDelivery());
			response.setNoOfOriginalBill(pdfCreation.getNoOfOriginalBill());
			response.setShippedOnBoard(pdfCreation.getShippedOnBoard());
			response.setShipperAddress(pdfCreation.getShipperAddress());
			response.setConsigneeAddress(pdfCreation.getConsigneeAddress());
			response.setFristNotifyAddrerss(pdfCreation.getFristNotifyAddrerss());
		}
		return response;*/

	}




