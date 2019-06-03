package chushka.repository;

import chushka.domain.enteties.Product;

public interface ProductRepository extends GenericRepository<Product, String> {

    Product findByName(String name);
}
