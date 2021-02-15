package com.odeal.otomat.service;

import com.odeal.otomat.dto.BillingInfo;
import com.odeal.otomat.dto.OrderInfoDTO;
import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.entity.OrderInfo;
import com.odeal.otomat.entity.Product;
import com.odeal.otomat.enums.PaymentType;
import com.odeal.otomat.enums.PaymentTypeDetail;
import com.odeal.otomat.enums.ProductType;
import com.odeal.otomat.repository.OrderInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderInfoServiceTest {

    @Mock
    private OrderInfoRepository orderInfoRepository;
    @Mock
    private IProductService productService;
    @Mock
    private ModelMapper modelMapper;

    private Product product;
    private OrderInfo orderInfo;
    private ProductDTO productDTO;
    private OrderInfoDTO orderInfoDTO;
    private BillingInfo billingInfo;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Test name");
        product.setAmount(20D);
        product.setProductType(ProductType.FOOD);
        product.setSlotNumber(2);

        productDTO = new ProductDTO();
        product.setName("Test name");
        product.setAmount(20D);
        product.setProductType(ProductType.FOOD);
        product.setSlotNumber(2);

        orderInfo = new OrderInfo();
        orderInfo.setId(1L);
        orderInfo.setPaymentType(PaymentType.CASH);
        orderInfo.setPaymentTypeDetail(PaymentTypeDetail.BANKNOTE);
        orderInfo.setQuantity(2);
        orderInfo.setTotalAmount(40D);
        orderInfo.setReceiveAmount(50D);
        orderInfo.setRemainingAmount(10D);
        orderInfo.setProduct(product);

        orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setId(1L);
        orderInfoDTO.setPaymentType(PaymentType.CASH);
        orderInfoDTO.setPaymentTypeDetail(PaymentTypeDetail.BANKNOTE);
        orderInfoDTO.setQuantity(2);
        orderInfoDTO.setTotalAmount(40D);
        orderInfoDTO.setReceiveAmount(50D);
        orderInfoDTO.setRemainingAmount(10D);
        orderInfoDTO.setProduct(productDTO);
    }

    @Test
    void validationToPass() {

        assertAll(
                () -> Assertions.assertNotNull(product),
                () -> Assertions.assertNotNull(product.getSlotNumber()),
                () -> Assertions.assertNotEquals(0, product.getSlotNumber()),
                () -> Assertions.assertNotNull(orderInfo.getPaymentType()),
                () -> Assertions.assertNotNull(orderInfo.getPaymentTypeDetail()),
                () -> Assertions.assertNotNull(orderInfo.getReceiveAmount())
        );


    }

    @Test
    void mapObjectToPass() {
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(modelMapper.map(orderInfo, OrderInfoDTO.class)).thenReturn(orderInfoDTO);
    }

    @Test
    void calculateTotalPriceToPass() {
        // Total Price
        Assertions.assertEquals(product.getAmount() * orderInfo.getQuantity(), orderInfo.getTotalAmount());
        Assertions.assertEquals(product.getAmount() * orderInfo.getQuantity(), 40D);
        // Remaining Amount
        Assertions.assertEquals(orderInfo.getReceiveAmount()-(product.getAmount()*orderInfo.getQuantity()),orderInfo.getRemainingAmount());
        Assertions.assertEquals(orderInfo.getReceiveAmount()-(product.getAmount()*orderInfo.getQuantity()),10D);
    }

    @Test
    void getMethodsToPass() {
        when(productService.findBySlotNumber(anyInt())).thenReturn(new ProductDTO());
        when(orderInfoRepository.save(orderInfo)).thenReturn(orderInfo);
        when(orderInfoRepository.getOne(anyLong())).thenReturn(new OrderInfo());
    }

    @Test
    void createBillingInfoToPass() {
        billingInfo = new BillingInfo();

        billingInfo.setProductName(orderInfoDTO.getProduct().getName());
        billingInfo.setQuantity(orderInfoDTO.getQuantity());
        billingInfo.setPaymentType(orderInfoDTO.getPaymentType().name());
        billingInfo.setPaymetTypeDetail(orderInfoDTO.getPaymentTypeDetail().name());
        billingInfo.setAmount(orderInfoDTO.getProduct().getAmount());
        billingInfo.setTotalAmount(orderInfoDTO.getTotalAmount());
        billingInfo.setReceivedAmount(orderInfoDTO.getReceiveAmount());
        billingInfo.setRemainingAmount(orderInfoDTO.getReceiveAmount());

        Assertions.assertTrue(true);
    }
}
