package com.coralsoft.domorder.repositories;

import com.coralsoft.domorder.models.PurchaseItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItemModel, UUID> {
}
