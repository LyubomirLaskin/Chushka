package chushka.service;

import chushka.domain.enteties.Product;
import chushka.domain.enteties.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.ProductRepository;
import chushka.util.ModelMapper;

import javax.inject.Inject;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepository.save(product);
    }
}
