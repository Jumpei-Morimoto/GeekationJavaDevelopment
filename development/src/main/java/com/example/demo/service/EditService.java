package com.example.demo.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Items;
import com.example.demo.entity.Maker;
import com.example.demo.entity.Store;
import com.example.demo.entity.Users;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.form.ItemOrderForm;
import com.example.demo.form.ItemRegistrationForm;
import com.example.demo.form.MakerUpdateForm;
import com.example.demo.form.StoreUpdateForm;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemsRepository;
import com.example.demo.repository.MakerRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UsersRepository;


@Service
@Transactional(rollbackFor = Exception.class)
public class EditService {
	
	@Autowired
	private AdminListService adminlistService;
	
	@Autowired
	  private InventoryRepository inventoryRepository;
	
	@Autowired 
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	  private InventoryRegistrationServiceImpl inventoryRegistrationService;
	
	/*管理者編集*/
	@Autowired
	private UsersRepository usersRepository2;
	
	public List<Users> searchAll(){
		return usersRepository2.findAll();
	}
	
	public Users findById(long id) {
		return usersRepository2.findById(id).get();
	}
	
	public void update3(AdministratorRegistrationForm usersUpdate3) {
		
		Users users4 = findById(usersUpdate3.getId());
		
		 Long userID4 =  Long.parseLong(usersUpdate3.getStoreName());
		 Long positionID4 =  Long.parseLong(usersUpdate3.getPositionsType());
	     Long roleID4 =  Long.parseLong(usersUpdate3.getRoleType());
		
		users4.setId(usersUpdate3.getId());
		users4.setStoreName(userID4);
		users4.setLastName(usersUpdate3.getLastName());
		users4.setFirstName(usersUpdate3.getFirstName());
		users4.setEmail(usersUpdate3.getEmail());
		users4.setPositionId(positionID4);
		users4.setRoleId(roleID4);
		users4.setPhone(usersUpdate3.getPhone());
		users4.setPassword(passwordEncoder.encode(usersUpdate3.getPassword()));
		users4.setUpdatedAt(new Date());
		usersRepository2.save(users4);
		
	}
	
	
	 public void deleteUsers(Long id) {
	        usersRepository2.deleteById(id);
	    }
	 
	 
	 /*メーカー編集*/

	 @Autowired
		private MakerRepository makerRepository2;
		
		public List<Maker> makerSearchAll(){
			return makerRepository2.findAll();
		}
		
		public Maker makerFindById(long id) {
			return makerRepository2.findById(id).get();
			}
		
		public void makerUpdate3(MakerUpdateForm makerUpdate3) {
			
			Maker maker4 = makerFindById(makerUpdate3.getId());
			
			
		     maker4.setId(makerUpdate3.getId());
		     maker4.setMakerName(makerUpdate3.getMakerName());
		     maker4.setUpdatedAt(new Date());
			 makerRepository2.save(maker4);
			
		}
		
		
		 public void deleteMaker(Long id) {
		        makerRepository2.deleteById(id);
		    }
		 
		 
		 /*店舗編集*/

		 @Autowired
			private StoreRepository storeRepository2;
			
			public List<Store> storeSearchAll(){
				return storeRepository2.findAll();
			}
			
			public Store storeFindById(long id) {
				return storeRepository2.findById(id).get();
				}
			
			public void storeUpdate3(StoreUpdateForm storeUpdate3) {
				
				Store store4 = storeFindById(storeUpdate3.getId());
				
				
			     store4.setId(storeUpdate3.getId());
			     store4.setStoreName(storeUpdate3.getStoreName());
			     store4.setUpdatedAt(new Date());
				 storeRepository2.save(store4);
				
			}
			
			
			 public void deleteStore(Long id) {
			        storeRepository2.deleteById(id);
			    }
			 /*商品編集*/
			 
			 @Autowired
			private ItemsRepository itemsRepository;
			 
			  public List<Items> itemSearchAll(){
					return itemsRepository.findAll();
				}
				
			  public Items itemFindById(long id) {
					return itemsRepository.findById(id).get();
				}
				
			  public void itemUpdate(ItemRegistrationForm itemUpdate) {
					
				  Items item2 = itemFindById(itemUpdate.getId());
					
				  Long makerId =  Long.parseLong(itemUpdate.getMakerId());
		    	  Long purchase =  Long.parseLong(itemUpdate.getPurchase());
		    	  Long listPrice =  Long.parseLong(itemUpdate.getListPrice());
		    	  Long categoryId =  Long.parseLong(itemUpdate.getCategoryId());
		    	  Long subCategoryId =  Long.parseLong(itemUpdate.getSubCategoryId());
		    	  Long subSubCategoryId =  Long.parseLong(itemUpdate.getSubSubCategoryId());
					
				  item2.setId(itemUpdate.getId());
				  item2.setItemName(itemUpdate.getItemName());
				  item2.setItemBody(itemUpdate.getItemBody());
				  item2.setItemImage(itemUpdate.getItemImage());
				  item2.setMakerId(makerId);
				  item2.setPurchase(purchase);
				  item2.setListPrice(listPrice);
				  item2.setCategoryId(categoryId);
				  item2.setSubCategoryId (subCategoryId );
				  item2.setSubSubCategoryId(subSubCategoryId);
				  item2.setUpdatedAt(new Date());
				  itemsRepository.save(item2);
				  
				  /*ここからinventoryの編集*/
				  final String name = SecurityContextHolder.getContext().getAuthentication().getName();
			      Long userId =adminlistService.findByEmail(name);
			      Users userInfo =adminlistService.findById(userId);
			       
				  Optional<Inventory> inventory = inventoryRepository.findByItemIdAndStoreId(itemUpdate.getId(), userInfo.getStoreName());
				  if(inventory.isPresent()) {
				  Long price =  Long.parseLong(itemUpdate.getPrice());
				  Inventory inventoryPrice; 
				  inventoryPrice =  inventory.get();
				  inventoryPrice.setPrice(price);
				  inventoryRepository.save(inventoryPrice);
				  }else {
					  ItemOrderForm itemOrderForm = new ItemOrderForm();
					  int intValue = 0;
					  Long longValue = (long) intValue;
					  String itemId=String.valueOf(itemUpdate.getId());
					  Long price =  Long.parseLong(itemUpdate.getPrice());
					  
					  itemOrderForm.setTotal(longValue);
					  itemOrderForm.setItem_id(itemId);
				      itemOrderForm.setUser_id(userInfo.getId());
				      itemOrderForm.setStore_id(userInfo.getStoreName());
				      itemOrderForm.setPurchase(itemUpdate.getPurchase());
				      itemOrderForm.setOrdering_quantity("0");
				      itemOrderForm.setPrice(price);
				      
				      inventoryRegistrationService.saveInventory(itemOrderForm);
				  }
			  }
			 
			public void deleteItems(Long id) {
				itemsRepository.deleteById(id);
				
			}

			
}
