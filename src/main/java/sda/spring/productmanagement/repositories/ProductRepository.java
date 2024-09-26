package sda.spring.productmanagement.repositories;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import sda.spring.productmanagement.entities.ProductEntity;

import java.io.Serializable;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Remapper findById(SingularAttribute<AbstractPersistable, Serializable> id);
}
