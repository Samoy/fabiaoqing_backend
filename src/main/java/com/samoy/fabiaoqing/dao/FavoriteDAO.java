package com.samoy.fabiaoqing.dao;

import com.samoy.fabiaoqing.domainobject.FavoriteDO;

import java.util.List;

public interface FavoriteDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    int deleteByPrimaryKey(String objectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    int insert(FavoriteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    int insertSelective(FavoriteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    FavoriteDO selectByPrimaryKey(String objectId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    int updateByPrimaryKeySelective(FavoriteDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_favorite
     *
     * @mbg.generated Wed Nov 20 13:59:28 CST 2019
     */
    int updateByPrimaryKey(FavoriteDO record);

    int deleteByEmoticonId(String userId, String emoticonId);

    List<FavoriteDO> selectByUserId(String userId);
}