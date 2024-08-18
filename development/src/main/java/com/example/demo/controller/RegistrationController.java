package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.AdministratorRegistrationService;
import com.example.demo.service.SelectboxService;

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
	    	
	    	session.invalidate();
	    	
	    	return"redirect:/admin_list";
	    }

}
