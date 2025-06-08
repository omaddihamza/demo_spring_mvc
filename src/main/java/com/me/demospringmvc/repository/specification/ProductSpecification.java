package com.me.demospringmvc.repository.specification;

import com.me.demospringmvc.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> productNameLike(String searchText) {
        return (root, query, cb) -> {
            query.distinct(true);
            if (searchText == null || searchText.trim().isEmpty()) {
                return null;
            }
            return cb.like(cb.lower(root.get("name")), "%" + searchText.toLowerCase() + "%");
        };

}
}
