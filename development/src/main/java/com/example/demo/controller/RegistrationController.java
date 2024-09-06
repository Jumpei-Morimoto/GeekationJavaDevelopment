package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.form.ItemOrderForm;
import com.example.demo.form.MakerRegistrationForm;
import com.example.demo.form.StoreRegistrationForm;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.AdministratorRegistrationService;
import com.example.demo.service.MakerRegistrationService;
import com.example.demo.service.SelectboxService;
import com.example.demo.service.StoreRegistrationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {
	
	@Autowired
    private UsersRepository usersRepository;
	
	@Autowired
    private StoreRepository storeRepository;
	
	@Autowired
	private AdministratorRegistrationService administratorRegistrationService;
	
	@Autowired
	private MakerRegistrationService makerRegistrationService;
	
	@Autowired
	private StoreRegistrationService storeRegistrationService;
	
    @Autowired
    private SelectboxService selectboxService;
	
	@GetMapping("/registration/administrator_registration")
    public String administrator_registration(Model model) {
		
        model.addAttribute("administratorRegistrationForm", new AdministratorRegistrationForm ());
        /*ここからセレクト引用*/
        List<Store> storeList = selectboxService.searchAll();
        model.addAttribute("storeList", storeList);
        
        List<Positions> positionsList = selectboxService.searchAll2();
        model.addAttribute("positionsList", positionsList);
        
        List<Roles> rolesList = selectboxService.searchAll3();
        model.addAttribute("rolesList", rolesList);
        /*ここまでセレクト引用*/
        return "/registration/administrator_registration";
    }	
	
/*ここからデータベースに保存*/
	@PostMapping("/administrator_registration2")
	 public String administrator_registration(@Validated @ModelAttribute("administratorRegistrationForm") AdministratorRegistrationForm administratorRegistrationForm, BindingResult errorResult,HttpServletRequest request) {
	      
	    	if(errorResult.hasErrors()) {	
	    	  return "/registration/administrator_registration";
	      }
	    	
	      HttpSession session = request.getSession();
	      session.setAttribute("administratorRegistrationForm",administratorRegistrationForm);
	      
	      return "redirect:/administrator_registration3";
	    }
	    
	 @GetMapping("/administrator_registration3")
	    public String confirm(Model model, HttpServletRequest request) {
	    	HttpSession session = request.getSession();
	    	
	    	AdministratorRegistrationForm administratorRegistrationForm = (AdministratorRegistrationForm) session.getAttribute("administratorRegistrationForm");
	    	model.addAttribute("administratorRegistrationForm",administratorRegistrationForm);
	    	return "redirect:/administrator_registration/register";
	    }
	    
	    @GetMapping("/administrator_registration/register")
	    public String register(Model model,HttpServletRequest request) {
	    	
	    	HttpSession session = request.getSession();
	    	AdministratorRegistrationForm administratorRegistrationForm = (AdministratorRegistrationForm) session.getAttribute("administratorRegistrationForm");
	    	
	    	administratorRegistrationService.saveAdmin(administratorRegistrationForm);
	    	
	    	return "redirect:/administrator_registration/complete";
	    }
	    	
	    
	    @GetMapping("/administrator_registration/complete")
	    public String complete(Model model, HttpServletRequest request) {
	    	
	    	if (request.getSession(false) == null) {
	    		return "redirect:/top";
	    	}
	    	
	    	HttpSession session = request.getSession();
	    	AdministratorRegistrationForm administratorRegistrationForm = (AdministratorRegistrationForm) session.getAttribute("administratorRegistrationForm");
	    	model.addAttribute("administratorRegistrationForm",administratorRegistrationForm);
	    	
	    	session.removeAttribute("administratorRegistrationForm");
	    	
	    	return"redirect:/admin_list";
	    }
	    /*ここまで管理者新規登録*/
	    
	    
	    
	    /*ここからメーカー新規登録*/
	   
	    
	    @GetMapping("/registration/maker_registration")
	    public String maker_registration(Model model) {
			
	        model.addAttribute("makerRegistrationForm", new MakerRegistrationForm ());
	        return "/registration/maker_registration";
	    }	
		
	/*ここからデータベースに保存*/
		@PostMapping("/maker_registration2")
		 public String maker_registration(@Validated @ModelAttribute("makerRegistrationForm") MakerRegistrationForm makerRegistrationForm, BindingResult errorResult,HttpServletRequest request) {
		      
		    	if(errorResult.hasErrors()) {	
		    	  return "/registration/maker_registration";
		      }
		    	
		      HttpSession session = request.getSession();
		      session.setAttribute("makerRegistrationForm",makerRegistrationForm);
		      
		      return "redirect:/maker_registration3";
		    }
		    
		 @GetMapping("/maker_registration3")
		    public String confirmMaker(Model model, HttpServletRequest request) {
		    	HttpSession session = request.getSession();
		    	
		    	MakerRegistrationForm makerRegistrationForm = (MakerRegistrationForm) session.getAttribute("makerRegistrationForm");
		    	model.addAttribute("makerRegistrationForm",makerRegistrationForm);
		    	return "redirect:/maker_registration/register";
		    }
		    
		    @GetMapping("/maker_registration/register")
		    public String registerMaker(Model model,HttpServletRequest request) {
		    	
		    	HttpSession session = request.getSession();
		    	MakerRegistrationForm makerRegistrationForm = (MakerRegistrationForm) session.getAttribute("makerRegistrationForm");
		    	
		    	makerRegistrationService.saveMaker(makerRegistrationForm);
		    	
		    	return "redirect:/maker_registration/complete";
		    }
		    	
		    
		    @GetMapping("/maker_registration/complete")
		    public String completeMaker(Model model, HttpServletRequest request) {
		    	
		    	if (request.getSession(false) == null) {
		    		return "redirect:/top";
		    	}
		    	
		    	HttpSession session = request.getSession();
		    	MakerRegistrationForm makerRegistrationForm = (MakerRegistrationForm) session.getAttribute("makerRegistrationForm");
		    	model.addAttribute("makerRegistrationForm",makerRegistrationForm);
		    	
		    	session.removeAttribute("makerRegistrationForm");
		    	
		    	return"redirect:/maker_list";
		    }
		    /*ここから店舗新規登録*/
			   
		    
		    @GetMapping("/registration/store_registration")
		    public String store_registration(Model model) {
				
		        model.addAttribute("storeRegistrationForm", new StoreRegistrationForm ());
		        return "/registration/store_registration";
		    }	
			
		/*ここからデータベースに保存*/
			@PostMapping("/store_registration")
			 public String store_registration(@Validated @ModelAttribute("storeRegistrationForm") StoreRegistrationForm storeRegistrationForm, BindingResult errorResult,HttpServletRequest request) {
			      
			    	if(errorResult.hasErrors()) {	
			    	  return "/registration/store_registration";
			      }
			    	
			      HttpSession session = request.getSession();
			      session.setAttribute("storeRegistrationForm",storeRegistrationForm);
			      
			      return "redirect:/store_registration3";
			    }
			    
			 @GetMapping("/store_registration3")
			    public String confirmStore(Model model, HttpServletRequest request) {
			    	HttpSession session = request.getSession();
			    	
			    	StoreRegistrationForm storeRegistrationForm = (StoreRegistrationForm) session.getAttribute("storeRegistrationForm");
			    	model.addAttribute("storeRegistrationForm",storeRegistrationForm);
			    	return "redirect:/store_registration/register";
			    }
			    
			 @GetMapping("/store_registration/register")
			    public String registerStore(Model model,HttpServletRequest request) {
			    	
			    	HttpSession session = request.getSession();
			    	StoreRegistrationForm storeRegistrationForm = (StoreRegistrationForm) session.getAttribute("storeRegistrationForm");
			    	
			    	storeRegistrationService.saveStore(storeRegistrationForm);
			    	
			    	return "redirect:/store_registration/complete";
			    }
			    	
			    
			    @GetMapping("/store_registration/complete")
			    public String completeStore(Model model, HttpServletRequest request) {
			    	
			    	if (request.getSession(false) == null) {
			    		return "redirect:/top";
			    	}
			    	
			    	HttpSession session = request.getSession();
			    	StoreRegistrationForm storeRegistrationForm = (StoreRegistrationForm) session.getAttribute("storeRegistrationForm");
			    	model.addAttribute("storeRegistrationForm",storeRegistrationForm);
			    	
			    	session.removeAttribute("storeRegistrationForm");
			    	
			    	return"redirect:/store_list";
			    }
			    
			    /*商品注文*/
			    /*ここからデータベースに保存*/
				@GetMapping("/item_order/{id}")
				 public String item_order(@PathVariable long item_id,@Validated @ModelAttribute("itemOrderForm") ItemOrderForm itemOrderForm, BindingResult errorResult,HttpServletRequest request) {
				      
				    	if(errorResult.hasErrors()) {	
				    	  return "/list/item_list";
				      }
				      HttpSession session = request.getSession();
				      session.setAttribute("itemOrderForm",itemOrderForm);
				      
				      return "itemOrderForm";
				    }   

							
}
