package com.odeal.otomat.service.impl;

import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.entity.Product;
import com.odeal.otomat.repository.ProductRepository;
import com.odeal.otomat.service.IProductService;
import com.odeal.otomat.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCreatedDate(new Date());
        product = productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
    }

    @Override
    public ProductDTO getById(Long id) {
        Product issue = productRepository.getOne(id);
        return modelMapper.map(issue, ProductDTO.class);
    }


    @Override
    public TPage<ProductDTO> getAllPageable(Pageable pageable) {
        Page<Product> data = productRepository.findAll(pageable);
        TPage<ProductDTO> respnose = new TPage<ProductDTO>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProductDTO[].class)));
        return respnose;
    }

    public List<ProductDTO> getAll() {
        List<Product> data = productRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ProductDTO[].class));
    }

    @Override
    public Boolean delete(Long productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public ProductDTO findBySlotNumber(int slotNumber) {
        Product product = this.productRepository.findBySlotNumber(slotNumber);
        if (product == null) {
            return null;
        }
        return this.modelMapper.map(product, ProductDTO.class);
    }

}
