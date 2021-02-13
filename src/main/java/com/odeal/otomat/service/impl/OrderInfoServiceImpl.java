package com.odeal.otomat.service.impl;

import com.odeal.otomat.dto.OrderInfoDTO;
import com.odeal.otomat.entity.OrderInfo;
import com.odeal.otomat.repository.OrderInfoRepository;
import com.odeal.otomat.service.IOrderInfoService;
import com.odeal.otomat.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    private final ModelMapper modelMapper;
    private final OrderInfoRepository orderInfoRepository;

    public OrderInfoServiceImpl(ModelMapper modelMapper, OrderInfoRepository orderInfoRepository) {
        this.modelMapper = modelMapper;
        this.orderInfoRepository = orderInfoRepository;
    }

    @Override
    public OrderInfoDTO save(OrderInfoDTO orderInfoDTO) {
        OrderInfo product = modelMapper.map(orderInfoDTO, OrderInfo.class);
        product = orderInfoRepository.save(product);
        orderInfoDTO.setId(product.getId());
        return orderInfoDTO;
    }

    @Override
    public OrderInfoDTO getById(Long id) {
        OrderInfo issue = orderInfoRepository.getOne(id);
        return modelMapper.map(issue, OrderInfoDTO.class);
    }


    @Override
    public TPage<OrderInfoDTO> getAllPageable(Pageable pageable) {
        Page<OrderInfo> data = orderInfoRepository.findAll(pageable);
        TPage<OrderInfoDTO> respnose = new TPage<OrderInfoDTO>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), OrderInfoDTO[].class)));
        return respnose;
    }

    public List<OrderInfoDTO> getAll() {
        List<OrderInfo> data = orderInfoRepository.findAll();
        return Arrays.asList(modelMapper.map(data, OrderInfoDTO[].class));
    }

    @Override
    public Boolean delete(Long issueId) {
        orderInfoRepository.deleteById(issueId);
        return true;
    }

}
