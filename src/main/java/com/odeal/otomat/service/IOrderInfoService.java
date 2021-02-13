package com.odeal.otomat.service;

import com.odeal.otomat.dto.OrderInfoDTO;
import com.odeal.otomat.util.TPage;
import org.springframework.data.domain.Pageable;

public interface IOrderInfoService {

    OrderInfoDTO save(OrderInfoDTO issue);

    OrderInfoDTO getById(Long id);

    TPage<OrderInfoDTO> getAllPageable(Pageable pageable);

    Boolean delete(Long issue);

}
