package com.odeal.otomat.controller;

import com.odeal.otomat.dto.BillingInfo;
import com.odeal.otomat.dto.OrderInfoDTO;
import com.odeal.otomat.service.IOrderInfoService;
import com.odeal.otomat.util.ApiPaths;
import com.odeal.otomat.util.TPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.OrderInfoCtrl.CTRL)
public class OrderInfoController {
    
    private final IOrderInfoService orderInfoService;

    public OrderInfoController(IOrderInfoService orderInfoService) {
        this.orderInfoService = orderInfoService;
    }

    @GetMapping("/pagination")
    public ResponseEntity<TPage<OrderInfoDTO>> getAllByPagination(Pageable pageable) {
        TPage<OrderInfoDTO> data = orderInfoService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderInfoDTO> getById(@PathVariable(value = "id", required = true) Long id) {
        OrderInfoDTO orderInfoDTO = orderInfoService.getById(id);
        return ResponseEntity.ok(orderInfoDTO);
    }


    @PostMapping
    public ResponseEntity<BillingInfo> createProject(@RequestBody OrderInfoDTO orderInfo) throws Exception {
        return ResponseEntity.ok(orderInfoService.validateAndSaveOrderInfo(orderInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(orderInfoService.delete(id));
    }
}
