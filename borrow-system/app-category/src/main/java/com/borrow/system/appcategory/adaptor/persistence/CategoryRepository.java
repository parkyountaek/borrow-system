package com.borrow.system.appcategory.adaptor.persistence;

import com.borrow.system.modulecore.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
