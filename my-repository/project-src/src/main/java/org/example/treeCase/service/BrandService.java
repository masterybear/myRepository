package org.example.treeCase.service;

import org.example.treeCase.model.Brand;
import org.example.treeCase.model.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> queryInfo(Brand brand);

    PageBean<Brand> queryInfoByPage(Integer currentPage, Integer pageSize, Brand brand);

    void delInfo(int[] ids);

    void updateInfo(Brand brand);

    void createInfo(Brand brand);
}
