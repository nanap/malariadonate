package controller;


import java.io.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.mpowerpayments.mpower.*;


public class PayServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{

		req.getRequestDispatcher("/WEB-INF/pay.jsp").forward(req,resp);

		// if (req.getSession().getAttribute("Username") == null) {

		// 	resp.sendRedirect("/login?next=" + req.getRequestURI());
		// 	return;
		// }
  //   		req.getRequestDispatcher("/WEB-INF/settings.jsp").forward(req,resp);
	} 

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{

			

			// boolean buy = Boolean.parseBoolean(req.getParameter("buy"));
			int x = Integer.parseInt(req.getParameter("buy"));

		

			if (x == 1 || x == 2 || x == 3 || x == 4 ){
				//Setting up your MPower API keys
				MPowerSetup setup = new MPowerSetup();
				setup.setMasterKey("9cf18822-7e41-4f1f-b1b2-227f518cdebc");
				setup.setPrivateKey("test_private_fzD-mzhW6wpPPpIzyp6zoccrW3o");
				setup.setPublicKey("test_public_IUnMkwSOVh2dJxJXkT07BAlcmNg");
				setup.setToken("9af1db971f1ee01715c3"); 
				setup.setMode("test");


				//Checkout Store Configuration
				MPowerCheckoutStore store = new MPowerCheckoutStore();
				store.setName("Malaria Hunter");
				store.setTagline("Donation for Malaria Eradication");
				store.setPhoneNumber("024561793");
				store.setPostalAddress("K.I.A, P.O.Box 9831, Airport - Accra");
				store.setWebsiteUrl("http://localhost:8080/");

				//Initialize a Checkout Invoice
				MPowerCheckoutInvoice invoice = new MPowerCheckoutInvoice (setup, store);

				 
				/* You can optionally set a generation invoice description text which can 
				be used in cases where your invoice does not need an items list or in cases
				where you need to include some extra descriptive information to your invoice */
				invoice.setDescription("Thanks for helping us fight malaria");

				//Setting total amount chargable
				if (x == 1 ) {
					invoice.setTotalAmount(5.00);
					// Adding items to your invoice
				
				invoice.addItem(" 1 Mosquito Net",5,1.00,5.00,"Thanks for preventing malaria in a Ghanaian family");
				

				}else if (x == 2) {

					// Adding items to your invoice
				invoice.addItem("5 Mosquito Nets",5,5.00,25.00,"Thanks for preventing malaria in a Ghanaian family");

				invoice.setTotalAmount(25.00);
	
				}else if (x == 3) {

					
					// Adding items to your invoice
			
				invoice.addItem("5 Mosquito Nets",5,5.00,25.00,"Thanks for preventing malaria in 25 Ghanaian family");
				invoice.addItem("10 Malaria Medication",10,2.50,25.00,"Thanks for saving the life of 5 Ghanaian Children");

				invoice.setTotalAmount(50.00);			

				}else{
					
					// Adding items to your invoice
				invoice.addItem("10 Mosquito Nets",10,5.00,50.00,"Thanks for preventing malaria in 50 Ghanaian family");
				invoice.addItem("20 Malaria Medication",20,2.50,50.00,"Thanks for saving the life of 10 Ghanaian Children");

				invoice.setTotalAmount(100.00);
				}
				

				// Setting the return URL on an invoice instance. 
				// This will overwrite any global settings for return URL
				 invoice.setReturnUrl("http://www.startup360.org/contact/");

				// The code below depicts how to create the checkout invoice on our servers
				// and redirect to the checkout page.
				if (invoice.create()) {
				  System.out.println(invoice.getStatus());
				  System.out.println(invoice.getResponseText());
				  System.out.println(invoice.getInvoiceUrl());

				  // Globally setting return URL, the piece of code below 
				  // should be included with the checkout shop setup code
//				  MPowerStore store = new MPowerStore();
				  //store.setReturnUrl("http://google.com/");
				 
				  

				  	resp.sendRedirect(invoice.getInvoiceUrl());

				} else {
				  System.out.println(invoice.getResponseText());
				  System.out.println(invoice.getStatus());
				}
			}


			       
	}
}

