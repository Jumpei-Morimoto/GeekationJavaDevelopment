package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Items;
import com.example.demo.entity.Maker;
import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.entity.Sub_Category;
import com.example.demo.entity.Sub_SubCategory;
import com.example.demo.entity.Users;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.form.ItemRegistrationForm;
import com.example.demo.form.MakerRegistrationForm;
import com.example.demo.form.MakerUpdateForm;
import com.example.demo.form.StoreRegistrationForm;
import com.example.demo.repository.ItemsRepository;
import com.example.demo.service.AdminListService;
import com.example.demo.service.AdministratorRegistrationService;
import com.example.demo.service.EditService;
import com.example.demo.service.InventoryListServiceImpl;
import com.example.demo.service.ItemListServiceImpl2;
import com.example.demo.service.MakerListServiceImpl;
import com.example.demo.service.SelectboxService;
import com.example.demo.service.StoreListServiceImpl;

@Controller
public class EditController {
	
	@Autowired
	private AdminListService adminlistService;
	
	@Autowired
	private MakerListServiceImpl makerlistService;
	
	@Autowired
	private StoreListServiceImpl storelistService;
	
	@Autowired
	private ItemListServiceImpl2 itemlistService2;
	
	@Autowired
	private InventoryListServiceImpl inventoryListService;
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired 
    private PasswordEncoder passwordEncoder;
	
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
		
		List<Store> storeList2 = selectboxService.searchAll();
        model.addAttribute("storeList2", storeList2);
        
        List<Positions> positionsList2 = selectboxService.searchAll2();
        model.addAttribute("positionsList2", positionsList2);
        
        List<Roles> rolesList2 = selectboxService.searchAll3();
        model.addAttribute("rolesList2", rolesList2);
		
		
		usersUpdate3.setId(users3.getId());
		usersUpdate3.setStoreName(userID3);
		usersUpdate3.setLastName(users3.getLastName());
		usersUpdate3.setFirstName(users3.getFirstName());
		usersUpdate3.setEmail(users3.getEmail());
		usersUpdate3.setPositionsType(positionID3);
		usersUpdate3.setRoleType(roleID3);
		usersUpdate3.setPhone(users3.getPhone());
		usersUpdate3.setPassword(users3.getPassword());
		model.addAttribute("administratorRegistrationForm", usersUpdate3);
		
		return "edit/user_edit";
		
	}

	
	@PostMapping("/users/update")	
	public String update2(@Validated @ModelAttribute AdministratorRegistrationForm administratorRegistrationForm, BindingResult result, Model model ) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			
			for (ObjectError error : result.getAllErrors()) {
			errorList.add(error.getDefaultMessage());
			}
			List<Store> storeList2 = selectboxService.searchAll();
	        model.addAttribute("storeList2", storeList2);
	        
	        List<Positions> positionsList2 = selectboxService.searchAll2();
	        model.addAttribute("positionsList2", positionsList2);
	        
	        List<Roles> rolesList2 = selectboxService.searchAll3();
	        model.addAttribute("rolesList2", rolesList2);
			model.addAttribute("administratorRegistrationForm",administratorRegistrationForm);
			model.addAttribute("validationError",errorList);
			
			return "edit/user_edit" ; /*String.format("redirect:/edit/user_edit/%d/edit",usersUpdate3.getId());*/
		}
		
		
		editService.update3(administratorRegistrationForm);
		return String.format("redirect:/detailList/users_detail/%d", administratorRegistrationForm.getId());
	}

	@GetMapping("/user/{id}/delete")
	  public String delete(@PathVariable Long id) {
		editService.deleteUsers(id);
	    return "redirect:/admin_list";
	  }
	
	/*メーカー編集*/
	@GetMapping("/edit/maker_edit/{id}/edit")
	public String makerEdit(@PathVariable Long id, Model model) {
		
		Maker maker3 =makerlistService.findById(id);
	    
		MakerRegistrationForm makerUpdate = new MakerRegistrationForm ();

		String makerCreatedAt = String.valueOf(makerUpdate.getCreatedAt());
		String makerUpdatedAt = String.valueOf(makerUpdate.getUpdatedAt());
		
				
		makerUpdate.setId(maker3.getId());
		makerUpdate.setMakerName(maker3.getMakerName());
		makerUpdate.setCreatedAt(makerCreatedAt);
		makerUpdate.setUpdatedAt(makerUpdatedAt);
		
		model.addAttribute("makerUpdateForm", makerUpdate);
		
		
		return "edit/maker_edit";
		
	}
		
		@PostMapping("/maker/update")	
		public String makerUpdate2(@Validated @ModelAttribute MakerUpdateForm makerUpdateForm , BindingResult result, Model model ) {
			
			if (result.hasErrors()) {
				List<String> errorList = new ArrayList<String>();
				
				for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
				}
				model.addAttribute("validationError",errorList);
				model.addAttribute("makerUpdateForm", makerUpdateForm);
				
				return  "edit/maker_edit";
			}
			editService.makerUpdate3(makerUpdateForm);
			return String.format("redirect:/detailList/maker_detail/%d", makerUpdateForm.getId());
		}

		@GetMapping("/maker/{id}/delete")
		  public String deletemaker(@PathVariable Long id) {
			editService.deleteMaker(id);
		    return "redirect:/maker_list";
		  }
	
		
		
		
		/*店舗編集*/
		@GetMapping("/edit/store_edit/{id}/edit")
		public String storeEdit(@PathVariable Long id, Model model) {
			
			Store store3 =storelistService.findById(id);
		    
			StoreRegistrationForm storeUpdate = new StoreRegistrationForm ();

			String storeCreatedAt = String.valueOf(storeUpdate.getCreatedAt());
			String storeUpdatedAt = String.valueOf(storeUpdate.getUpdatedAt());
			
					
			storeUpdate.setId(store3.getId());
			storeUpdate.setStoreName(store3.getStoreName());
			storeUpdate.setAddress(store3.getAddress());
			storeUpdate.setCreatedAt(storeCreatedAt);
			storeUpdate.setUpdatedAt(storeUpdatedAt);
			
			model.addAttribute("storeRegistrationForm", storeUpdate);
			
			
			return "edit/store_edit";
			
		}
			
			@PostMapping("/store/update")	
			public String storeUpdate2(@Validated @ModelAttribute StoreRegistrationForm storeRegistrationForm, BindingResult errorResult, Model model ) {
				
				if (errorResult.hasErrors()) {
					List<String> errorList = new ArrayList<String>();
					
					for (ObjectError error : errorResult.getAllErrors()) {
					errorList.add(error.getDefaultMessage());
					}
					errorResult.getAllErrors().forEach(error -> {
			            System.out.println("Error: " + error.getDefaultMessage());
			        });
					
					model.addAttribute("storeRegistrationForm",storeRegistrationForm);
					
					model.addAttribute("validationError",errorList);
					return "edit/store_edit";
				}
				editService.storeUpdate3(storeRegistrationForm);
				return String.format("redirect:/detailList/store_detail/%d", storeRegistrationForm.getId());
			}

			@GetMapping("/store/{id}/delete")
			  public String deleteStore(@PathVariable Long id) {
				editService.deleteStore(id);
			    return "redirect:/store_list";
			  }
			
			
			
			/*商品編集*/
			@GetMapping("/edit/item_edit/{id}/edit")
			public String itemEdit(@PathVariable Long id, Model model) {
				
				Items item =itemlistService2.findById(id);
				
				if (item == null) {
			        return "redirect:/error";
			    }
			    
				ItemRegistrationForm itemUpdate = new ItemRegistrationForm ();
				
				String itemCreatedAt = String.valueOf(itemUpdate.getCreatedAt());
				String itemUpdatedAt = String.valueOf(itemUpdate.getUpdatedAt());
				String makerId =  String.valueOf(item.getMakerId());
				String purchase =  String.valueOf(item.getPurchase());
				String listPrice =  String.valueOf(item.getListPrice());
				String categoryId=  String.valueOf(item.getCategoryId());
				String subCategoryId =  String.valueOf(item.getSubCategoryId());
				String subSubCategoryId =  String.valueOf(item.getSubSubCategoryId());
	
				itemUpdate.setId(item.getId());
	    		itemUpdate.setItemName(item.getItemName());
	    		itemUpdate.setItemBody(item.getItemBody());
	    		itemUpdate.setMakerId(makerId);
	    		itemUpdate.setPurchase(purchase);
	    		itemUpdate.setListPrice(listPrice);
	    		itemUpdate.setCategoryId(categoryId);
	    		itemUpdate.setSubCategoryId(subCategoryId);
	            itemUpdate.setSubSubCategoryId(subSubCategoryId);
	            itemUpdate.setItemImage(item.getItemImage());
				itemUpdate.setCreatedAt(itemCreatedAt);
				itemUpdate.setUpdatedAt(itemUpdatedAt);
				
				/*ここからセレクト引用*/
		        List<Category> categoryList = selectboxService.searchAll4();
		        model.addAttribute("categoryList", categoryList);
		        
		        List<Sub_Category> sub_CategoryList = selectboxService.searchAll5();
		        model.addAttribute("subCategoryList", sub_CategoryList);
		        
		        List<Sub_SubCategory> sub_SubCategoryList = selectboxService.searchAll6();
		        model.addAttribute("subSubCategoryList", sub_SubCategoryList);
		        
		        List<Maker> makerList = selectboxService.searchAll7();
		        model.addAttribute("makerList", makerList);
		        /*ここまでセレクト引用*/
				
				model.addAttribute("itemRegistrationForm", itemUpdate);
				
				Inventory inventory = inventoryListService.findById(id);
				   
			    model.addAttribute("inventoryData", inventory);
				
				
				return "/edit/item_edit";
				
			}
				
				@PostMapping("/item/update/{id}")	
				public String itemUpdate2(@Validated @ModelAttribute ItemRegistrationForm itemRegistrationForm ,BindingResult result, @RequestParam("imageFile") MultipartFile imageFile, Model model )throws IOException {
					
				        if (imageFile.isEmpty()) {
				        	
				        	List<Category> categoryList = selectboxService.searchAll4();
					        model.addAttribute("categoryList", categoryList);
					        
					        List<Sub_Category> sub_CategoryList = selectboxService.searchAll5();
					        model.addAttribute("subCategoryList", sub_CategoryList);
					        
					        List<Sub_SubCategory> sub_SubCategoryList = selectboxService.searchAll6();
					        model.addAttribute("subSubCategoryList", sub_SubCategoryList);
					        
					        List<Maker> makerList = selectboxService.searchAll7();
					        model.addAttribute("makerList", makerList);
					        
				            model.addAttribute("message", "画像を選択してください");
				            return  "/edit/item_edit";
				        }
					
					if (result.hasErrors()) {
						
						 List<Category> categoryList = selectboxService.searchAll4();
					        model.addAttribute("categoryList", categoryList);
					        
					        List<Sub_Category> sub_CategoryList = selectboxService.searchAll5();
					        model.addAttribute("subCategoryList", sub_CategoryList);
					        
					        List<Sub_SubCategory> sub_SubCategoryList = selectboxService.searchAll6();
					        model.addAttribute("subSubCategoryList", sub_SubCategoryList);
					        
					        List<Maker> makerList = selectboxService.searchAll7();
					        model.addAttribute("makerList", makerList);
						
						model.addAttribute("itemRegistrationForm",itemRegistrationForm);
						
						return  "/edit/item_edit";
					}
					
					
					if (!imageFile.isEmpty()) {
				            String uploadDir = "src/main/resources/static/img/";
				            String fileName = imageFile.getOriginalFilename();
				            Path filePath = Paths.get(uploadDir + fileName);
				            itemRegistrationForm.setItemImage(fileName);
				         
				            Files.write(filePath, imageFile.getBytes());
				         }/*else{
				        	Optional<Items> ItemList = itemsRepository.findById(itemRegistrationForm.getId());
				        	if(ItemList.isPresent()) {
				        		Items Item = ItemList.get();
				        		itemRegistrationForm.setItemImage(Item.getItemImage());
				            }
				         }*/
					
					editService.itemUpdate(itemRegistrationForm);
					return String.format("redirect:/detailList/item_detail/%d", itemRegistrationForm.getId());
				}
				


			
			@GetMapping("/item/{id}/delete")
			  public String deleteItems1(@PathVariable Long id) {
				editService.deleteItems(id);
			    return "redirect:/item_list";
			  }
			
			

}
