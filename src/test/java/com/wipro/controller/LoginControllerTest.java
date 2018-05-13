package com.wipro.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.wipro.controller.LoginController;

import junit.framework.Assert;

public class LoginControllerTest extends Mockito {

	@Mock
	 HttpServletRequest request;
	 @Mock
	 HttpServletResponse response;
	 @Mock
	 RequestDispatcher rd;
	 @InjectMocks
	 LoginController loginController;
	 @Before
	 public void setUp() throws Exception {
	  MockitoAnnotations.initMocks(this);
	 }

   @Test
   public void testLoginSuccess() throws Exception {
	   
	   
	   Mockito.when(request.getParameter("email")).thenReturn("parivesh.kurmi@gmail.com");
	   Mockito.when(request.getParameter("password")).thenReturn("1234");
	   Mockito.doNothing().when(response).sendRedirect("./book-ticket.jsp");          
       StringWriter stringWriter = new StringWriter();
       PrintWriter writer = new PrintWriter(stringWriter);
       Mockito.when(response.getWriter()).thenReturn(writer);
       loginController.doPost(request, response);
       
       String result = stringWriter.getBuffer().toString().trim();
       Assert.assertEquals("Login successfull...", result);
	   
   }
   
 @Test
   public void testLoginFail() throws Exception {
	   Mockito.when(request.getParameter("email")).thenReturn("avinash.patel@wipro.com");
	   Mockito.when(request.getParameter("password")).thenReturn("12345");
	   Mockito.when(request.getRequestDispatcher("/fail.jsp")).thenReturn(rd);            
       StringWriter stringWriter = new StringWriter();
       PrintWriter writer = new PrintWriter(stringWriter);
       Mockito.when(response.getWriter()).thenReturn(writer);
       loginController.doPost(request, response);
    
       Mockito.verify(rd).forward(request, response);
       
       String result = stringWriter.getBuffer().toString().trim();
       Assert.assertEquals("Login fail...", result);
     
   }
}
