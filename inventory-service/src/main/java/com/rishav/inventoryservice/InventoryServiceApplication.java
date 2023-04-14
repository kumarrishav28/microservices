package com.rishav.inventoryservice;

import com.rishav.inventoryservice.model.Inventory;
import com.rishav.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

/*

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){

		return args ->  {
			Inventory inventory1 = new Inventory();

			inventory1.setSkuCode("samsung");
			inventory1.setQuantity(1);

			inventoryRepository.save(inventory1);

			Inventory inventory2 = new Inventory();

			inventory2.setSkuCode("samsung_red");
			inventory2.setQuantity(2);

			inventoryRepository.save(inventory2);


		};

	}
*/

}
