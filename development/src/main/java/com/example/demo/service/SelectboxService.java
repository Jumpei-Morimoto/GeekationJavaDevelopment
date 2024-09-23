package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Maker;
import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.entity.Sub_Category;
import com.example.demo.entity.Sub_SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MakerRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.repository.SubSubCategoryRepository;

@Service
public class SelectboxService {
	
	    @Autowired
	    private StoreRepository storeRepository;
	    @Autowired
	    private PositionRepository positionRepository;
	    @Autowired
	    private RolesRepository rolesRepository;
	    @Autowired
	    private CategoryRepository categoryRepository;
	    @Autowired
	    private SubCategoryRepository subCategoryRepository;
	    @Autowired
	    private SubSubCategoryRepository subSubCategoryRepository;
	    @Autowired
	    private MakerRepository makerRepository;

	    public List<Store> searchAll() {
	        return storeRepository.findAll();
	    }
	    public Store findById(long id) {
    		return storeRepository.findById(id).get();
    	}
	    
	    public List<Positions> searchAll2() {
	        return positionRepository.findAll();
	    }
	    public Positions findById2(long id) {
    		return positionRepository.findById(id).get();
    	}
	    
	    public List<Roles> searchAll3() {
	        return rolesRepository.findAll();
	    }
	    public Roles findById3(long id) {
    		return rolesRepository.findById(id).get();
    	}
	    
	    public List<Category> searchAll4() {
	        return categoryRepository.findAll();
	    }
	    public Category findById4(long id) {
    		return categoryRepository.findById(id).get();
    	}
	    
	    public List<Sub_Category> searchAll5() {
	        return subCategoryRepository.findAll();
	    }
	    public Sub_Category findById5(long id) {
    		return subCategoryRepository.findById(id).get();
    	}
	    
	    public List<Sub_SubCategory> searchAll6() {
	        return subSubCategoryRepository.findAll();
	    }
	    public Sub_SubCategory findById6(long id) {
    		return subSubCategoryRepository.findById(id).get();
    	}
	    
	    public List<Maker> searchAll7() {
	        return makerRepository.findAll();
	    }
	    public Maker findById7(long id) {
    		return makerRepository.findById(id).get();
    	}

}
