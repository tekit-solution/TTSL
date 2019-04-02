package com.cpw.configuration;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	private int maxUploadSizeInMb = 1024 * 1024; // 1 MB

	     
	
	    private File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

	@Override
	 protected Class < ? > [] getRootConfigClasses() {
	  return new Class[] {
	   ApplicationConfiguration.class
	  };
	 }
	 @Override
	 protected Class < ? > [] getServletConfigClasses() {
	  return null;
	 }
	 @Override
	 protected String[] getServletMappings() {
	  return new String[] {
	   "/"
	  };
	 }
	 private MultipartConfigElement getMultipartConfigElement() {
		
		         MultipartConfigElement multipartConfigElement = new
		 
		             MultipartConfigElement(uploadDirectory.getAbsolutePath(),
		
		                 maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);
		 
		         return multipartConfigElement;
		
		     }


	 @Override
	protected void customizeRegistration(Dynamic registration) {
		 registration.setMultipartConfig(getMultipartConfigElement());

	}
}

