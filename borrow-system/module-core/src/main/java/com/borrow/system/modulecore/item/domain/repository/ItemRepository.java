package com.borrow.system.modulecore.item.domain.repository;

import com.borrow.system.modulecore.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
