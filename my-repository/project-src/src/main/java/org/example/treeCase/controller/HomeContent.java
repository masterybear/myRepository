package org.example.treeCase.controller;

import org.example.treeCase.javaConfig.JavaConfig;
import org.example.treeCase.model.Brand;
import org.example.treeCase.model.PageBean;
import org.example.treeCase.service.BrandService;
import org.example.treeCase.service.UserService;
import org.example.treeCase.utils.JwtUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContent {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
    BrandService brandService = (BrandService) ctx.getBean("brandService");
    UserService userService = (UserService) ctx.getBean("userService");

    @PostMapping("/queryInfo")
    public PageBean<Brand> queryInfo(String token, Brand brand, Integer currentPage, Integer pageSize) {
        if (token == null || JwtUtils.verifyToken(token)) {
            return null;
        }
        if (currentPage == null || pageSize == null) {
            return null;
        }
        return brandService.queryInfoByPage(currentPage, pageSize, brand);
    }

    @PostMapping("/delInfo")
    public String delInfo(String token, int[] ids) {
        if (token == null || JwtUtils.verifyToken(token)) {
            return null;
        }
        if (ids == null) {
            return null;
        }
        brandService.delInfo(ids);
        return "success";
    }

    @PostMapping("/updateInfo")
    public String updateInfo(String token, Brand brand) {
        if (token == null || JwtUtils.verifyToken(token)) {
            return null;
        }
        brandService.updateInfo(brand);
        return "success";
    }

    @PostMapping("/createInfo")
    public String createInfo(String token, Brand brand) {
        if (token == null || JwtUtils.verifyToken(token)) {
            return null;
        }
        brandService.createInfo(brand);
        return "success";
    }

    @PostMapping("/queryName")
    public String queryName(String token, String email) {
        if (token == null || JwtUtils.verifyToken(token)) {
            return null;
        }
        if (email == null) {
            return null;
        }
        return userService.queryNameByEmail(email);
    }
}
