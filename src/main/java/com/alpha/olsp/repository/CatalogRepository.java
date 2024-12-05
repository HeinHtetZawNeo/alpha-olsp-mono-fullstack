package com.alpha.olsp.repository;

import com.alpha.olsp.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, String> {
    Optional<Catalog> findById(String name);

    List<Catalog> findAllByParentCatalogIsNull();

    Boolean existsByName(String name);
}
