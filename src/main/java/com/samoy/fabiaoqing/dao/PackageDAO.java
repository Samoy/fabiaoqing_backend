package com.samoy.fabiaoqing.dao;

import com.samoy.fabiaoqing.domainobject.PackageDO;
import com.samoy.fabiaoqing.domainobject.PackageDOKey;

import java.util.List;

public interface PackageDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    int deleteByPrimaryKey(PackageDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    int insert(PackageDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    int insertSelective(PackageDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    PackageDO selectByPrimaryKey(PackageDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    int updateByPrimaryKeySelective(PackageDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_package
     *
     * @mbg.generated Thu May 30 09:54:43 CST 2019
     */
    int updateByPrimaryKey(PackageDO record);

    List<PackageDO> selectByParentId(String parentId);
}