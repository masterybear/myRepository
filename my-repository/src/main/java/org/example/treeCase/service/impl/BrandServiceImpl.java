package org.example.treeCase.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.treeCase.mapper.BrandMapper;
import org.example.treeCase.model.Brand;
import org.example.treeCase.model.PageBean;
import org.example.treeCase.service.BrandService;
import org.example.treeCase.utils.SqlSessionFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    public BrandServiceImpl(PageBean<Brand> brandPageBean) {
        this.brandPageBean = brandPageBean;
    }
    private final PageBean<Brand> brandPageBean;
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> queryInfo(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.queryInfo(brand);
        sqlSession.close();
        return brands;
    }

    @Override
    public PageBean<Brand> queryInfoByPage(Integer currentPage, Integer pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * pageSize;
        //设置模糊表达式
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%"+companyName+"%");
        }
        //执行查询
        List<Brand> rows = mapper.queryInfoByPage(begin, pageSize, brand);
        Integer totalCount = mapper.selectTotalCount(brand);

        brandPageBean.setRows(rows);
        brandPageBean.setTotalCount(totalCount);

        sqlSession.close();
        return brandPageBean;
    }

    @Override
    public void delInfo(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delById(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateInfo(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateInfo(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void createInfo(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.createInfo(brand);
        sqlSession.commit();
        sqlSession.close();
    }
}
