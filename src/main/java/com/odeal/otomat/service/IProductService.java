package com.odeal.otomat.service;

import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductDTO save(ProductDTO issue);

    ProductDTO getById(Long id);

    TPage<ProductDTO> getAllPageable(Pageable pageable);

    Boolean delete(Long issue);

    ProductDTO findBySlotNumber(Long slotNumber);

}
