package com.borrow.system.appcategory.application.port.in;

import com.borrow.system.modulecore.category.domain.Category;

public record CreateCategoryCommand(String name,
                                    int borrowDay,
                                    boolean isQr) {
    public Category toEntity() {
        return Category.create(name, borrowDay, isQr);
    }
}
