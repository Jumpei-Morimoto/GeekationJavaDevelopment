package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Inventory;
import com.example.demo.form.ItemOrderForm;
import com.example.demo.repository.InventoryRepository;


@Service
public class InventoryRegistrationServiceImpl implements InventoryRegistrationService{
	
	@Autowired
    private InventoryRepository inventoryRepository;
	
	
	@Transactional
	public void saveInventory(ItemOrderForm itemOrderForm) {
		
		 Long itemId = Long.parseLong(itemOrderForm.getItem_id());
		 Long orderingQuantity = Long.parseLong(itemOrderForm.getOrdering_quantity());
		 Long storeId = itemOrderForm.getStore_id();

		 // 既存の在庫情報を取得
		 Optional<Inventory> existingInventory = inventoryRepository.findByItemIdAndStoreId(itemId, storeId);

		 Inventory inventory;  
		 if (existingInventory.isPresent()) {
		        // 既存の在庫が存在する場合、数量を更新
			    System.out.println("既存の在庫が見つかりました: " + existingInventory.get());
			    inventory = existingInventory.get();
		        inventory.setQuantity(inventory.getQuantity() + orderingQuantity);
		        
		 } else {
		        // 既存の在庫が存在しない場合、新しい在庫として登録
			    System.out.println("新しい在庫を追加します");
			    inventory = new Inventory();
			    inventory .setItemId(itemId);
			    inventory .setStoreId(storeId);
			    inventory .setQuantity(orderingQuantity);
			    inventoryRepository.save(inventory);
		    }
		 inventoryRepository.save(inventory);

		    // 在庫を保存
		    
		
	}
		/*Inventory inventory = new Inventory();
		

		Long ItemID =  Long.parseLong(itemOrderForm.getItem_id());
		Long ordering_quantity =  Long.parseLong(itemOrderForm.getOrdering_quantity());
		
		inventory.setQuantity(ordering_quantity);
		inventory.setItemId(ItemID);
		inventory.setStoreId(itemOrderForm.getStore_id());
		
	
      inventoryRepository.save(inventory);

	}*/
	
	

}
