package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.entity.Users;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.service.AdminListService;
import com.example.demo.service.AdministratorRegistrationService;
import com.example.demo.service.EditService;
import com.example.demo.service.SelectboxService;

@Controller
public class EditController {
	
	@Autowired
	  private AdminListService adminlistService;
	
	@Autowired
	private AdministratorRegistrationService administratorRegistrationService2;
	
	@Autowired
    private SelectboxService selectboxService;

	@Autowired
	private  EditService editService;

	/*管理者編集*/
	@GetMapping("/edit/user_edit/{id}/edit")
	public String userEdit(@PathVariable Long id, Model model) {
		
		Users users3 =adminlistService.findById(id);
	    
		AdministratorRegistrationForm usersUpdate3 = new AdministratorRegistrationForm();
		
		String userID3 =  String.valueOf(users3.getStoreName());
		String positionID3 =  String.valueOf(users3.getPositionId());
		String roleID3 =  String.valueOf(users3.getRoleId());
		
		
		usersUpdate3.setId(users3.getId());
		usersUpdate3.setStoreName(userID3);
		usersUpdate3.setLastName(users3.getLastName());
		usersUpdate3.setFirstName(users3.getFirstName());
		usersUpdate3.setEmail(users3.getEmail());
		usersUpdate3.setPositionsType(positionID3);
		usersUpdate3.setRoleType(roleID3);
		usersUpdate3.setPhone(users3.getPhone());
		usersUpdate3.setPassword(users3.getPassword());
		model.addAttribute("usersUpdate", usersUpdate3);
		
		List<Store> storeList2 = selectboxService.searchAll();
        model.addAttribute("storeList2", storeList2);
        
        List<Positions> positionsList2 = selectboxService.searchAll2();
        model.addAttribute("positionsList2", positionsList2);
        
        List<Roles> rolesList2 = selectboxService.searchAll3();
        model.addAttribute("rolesList2", rolesList2);
		
		return "edit/user_edit";
		
	}

	
	@PostMapping("/users/update/{id}")	
	public String update2(@Validated @ModelAttribute AdministratorRegistrationForm usersUpdate3, BindingResult result, Model model ) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			
			for (ObjectError error : result.getAllErrors()) {
			errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError",errorList);
			
			return  String.format("redirect:/edit/user_edit/%d/edit",usersUpdate3.getId());
		}
		editService.update3(usersUpdate3);
		return String.format("redirect:/detailList/users_detail/%d", usersUpdate3.getId());
	}

	@GetMapping("/user/{id}/delete")
	  public String delete(@PathVariable Long id) {
		editService.deleteUsers(id);
	    return "redirect:/admin_list";
	  }

}
