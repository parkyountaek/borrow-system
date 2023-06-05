package com.borrow.system.appitem.adapter.persistence;

import com.borrow.system.modulecore.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
