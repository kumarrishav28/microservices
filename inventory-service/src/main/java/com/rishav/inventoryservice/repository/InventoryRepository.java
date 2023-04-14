package com.rishav.inventoryservice.repository;

import com.rishav.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

        List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
