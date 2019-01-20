package com.lti.vehicle.controller;

	import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.validation.BindingResult;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.servlet.ModelAndView;

import com.lti.vehicle.model.AccountDetails;
import com.lti.vehicle.model.Claim;
import com.lti.vehicle.model.UserDetails;
import com.lti.vehicle.model.VehicleDetails;
import com.lti.vehicle.service.ClaimServiceImpl;
import com.lti.vehicle.service.IAdminService;
import com.lti.vehicle.service.IClaimService;
import com.lti.vehicle.service.IPlanService;
import com.lti.vehicle.service.IVehicleService;
import com.lti.vehicle.service.PaymentServiceDao;
import com.lti.vehicle.service.UserService;

	@Controller
	public class PageController {
		
		private UserService userService;
		
		
		
		private IAdminService iAdminService;

		@Autowired
		public void setiAdminService(IAdminService iAdminService) {
			this.iAdminService = iAdminService;
		}

		

		@Autowired
		public void setiClaimService(IClaimService iClaimService) {
			this.iClaimService = iClaimService;
		}
		@Autowired
		public void setIVehicleService(IVehicleService iVehicleService) {
			IVehicleService = iVehicleService;
		}
		
		@Autowired
		public void setIPlanService(IPlanService iPlanService) {
			IPlanService = iPlanService;
		}
		@Autowired
		public void setPaymentServiceDao(PaymentServiceDao paymentServiceDao) {
			PaymentServiceDao = paymentServiceDao;
		}



		private IClaimService iClaimService;
		private IVehicleService IVehicleService;
		private IPlanService IPlanService;
		private PaymentServiceDao PaymentServiceDao;

		@Autowired
		public void setUserService(UserService ps) {
			this.userService = ps;
		}
		@Autowired
		/* private CategoryDAO categoryDAO; */
		
		@RequestMapping(value={"/"})
		public ModelAndView goHome() {
			ModelAndView mv=new ModelAndView("index.jsp");
		return mv;
		}
		
	 	
//		@RequestMapping(value="/login")
//		public ModelAndView login() {
//			ModelAndView mv=new ModelAndView("login");
//			return mv;
//		}
//		
//		@RequestMapping(value="/loginverification",method=RequestMethod.POST)
//		public String LoginValidation(Model model,HttpServletRequest req)
//		{
//			
//		}
		@RequestMapping(value="/welcome")
		public ModelAndView welcome() {
			ModelAndView mv=new ModelAndView("welcome");
			

			return mv;
		}
	
		
		
	
		@RequestMapping("/login")
		public String showLoginView(Model model)
		{
			
			System.out.println("welllllllllllllcomeeeeeee");
		model.addAttribute("userDetails", new UserDetails());
		return "login";
		}

		
		
		
		
		
		
		/*@RequestMapping(value="/loginverification",method=RequestMethod.POST)
		public String LoginValidation(@ModelAttribute ("userDetails") @Valid UserDetails u,
				BindingResult result,HttpServletRequest req ,HttpSession session,Model model)
		{
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			
			
			if(email.equals("admin@gmail.com")&& password.equals("admin"))
		 
			{
				return "admin";
			
			}
			else if(userService.verifyUser(email, password))
			{
				session.setAttribute("email", u.getEmail());
				return "redirect:/";
			}
			else
			return "login";
		}
*/
/*@RequestMapping(value="/register")
		public String register(Model m) {
			m.addAttribute("user", new UserDetails());
			//ModelAndView mv=new ModelAndView("register");
			return "register";
		}*/



@RequestMapping(value="/register")
public String goRegister(Model model) {
model.addAttribute("userDetails",new UserDetails());
return "register";
}








		
		@RequestMapping(value="/register/add" ,method = RequestMethod.POST)
			public String addUser(@ModelAttribute("user") UserDetails u,BindingResult result, Model model) {
		try {
			if (!result.hasErrors()) {			
				if (u.getId() == null || u.getId() == 0) {
						// new user, add it		
						//User user = handleFileUpload(result, fileUpload, u);					
						
						this.userService.addUser(u);
				}  
			 
				return "redirect:/login";
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		return "register";
		}
		
		@RequestMapping(value= {"/logout","/vehicles/plans/calculate/logout"})
		public String logout(HttpSession session)
		{
			session.invalidate();
			System.out.println("logout");
			
			//session.getAttribute("uDetails");
			return "redirect:/";
		}

		
		
		
		
		@RequestMapping(value ="/loginVerification1", method = RequestMethod.POST)
		public String LoginValidation1(@ModelAttribute ("userDetails") @Valid UserDetails u,
				BindingResult result,HttpServletRequest req ,HttpSession session,Model model ) {
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			//System.out.println("pass"+password);
			
			if (email.equals("admin@gmail.com") && password.equals("admin"))

				{
							return "adminprofile";
		
				} 
			
			
			else if (userService.verifyUser(email, password)) {
			
			UserDetails userDetails= userService.getByEmail(email);
			session.setAttribute("uDetails", userDetails);
			System.out.println("user detailsssss"+userDetails);
			session.setAttribute("email", userDetails.getEmail());
			
			
			System.out.println("Login success");
		//	session.setAttribute("abc", userDetails.getVehicleDetails().getBrand());
			//session.setAttribute("planyear", userDetails.getPlans().getPlanYear());
		
					return "welcome";
			
			}
			else
			{
			return "redirect:/login";
			}
		}
		
		
		
		
		
		
//		@RequestMapping(value ="/loginVerification", method = RequestMethod.POST)
//		public String LoginValidation(@ModelAttribute ("userDetails") @Valid UserDetails u,
//				BindingResult result,HttpServletRequest req ,HttpSession session,Model model )
//				{
//			String email = req.getParameter("email");
//			String password = req.getParameter("password");
//			System.out.println("pass"+password);
//
//			
//			System.out.println("inside mapping");
//
////			if (email.equals("admin@gmail.com") && password.equals("admin"))
////
////			{
////				return "adminprofile";
////
////			} else  
//			if (userService.verifyUser(email, password)) {
//				
//				System.out.println("Login successs");
//				UserDetails userDetails= userService.getByEmail(email);
//				
//				System.out.println("loginnn obj"+userDetails);
//				
//				session.setAttribute("firstName", userDetails.getFirstName());
//				session.setAttribute("lastName", userDetails.getLastName());
//				session.setAttribute("email", userDetails.getEmail());
//				session.setAttribute("password", userDetails.getPassword());
//				session.setAttribute("confirm", userDetails.getConfirmPassword());				
//				session.setAttribute("id", userDetails.getId());
//				
//				session.setAttribute("planId", userDetails.getPlans().getPlanId());
//				
//				session.setAttribute("brandName", userDetails.getVehicleDetails().getBrand());
//				session.setAttribute("chasisNum", userDetails.getVehicleDetails().getChasisNum());
//				session.setAttribute("engineNum", userDetails.getVehicleDetails().getEngineNum());
//				session.setAttribute("licenseNum", userDetails.getVehicleDetails().getLicense());
//				session.setAttribute("model", userDetails.getVehicleDetails().getModel());
//				session.setAttribute("year", userDetails.getVehicleDetails().getPurchaseYear());
//				session.setAttribute("month", userDetails.getVehicleDetails().getPurchaseMonth());
//				session.setAttribute("regNum", userDetails.getVehicleDetails().getRegNumber());
//				
//				session.setAttribute("premium", userDetails.getPayment().getPayAmount());
//				
//				session.setAttribute("claimId", userDetails.getClaim().getClaimId());
//				session.setAttribute("reason", userDetails.getClaim().getClaimReason());
//				session.setAttribute("claimDate", userDetails.getClaim().getClaimDate());
//				session.setAttribute("claimDate", userDetails.getAccountDetails().getAccountId());
//				session.setAttribute("claimDate", userDetails.getAccountDetails().getAccountType());
//				session.setAttribute("claimDate", userDetails.getAccountDetails().getBalance());
//			
//			/*	System.out.println(userDetails);*/
//				//session.getAttribute("userDetails");
//				
//			System.out.println("Login success");
//			
//			}
//			return "loginVer";
//				}
			
				
//			
//			} else
//				return "login";
//		}

		@RequestMapping(value="/Accountdetails")
		public String accountdetails(Model model,UserDetails userDetails,HttpSession session ) {
		model.addAttribute("userDetails",new UserDetails());
		model.addAttribute("accountDetails",new AccountDetails());
//		session.setAttribute("claimDate", userDetails.getAccountDetails().getAccountId());
//		session.setAttribute("claimDate", userDetails.getAccountDetails().getAccountType());
		userDetails.getAccountDetails().setBalance(100000);
		session.setAttribute("accountBalance", userDetails.getAccountDetails().getBalance());
//		
	
		return "accountdetails";
		} 

		
		
		
///////////////////////////////////////////////////////Admin operations
		
		
		
		@RequestMapping(value="/listclaims", method=RequestMethod.GET)
		public String listdetails(Model model) {
			System.out.println("hi");

		try
		{
			List l=iAdminService.claimlist();
			model.addAttribute("listclaims", l);
			//model.addAttribute("customerDetails", customerDetails);
			System.out.println("inside"+l);
			
		}catch(Throwable t)
		{
			t.printStackTrace();
		}
		return "listclaims";
		}

		
		
		
		
/////////////////////////////accept
		@RequestMapping("/accept/{claimId}") 
		public String acceptStatus(  @PathVariable("claimId") int claimId, Model model)
		{
	
			List<Claim>c=iAdminService.fetchClaimDetails(claimId);;
		
			this.iAdminService.acceptClaim(claimId);
			List<Claim> claim= this.iAdminService.claimlist();
			model.addAttribute("claim",claim);
			return "redirect:/adminapprove";
		}
		
		
		@RequestMapping("/reject/{claimId}")
		public String rejectStatus(  @PathVariable("claimId") int claimId, Model model)
		{
	
			List<Claim>c=iAdminService.fetchClaimDetails(claimId);;
			this.iAdminService.acceptClaim(claimId);
			List<Claim> claim= this.iAdminService.claimlist();
			model.addAttribute("claim",claim);
			return "redirect:/admindisapprove";
		}

		

	}
