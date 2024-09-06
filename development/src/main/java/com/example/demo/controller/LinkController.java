package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Category;
import com.example.demo.entity.ItemsReference;
import com.example.demo.entity.Maker;
import com.example.demo.entity.OrdersReference;
import com.example.demo.entity.Store;
import com.example.demo.entity.Sub_Category_reference;
import com.example.demo.entity.Sub_SubCategoryReference;
import com.example.demo.entity.Users;
import com.example.demo.entity.Users_reference;
import com.example.demo.service.AdminListService;
import com.example.demo.service.CategoryArrayServiceImpl;
import com.example.demo.service.CategoryListServiceImpl;
import com.example.demo.service.MakerListServiceImpl;
import com.example.demo.service.OrdersReferenceService;
import com.example.demo.service.StoreListServiceImpl;
import com.example.demo.service.SubCategoryArrayServiceImpl;
import com.example.demo.service.SubSubCategoryArrayServiceImpl;
import com.example.demo.service.UsersReferenceService;

@Controller
public class LinkController {
	
	@Autowired
	  private AdminListService adminlistService;
	
	@Autowired
	  private UsersReferenceService usersReferenceService;
	
	@Autowired
	  private OrdersReferenceService ordersReferenceService;
	
	@Autowired
	  private MakerListServiceImpl makerListService;
	
	@Autowired
	  private StoreListServiceImpl storeListService;
	
	@Autowired
	  private CategoryListServiceImpl categoryListService;
	
	@Autowired
	  private CategoryArrayServiceImpl categoryArrayService;
	
	@Autowired
	  private SubCategoryArrayServiceImpl subCategoryArrayService;
	
	@Autowired
	  private SubSubCategoryArrayServiceImpl subSubCategoryArrayService;
	
	
	
	
	
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
    /*メーカー一覧リンク*/
	@GetMapping("/maker_list")
    public String makerList(Model model) {
		List<Maker> makerlist = makerListService.searchAll();
	    model.addAttribute("makerlist", makerlist);
		return "/list/maker_list";
	}
	/*メーカー詳細リンク*/
	@GetMapping("/detailList/maker_detail/{id}")
    public String makerDetail(@PathVariable Long id, Model model) {
		Maker maker2 = makerListService.findById(id);
	    model.addAttribute("makerData", maker2);
		return "/detailList/maker_detail";
	}
	 /*カテゴリ一覧リンク*/
	@GetMapping("/category_list")
	public String categoryList(Model model) {
		List<Category> categorylist = categoryListService.searchAll();
		model.addAttribute("categorylist", categorylist);
		return "/list/category_list";
		}
	/*カテゴリ詳細リンク*/
	@GetMapping("/category/category_detail/{id}")
	public String categoryDetail(@PathVariable long id, Model model) {
	    List<Sub_Category_reference> category2 = categoryArrayService.searchAll();
		int n = category2.size();
		List<Sub_Category_reference> category3= new ArrayList<>();
		for(int i =0; i < n ; i++) {
		    if(category2.get(i).getCategories().getId() == id) {	
			    category3.add(category2.get(i));
                model.addAttribute("categoryDate", category3);
			  }
			}
			return "/detailList/category_detail";
		}
	
	/*中カテゴリ詳細リンク*/
	@GetMapping("/sub_category_detail/{id}")
	public String subCategoryDetail(@PathVariable long id, Model model) {
	    List<Sub_SubCategoryReference> subCategory1 = subCategoryArrayService.searchAll();
		int n = subCategory1.size();
		List<Sub_SubCategoryReference> subCategory2= new ArrayList<>();
		for(int i =0; i < n ; i++) {
		    if(subCategory1.get(i).getSubCategories().getId() == id) {	
			    subCategory2.add(subCategory1.get(i));
                model.addAttribute("subCategoryDate", subCategory2);
			  }
			}
			return "/detailList/sub_category_detail";
		}
	/*小カテゴリ詳細リンク*/
	@GetMapping("/sub_sub_category_detail/{id}")
	public String subSubCategoryDetail(@PathVariable long id, Model model) {
	    List<ItemsReference> subSubCategory1 = subSubCategoryArrayService.searchAll();
		int n = subSubCategory1.size();
		List<ItemsReference> subSubCategory2= new ArrayList<>();
		for(int i =0; i < n ; i++) {
		    if(subSubCategory1.get(i).getItemSubSubCategories().getId() == id) {	
		    	subSubCategory2.add(subSubCategory1.get(i));
                model.addAttribute("subSubCategoryDate", subSubCategory2);
			  }
			}
			return "/detailList/sub_subCategory_detail";
		}
	/*店舗一覧リンク*/
	@GetMapping("/store_list")
    public String storeList(Model model) {
		List<Store> storelist = storeListService.searchAll();
	    model.addAttribute("storelist", storelist);
		return "/list/store_list";
	}
	/*店舗詳細リンク*/
	@GetMapping("/detailList/store_detail/{id}")
    public String storeDetail(@PathVariable Long id, Model model) {
		Store store2 = storeListService.findById(id);
		
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId =adminlistService.findByEmail(name);
        Users userInfo =adminlistService.findById(userId);
        model.addAttribute("userInfo",userInfo);
        
	    model.addAttribute("storeData", store2);
	    
	    if(userInfo.getStoreName()== id) {
		return "/detailList/store_detail";
	}else {
		return "redirect:/store_list";
	}
	    }
		
	/*発注履歴一覧リンク*/
	@GetMapping("/order_list")
    public String orderList(Model model) {
		
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
	    Long userId =adminlistService.findByEmail(name);
	    Users userInfo =adminlistService.findById(userId);
	      
	 List<OrdersReference> orderlist = ordersReferenceService.findByStoreId(userInfo.getStoreName());
	    model.addAttribute("orderlist", orderlist);
		return "/list/order_list";
	}
	
	/*注文履歴詳細リンク*/
	@GetMapping("/detailList/order_detail/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
		OrdersReference ordersData = ordersReferenceService.findById(id);
	    model.addAttribute("ordersData", ordersData);
		return "/detailList/order_detail";
	}
}
