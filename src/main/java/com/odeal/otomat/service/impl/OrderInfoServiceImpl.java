package com.odeal.otomat.service.impl;

import com.odeal.otomat.dto.BillingInfo;
import com.odeal.otomat.dto.OrderInfoDTO;
import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.entity.OrderInfo;
import com.odeal.otomat.enums.PaymentType;
import com.odeal.otomat.enums.ProductTypeDetail;
import com.odeal.otomat.repository.OrderInfoRepository;
import com.odeal.otomat.service.IOrderInfoService;
import com.odeal.otomat.service.IProductService;
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
    private final IProductService productService;

    public OrderInfoServiceImpl(ModelMapper modelMapper, OrderInfoRepository orderInfoRepository, IProductService productService) {
        this.modelMapper = modelMapper;
        this.orderInfoRepository = orderInfoRepository;
        this.productService = productService;
    }

    @Override
    public BillingInfo validateAndSaveOrderInfo(OrderInfoDTO orderInfoDTO) {
        this.validateOrderInfo(orderInfoDTO);
        this.save(orderInfoDTO);
        return this.createBillingInfo(orderInfoDTO);
    }

    @Override
    public BillingInfo createBillingInfo(OrderInfoDTO orderInfoDTO) {
        BillingInfo billingInfo = new BillingInfo();
        billingInfo.setProductName(orderInfoDTO.getProduct().getName());
        billingInfo.setQuantity(orderInfoDTO.getQuantity());
        billingInfo.setPaymentType(orderInfoDTO.getPaymentType().name());
        billingInfo.setPaymetTypeDetail(orderInfoDTO.getPaymentTypeDetail().name());
        billingInfo.setAmount(orderInfoDTO.getProduct().getAmount());
        billingInfo.setTotalAmount(orderInfoDTO.getTotalAmount());
        billingInfo.setReceivedAmount(orderInfoDTO.getReceiveAmount());
        billingInfo.setRemainingAmount(orderInfoDTO.getReceiveAmount());
        return billingInfo;
    }

    private OrderInfoDTO validateOrderInfo (OrderInfoDTO orderInfoDTO) {

        if(orderInfoDTO.getProduct() == null || orderInfoDTO.getProduct().getSlotNumber() == 0) {
            throw new IllegalArgumentException("Ürün seçimi yapınız!");
        }

        ProductDTO productDTO = this.productService.findBySlotNumber(orderInfoDTO.getProduct().getSlotNumber());
        if (productDTO == null) {
            throw new IllegalArgumentException("Geçerli bir ürün kodu seçiniz");
        }

        orderInfoDTO.setProduct(productDTO);

        if (orderInfoDTO.getQuantity() == 0) {
            throw new IllegalArgumentException("Adet seçiniz!");
        }

        if (!productDTO.getProductTypeDetail().equals(ProductTypeDetail.HOT)
                && orderInfoDTO.getSugarQuantity() > 0) {
            throw new IllegalArgumentException("Yiyeceklerde ve soğuk içeceklerde şeker adedi seçilemez!");
        }

        if (orderInfoDTO.getPaymentType() == null) {
            throw new IllegalArgumentException("Ödeme şekli seçilmelidir!");
        }

        if (orderInfoDTO.getPaymentType().equals(PaymentType.CREDIT_CARD) && orderInfoDTO.getPaymentTypeDetail() == null) {
            throw new IllegalArgumentException("Kredi kartı ile yapılacak ödemelerde temaslı veya temassız seçimi yapılmalıdır!");
        }

        double totalAmount = productDTO.getAmount() * orderInfoDTO.getQuantity();
        orderInfoDTO.setTotalAmount(totalAmount);

        if (orderInfoDTO.getPaymentType().equals(PaymentType.CASH)) {

            if (orderInfoDTO.getPaymentTypeDetail() == null) {
                throw new IllegalArgumentException("Nakit yapılacak ödemelerde kağıt veya bozuk para seçimi yapılmalıdır!");
            }

            if (orderInfoDTO.getReceiveAmount() == null) {
                throw new IllegalArgumentException("Lütfen para giriniz!");
            }

            if (orderInfoDTO.getReceiveAmount() < totalAmount) {
                throw new IllegalArgumentException("Girilen tutar toplam tutardan küçük olamaz!");
            }
            orderInfoDTO.setReceiveAmount(orderInfoDTO.getReceiveAmount());
            orderInfoDTO.setRemainingAmount(orderInfoDTO.getReceiveAmount() - totalAmount);
        } else {
            orderInfoDTO.setReceiveAmount(totalAmount);
            orderInfoDTO.setRemainingAmount(0D);
        }

        return orderInfoDTO;
    }

    @Override
    public OrderInfoDTO save(OrderInfoDTO orderInfoDTO) {
        OrderInfo orderInfo = modelMapper.map(orderInfoDTO, OrderInfo.class);
        orderInfo = orderInfoRepository.save(orderInfo);
        orderInfoDTO.setId(orderInfo.getId());
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
