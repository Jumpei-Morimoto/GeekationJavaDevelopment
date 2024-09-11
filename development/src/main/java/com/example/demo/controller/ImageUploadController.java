package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Items;
import com.example.demo.entity.Maker;
import com.example.demo.entity.Sub_Category;
import com.example.demo.entity.Sub_SubCategory;
import com.example.demo.form.ItemRegistrationForm;
import com.example.demo.service.ItemRegistrationService;
import com.example.demo.service.SelectboxService;

import jakarta.validation.Valid;

@Controller
public class ImageUploadController {
	private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";

	@Autowired
    private SelectboxService selectboxService;
	
	@Autowired
    private ItemRegistrationService itemRegistrationService;
	
	@GetMapping("/registration/item_registration")
    public String item_registration(Model model) {
		
		model.addAttribute("itemRegistrationForm", new ItemRegistrationForm());
		
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
        return "/registration/item_registration";
    }
	
	@PostMapping("/image_upload")
    public String uploadFile(@Valid ItemRegistrationForm itemRegistrationForm, BindingResult result, Model model) throws IOException {
        
		if (result.hasErrors()) {
	        return "/registration/item_registration";
	    }
		
		MultipartFile file = itemRegistrationForm.getFile();
	    if (file.isEmpty()) {
	        return "/registration/item_registration";
	    }
          
        Path filePath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());

           
        File directory = new File(UPLOAD_DIRECTORY);
        if (!directory.exists()) {
                directory.mkdirs();
        }
          
        file.transferTo(filePath.toFile());
            
        Items items = new Items();
            
    	Long makerId =  Long.parseLong(itemRegistrationForm.getMakerId());
    	Long purchase =  Long.parseLong(itemRegistrationForm.getPurchase());
    	Long listPrice =  Long.parseLong(itemRegistrationForm.getListPrice());
    	Long categoryId =  Long.parseLong(itemRegistrationForm.getCategoryId());
    	Long subCategoryId =  Long.parseLong(itemRegistrationForm.getSubCategoryId());
    	Long subSubCategoryId =  Long.parseLong(itemRegistrationForm.getSubSubCategoryId());
            
            
        items.setItemName(itemRegistrationForm.getItemName());
        items.setItemBody(itemRegistrationForm.getItemBody());
        items.setMakerId(makerId);
        items.setPurchase(purchase);
        items.setListPrice(listPrice);
        items.setCategoryId(categoryId);
        items.setSubCategoryId(subCategoryId);
        items.setSubSubCategoryId(subSubCategoryId);
        items.setItemImage(file.getOriginalFilename());

        itemRegistrationService.saveItems(items);

        model.addAttribute("imagePath",file.getOriginalFilename());

        return "/registration/item_registration"; 
        
	}
	
}
