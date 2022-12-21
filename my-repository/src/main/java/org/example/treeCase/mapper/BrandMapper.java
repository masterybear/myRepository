package org.example.treeCase.mapper;

import org.apache.ibatis.annotations.*;
import org.example.treeCase.model.Brand;

import java.util.List;

public interface BrandMapper {
    List<Brand> queryInfo(@Param("brand") Brand brand);

    List<Brand> queryInfoByPage(@Param("begin") int begin, @Param("size") int pageSize, @Param("brand") Brand brand);

    Integer selectTotalCount(@Param("brand") Brand brand);

    void delById(@Param("ids") int[] ids);

    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},state= #{state} where id = #{id}")
    @ResultMap("BrandMap")
    void updateInfo(Brand brand);

    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{state})")
    @ResultMap("BrandMap")
    void createInfo(Brand brand);
}
