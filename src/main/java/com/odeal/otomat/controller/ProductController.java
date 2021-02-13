package com.odeal.otomat.controller;

import com.odeal.otomat.dto.ProductDTO;
import com.odeal.otomat.service.IProductService;
import com.odeal.otomat.util.ApiPaths;
import com.odeal.otomat.util.TPage;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiPaths.ProductCtrl.CTRL)
public class ProductController {

    private final IProductService productService;
    
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/pagination")
    public ResponseEntity<TPage<ProductDTO>> getAllByPagination(Pageable pageable) {
        TPage<ProductDTO> data = productService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable(value = "id", required = true) Long id) {
        ProductDTO productDTO = productService.getById(id);
        return ResponseEntity.ok(productDTO);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProject(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.save(productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }

}
