package com.odeal.otomat.service;

import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.entity.Product;
import com.odeal.otomat.enums.ProductType;
import com.odeal.otomat.enums.ProductTypeDetail;
import com.odeal.otomat.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductServiceTest {

    private final String SOME_STRING = "someString";
    private final Double SOME_DOUBLE = 5D;

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ModelMapper modelMapper;

    private Product product;
    private ProductDTO productDTO;


    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName(SOME_STRING);
        product.setProductType(ProductType.DRINK);
        product.setProductTypeDetail(ProductTypeDetail.HOT);
        product.setAmount(SOME_DOUBLE);
        product.setSlotNumber(1);

    }

    @Test
    void findBySlotNumberToPass(){
        when(productRepository.findBySlotNumber(anyInt())).thenReturn(product);
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
    }

    @Test
    void getMethodsToPass(){
        when(productRepository.getOne(anyLong())).thenReturn(new Product());
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        when(productRepository.save(product)).thenReturn(product);
        doNothing().when(productRepository).deleteById(anyLong());

    }

}
