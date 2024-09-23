package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Items;
import com.example.demo.entity.ItemsReference;
import com.example.demo.entity.Sub_Category;
import com.example.demo.entity.Sub_SubCategory;
import com.example.demo.entity.Users;
import com.example.demo.form.ItemOrderForm;
import com.example.demo.form.ItemSerchForm;
import com.example.demo.repository.CategorySearchRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemNameSearchRepository;
import com.example.demo.service.AdminListService;
import com.example.demo.service.InventoryListServiceImpl;
import com.example.demo.service.InventoryRegistrationServiceImpl;
import com.example.demo.service.ItemListServiceImpl;
import com.example.demo.service.ItemOrderService;
import com.example.demo.service.ItemPageService;
import com.example.demo.service.ItemSerchSelectbox;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	
	@Autowired
	  private ItemPageService itemPageService;
	
	@Autowired
	  private ItemNameSearchRepository itemNameSearchRepository;
	
	@Autowired
	  private CategorySearchRepository categorySearchRepository;
	
	@Autowired
	  private InventoryRepository inventoryRepository;
	
	@Autowired
	private AdminListService adminlistService;
	
	@Autowired
	private ItemOrderService itemOrderService;
	
	@Autowired
	  private ItemListServiceImpl itemListService;
	
	@Autowired
	  private InventoryListServiceImpl inventoryListService;
	
	@Autowired
	  private InventoryRegistrationServiceImpl inventoryRegistrationService;
	
	@Autowired
	  private ItemSerchSelectbox itemSearchSelectbox;
	
	 /*商品一覧リンク*/
	@GetMapping("/item_list")
    public String itemList(Model model,Pageable pageable) {
		Page<Items> pageList = itemPageService.getItems(pageable);
	    model.addAttribute("pagelist", pageList);
	    model.addAttribute("itemslist", pageList.getContent());
	    model.addAttribute("itemSerchForm", new ItemSerchForm ());
	    model.addAttribute("itemOrderForm", new ItemOrderForm ());
        /*ここからセレクト引用*/
        List<Category> categoryList = itemSearchSelectbox.searchAll();
        model.addAttribute("categoryList", categoryList);
        
        List<Sub_Category> subCategoryList = itemSearchSelectbox.searchAll2();
        model.addAttribute("subCategoryList", subCategoryList);
        
        List<Sub_SubCategory> subSubCategoryList = itemSearchSelectbox.searchAll3();
        model.addAttribute("subSubCategoryList", subSubCategoryList);
        
		return "/list/item_list";
	}
	
	/*検索処理*/
	@GetMapping("/item_search")
	public String itemSearch(@RequestParam String itemName, Model model) {
		
		List<Items> list = itemNameSearchRepository.findByItemNameLike("%"+itemName+"%");
		
		model.addAttribute("itemSearchResult",list);
		
		return "/list/itemSearchResult";
	}
	
	@GetMapping("/category_search")
	public String categorySearch(@RequestParam String category, Model model) {
		Long catagoryId =  Long.parseLong(category);
		
		List<Items> list = itemNameSearchRepository.findByCategoryId(catagoryId);
		
		model.addAttribute("itemSearchResult2",list);
		
		return "/list/itemSearchResult2";
	}
	
	@GetMapping("/subCategory_search")
	public String subCategorySearch(@RequestParam String subCategory, Model model) {
		Long SubCatagoryId =  Long.parseLong(subCategory);
		
		List<Items> list = itemNameSearchRepository.findBySubCategoryId(SubCatagoryId);
		
		model.addAttribute("itemSearchResult3",list);
		
		return "/list/itemSearchResult3";
	}
	
	@GetMapping("/subSubCategory_search")
	public String subSubCategorySearch(@RequestParam String subSubCategory, Model model) {
		Long SubSubCatagoryId =  Long.parseLong(subSubCategory);
		
		List<Items> list = itemNameSearchRepository.findBySubSubCategoryId(SubSubCatagoryId);
		
		model.addAttribute("itemSearchResult4",list);
		
		return "/list/itemSearchResult4";
	}
	
	
	/*商品詳細リンク*/
	@GetMapping("/detailList/item_detail/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
		ItemsReference  items = itemListService.findById(id);
	    model.addAttribute("itemData", items);
	    
	    final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId =adminlistService.findByEmail(name);
        Users userInfo =adminlistService.findById(userId);
        model.addAttribute("userInfo",userInfo);
	    
	    Optional<Inventory> inventory = inventoryRepository.findByItemIdAndStoreId(id, userInfo.getStoreName());
	   
	    model.addAttribute("inventoryData", inventory);
		return "/detailList/item_detail";
	}
	
	/*ここから発注をデータベースに保存*/
	@PostMapping("/item_order")
	 public String item_order(@Validated @ModelAttribute("itemOrderForm") ItemOrderForm itemOrderForm, BindingResult errorResult,HttpServletRequest request) {
	      
	    	if(errorResult.hasErrors()) {	
	    	  return "/list/item_list";
	      }
	      final String name = SecurityContextHolder.getContext().getAuthentication().getName();
	      Long userId =adminlistService.findByEmail(name);
	      Users userInfo =adminlistService.findById(userId);
	   	
	      Long purchase =  Long.parseLong(itemOrderForm.getPurchase());
		  Long ordering_quantity =  Long.parseLong(itemOrderForm.getOrdering_quantity());
	    	    	
	      itemOrderForm.setTotal(purchase*ordering_quantity);
	      itemOrderForm.setUser_id(userInfo.getId());
	      itemOrderForm.setStore_id(userInfo.getStoreName());
	      
	      HttpSession session = request.getSession();
	      session.setAttribute("itemOrderForm",itemOrderForm);
	      
	      return "redirect:/item_order2";
	    }
	    
	 @GetMapping("/item_order2")
	    public String confirm(Model model, HttpServletRequest request) {
	    	HttpSession session = request.getSession();
	    	
	    	ItemOrderForm itemOrderForm = (ItemOrderForm) session.getAttribute("itemOrderForm");
	    	model.addAttribute("itemOrderForm",itemOrderForm);
	    	
	    	return "redirect:/item_order3";
	    }
	 @GetMapping("/item_order3")
	    public String registerItem(Model model,HttpServletRequest request) {
	    	
	    	HttpSession session = request.getSession();
	    	ItemOrderForm itemOrderForm = (ItemOrderForm) session.getAttribute("itemOrderForm");
	    	itemOrderService.saveItemOrder(itemOrderForm);
	    	inventoryRegistrationService.saveInventory(itemOrderForm);
	    	
	    	return "redirect:/item_order/complete";
	    }
	 	    
	    @GetMapping("/item_order/complete")
	    public String complete(Model model, HttpServletRequest request) {
	    	
	    	if (request.getSession(false) == null) {
	    		return "redirect:/store_list";
	    	}
	    	
	    	HttpSession session = request.getSession();
	    	ItemOrderForm itemOrderForm = (ItemOrderForm) session.getAttribute("itemOrderForm");
	    	model.addAttribute("itemOrderForm",itemOrderForm);
	    	
	    	session.removeAttribute("itemOrderForm");
	    	
	    	return"redirect:/item_list";
	    }
	
	
}
