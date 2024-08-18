package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Users;
import com.example.demo.entity.Users_reference;
import com.example.demo.service.AdminListService;
import com.example.demo.service.UsersReferenceService;

@Controller
public class LinkController {
	
	@Autowired
	  private AdminListService adminlistService;
	
	@Autowired
	  private UsersReferenceService usersReferenceService;
	
	/*topリンク*/
	/*管理者一覧リンク*/
	@GetMapping("/admin_list")
    public String adminList(Model model) {
		List<Users> userlist = adminlistService.searchAll();
	    model.addAttribute("userlist", userlist);
		return "/list/admin_list";
	}
	/*管理者詳細リンク*/
	@GetMapping("/detailList/users_detail/{id}")
    public String usersDetail(@PathVariable Long id, Model model) {
		Users_reference users2 = usersReferenceService.findById(id);
	    model.addAttribute("userData", users2);
		return "/detailList/users_detail";
	}
    
	
}
