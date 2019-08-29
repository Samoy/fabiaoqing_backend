package com.samoy.fabiaoqing.dao;

import com.samoy.fabiaoqing.domainobject.TagDO;

import java.util.List;

public interface TagDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    int deleteByPrimaryKey(String objectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    int insert(TagDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    int insertSelective(TagDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    TagDO selectByPrimaryKey(String objectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    int updateByPrimaryKeySelective(TagDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_tag
     *
     * @mbg.generated Sun Jul 14 08:53:04 CST 2019
     */
    int updateByPrimaryKey(TagDO record);

    List<TagDO> selectTagsRandom(Integer count);
}