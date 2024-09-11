package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ApiInventory;
import com.example.demo.entity.ApiOrders;
import com.example.demo.entity.ApiStore;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Items;
import com.example.demo.entity.Items2;
import com.example.demo.entity.ItemsReference;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Store;
import com.example.demo.entity.Users;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ItemsReferenceRepository;
import com.example.demo.repository.ItemsRepository;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UsersRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class ApiController {
	
	@Autowired
	  private StoreRepository storeRepository;
	
	@Autowired
	  private InventoryRepository inventoryRepository;
	
	@Autowired
	  private OrdersRepository ordersRepository;
	
	@Autowired
	  private ItemsReferenceRepository itemsReferenceRepository;
	
	@Autowired
	  private ItemsRepository itemsRepository;
	
	@Autowired
	  private UsersRepository usersRepository;
	
	@RequestMapping("/API")
	public String api() {
		/*在庫リスト取得*/
		List<Inventory> inventoryList = inventoryRepository.findAll();
		/*在庫の商品idだけ取得*/
		List<Long> itemIdList = new ArrayList<>();

	    for (Inventory inventory : inventoryList) {
	        itemIdList.add(inventory.getItemId());
	    }
	    /*ここまで商品id*/
	    /*ここから在庫の商品情報取得*/
	    List<ItemsReference> itemStockList = new ArrayList<>();
	    
	    for(Long itemIdList2:itemIdList) {
	    Optional<ItemsReference> itemStockList3 = itemsReferenceRepository.findById(itemIdList2);
	    itemStockList3.ifPresent(itemStockList::add);
	    }
	    /*ここまで在庫の商品情報取得*/
	    /*全商品情報取得*/
	    List<ItemsReference> AllItemStockList = new ArrayList<>();
	    AllItemStockList = itemsReferenceRepository.findAll();
	    /*ここまで*/
	    /*ここから商品データをjsonに変換して出力*/
	    Items2 itemStockListjson = new Items2();
	    List<Items2> itemlist = new ArrayList<>();
	    
	    for(int i=0; i<AllItemStockList.size(); i++) {
	    	itemStockListjson.setId((AllItemStockList.get(i).getId()));
		    itemStockListjson.setCategory((AllItemStockList.get(i).getItemCategories().getCategory()));
		    itemStockListjson.setSubCategory((AllItemStockList.get(i).getItemSubCategories().getSubCategory()));
		    itemStockListjson.setSubSubCategory((AllItemStockList.get(i).getItemSubSubCategories().getSubSubCategory()));
		    itemStockListjson.setItemName((AllItemStockList.get(i).getItemName()));
		    itemStockListjson.setItemBody((AllItemStockList.get(i).getItemBody()));
		    itemStockListjson.setPurchase((AllItemStockList.get(i).getPurchase()));
		    itemStockListjson.setListPrice((AllItemStockList.get(i).getListPrice()));

	    itemlist.add(itemStockListjson);    
	   
	}	
	 System.out.println(itemlist);
	 String ITEM =new String();
	 Gson gson = new GsonBuilder().setPrettyPrinting().create();
	 ITEM = gson.toJson(itemlist);
	 
     return ITEM;
     }
	@RequestMapping("/API2")
	public String api2() {
		/*店舗・在庫・発注履歴リスト取得*/
		List<Store> store =new ArrayList();
		store = storeRepository.findAll();
		List<Inventory> inventory = new ArrayList();
		inventory = inventoryRepository.findAll();
		List<Orders> orders = new ArrayList();
		orders = ordersRepository.findAll();		
		List<ApiStore> apiStoreList = new ArrayList<>();
		
		for(int i=0; i<store.size(); i++) {
            ApiStore apiStore = new ApiStore();  
    		List<ApiInventory> apiInventoryList = new ArrayList<>();
    		
    		/*店の住所・名前*/
			apiStore.setStoreName((store.get(i).getStoreName()));
			apiStore.setAddress((store.get(i).getAddress()));
			Long a = store.get(i).getId();
			/*在庫一覧*/
		  for(int n=0; n<inventory.size(); n++) {	
			if(inventory.get(n).getStoreId() == a) {
				ApiInventory apiInventory = new ApiInventory();
                Optional<Items> apiItem = itemsRepository.findById(inventory.get(n).getItemId() );
                apiInventory.setItemName(apiItem.get().getItemName());
				apiInventory.setPrice(inventory.get(n).getPrice());
				apiInventory.setQuantity(inventory.get(n).getQuantity());
				apiInventoryList.add(apiInventory);   
			}
		  }
		/*在庫ここまで*/
		/*ここから発注履歴作成*/
		  List<ApiOrders> apiOrdersList = new ArrayList<>();
		  Long cost= (long) 0;
		  for(int n=0; n<orders.size(); n++) {	
				if(orders.get(n).getStoreId() == a) {
					ApiOrders apiOrders = new ApiOrders();
	                Optional<Items> apiItem = itemsRepository.findById(orders.get(n).getItemId() );
	                Optional<Users> apiUsers = usersRepository.findById(orders.get(n).getUserId() );
	                apiOrders.setItemName(apiItem.get().getItemName());
	                apiOrders.setUserName(apiUsers.get().getFirstName()+apiUsers.get().getLastName());
	                apiOrders.setOrderingQuantity(orders.get(n).getOrderingQuantity());
	                apiOrders.setTotal((orders.get(n).getPurchase())*(orders.get(n).getOrderingQuantity()));
					apiOrdersList.add(apiOrders);   
				}
				
			  }
		  
		
		    /*在庫情報を格納*/
			apiStore.setInventories(apiInventoryList);
			apiStore.setOrders(apiOrdersList);
			apiStoreList.add(apiStore);	 
		}
		
		String STORE =new String();
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 STORE = gson.toJson(apiStoreList);
		 System.out.println(STORE);
		return STORE;
	}
}
