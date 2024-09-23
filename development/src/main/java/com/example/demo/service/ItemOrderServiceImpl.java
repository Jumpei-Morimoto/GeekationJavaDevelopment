package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Orders;
import com.example.demo.form.ItemOrderForm;
import com.example.demo.repository.OrdersRepository;


@Service
public class ItemOrderServiceImpl implements ItemOrderService{
	
	@Autowired
    private OrdersRepository ordersRepository;
	
	
	@Override
	public void saveItemOrder(ItemOrderForm itemOrderForm) {
		Orders orders = new Orders();
		

		Long ItemID =  Long.parseLong(itemOrderForm.getItem_id());
		Long purchase =  Long.parseLong(itemOrderForm.getPurchase());
		Long ordering_quantity =  Long.parseLong(itemOrderForm.getOrdering_quantity());
		
		orders.setId(itemOrderForm.getId());
		orders.setUserId(itemOrderForm.getUser_id());
		orders.setItemId(ItemID);
		orders.setPurchase(purchase);
		orders.setStoreId(itemOrderForm.getStore_id());
		orders.setOrderingQuantity(ordering_quantity);
		orders.setTotal(itemOrderForm.getTotal());
	
      ordersRepository.save(orders);

	}

}
