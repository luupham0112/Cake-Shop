package com.phanmem.cakeshop.controller.admin;

import com.phanmem.cakeshop.dto.CategoryDto;
import com.phanmem.cakeshop.dto.ProductDto;
import com.phanmem.cakeshop.entity.Category;
import com.phanmem.cakeshop.entity.Product;
import com.phanmem.cakeshop.repository.ProductRepository;
import com.phanmem.cakeshop.service.CategoryService;
import com.phanmem.cakeshop.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductController {
    public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/imageProduct";
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getAllProduct(ModelMap modelMap){
        List<Product> productList= productService.findAll();
        modelMap.addAttribute("products",productList);
        return "admin/product";

    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories",categoryService.findAll() );
        return "admin/product-add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") ProductDto dto,
                             @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        Product entity = new Product();
        BeanUtils.copyProperties(dto,entity);
        entity.setCategory(categoryService.findById(dto.getCategoryId()).get());

        //luu file anh
//        String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        entity.setImageName(fileName);
//        Product product= productService.save(entity);
//        String uploadDir="/imageProduct" + product.getProductId();
//        Path uploadPath= Paths.get(uploadDir);
//        if(!Files.exists(uploadPath)){
//            Files.createDirectories(uploadPath);
//        }
//        try(InputStream inputStream= multipartFile.getInputStream()){
//            Path filePath= uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath,StandardCopyOption.REPLACE_EXISTING);
//
//        } catch (IOException e){
//            throw  new IOException("Could not save file");
//        }
        String imageUUID=null;
        if(!multipartFile.isEmpty()){
            imageUUID=multipartFile.getOriginalFilename();
            Path fileNamePath= Paths.get(uploadDir,imageUUID);
            Files.write(fileNamePath, multipartFile.getBytes());
        } else{

        }
        entity.setImageName(imageUUID);
        productService.save(entity);
        return "redirect:/admin/product";

    }
    @GetMapping("/delete/{id}")

    public String deleteProduct(ModelMap modelMap, @PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/product";
    }

}
