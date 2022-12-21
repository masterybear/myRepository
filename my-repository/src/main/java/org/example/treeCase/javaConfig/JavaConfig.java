package org.example.treeCase.javaConfig;

import org.example.treeCase.model.Brand;
import org.example.treeCase.model.PageBean;
import org.example.treeCase.model.Result;
import org.example.treeCase.model.User;
import org.example.treeCase.service.BrandService;
import org.example.treeCase.service.UserService;
import org.example.treeCase.service.impl.BrandServiceImpl;
import org.example.treeCase.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean("user")
    User getUser() {
        return new User();
    }

    @Bean("userService")
    UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean("brandService")
    BrandService getBrandService(){
        return new BrandServiceImpl(brandPageBean());
    }

    @Bean
    PageBean<Brand> brandPageBean() {
        return new PageBean<Brand>() {};
    }

    @Bean("result")
    Result getResult() {
        return new Result();
    }

}
