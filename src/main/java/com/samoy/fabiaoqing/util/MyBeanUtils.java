package com.samoy.fabiaoqing.util;

import com.samoy.fabiaoqing.dao.TagDAO;
import com.samoy.fabiaoqing.domainobject.CategoryDO;
import com.samoy.fabiaoqing.domainobject.EmoticonDO;
import com.samoy.fabiaoqing.domainobject.PackageDO;
import com.samoy.fabiaoqing.domainobject.TagDO;
import com.samoy.fabiaoqing.dto.CategoryDTO;
import com.samoy.fabiaoqing.dto.EmoticonDTO;
import com.samoy.fabiaoqing.dto.PackageDTO;
import com.samoy.fabiaoqing.dto.TagDTO;
import com.samoy.fabiaoqing.viewobject.CategoryVO;
import com.samoy.fabiaoqing.viewobject.EmoticonVO;
import com.samoy.fabiaoqing.viewobject.PackageVO;
import com.samoy.fabiaoqing.viewobject.TagVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaBean转换类
 *
 * @author Samoy
 * @date 2019-05-31
 */
public class MyBeanUtils {

    /**
     * 列表展示表情包的最大张数
     */
    private static final int MAX_LIST_COUNT = 3;


    /**
     * 类别DO转DTO
     *
     * @param categoryDO 类别DO
     * @return 类别DTO
     */
    public static CategoryDTO convertCategoryDOToDTO(CategoryDO categoryDO) {
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(categoryDO, categoryDTO);
        return categoryDTO;
    }

    /**
     * 类别DTO转VO
     *
     * @param categoryDTO 类别DTO
     * @return 类别VO
     */
    public static CategoryVO convertCategoryDTOToVO(CategoryDTO categoryDTO) {
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(categoryDTO, categoryVO);
        return categoryVO;
    }

    /**
     * 表情包DO转DTO
     *
     * @param packageDO       表情包DO
     * @param emoticonDTOList 表情列表
     * @return 表情包DTO
     */
    public static PackageDTO convertPackageDOToDTO(PackageDO packageDO, List<EmoticonDTO> emoticonDTOList) {
        PackageDTO packageDTO = new PackageDTO();
        BeanUtils.copyProperties(packageDO, packageDTO);
        packageDTO.setEmoticonDTOList(emoticonDTOList);
        return packageDTO;
    }


    /**
     * 表情包DTO转VO
     *
     * @param packageDTO 表情包DTO
     * @return 表情包VO
     */
    public static PackageVO convertPackageDTOToVO(PackageDTO packageDTO) {
        PackageVO packageVO = new PackageVO();
        BeanUtils.copyProperties(packageDTO, packageVO);
        List<EmoticonDTO> emoticonDTOList = packageDTO.getEmoticonDTOList();
        if (!CollectionUtils.isEmpty(emoticonDTOList)) {
            packageVO.setCount(emoticonDTOList.size());
            packageVO.setList(emoticonDTOList.subList(0, Math.min(emoticonDTOList.size(), MAX_LIST_COUNT))
                    .stream().map(MyBeanUtils::convertEmoticonDTOToVO).collect(Collectors.toList()));
        }
        return packageVO;
    }

    /**
     * 表情DO转DTO
     *
     * @param emoticonDO 表情DO
     * @return 表情DTO
     */
    public static EmoticonDTO convertEmoticonDOToDTO(EmoticonDO emoticonDO) {
        EmoticonDTO emoticonDTO = new EmoticonDTO();
        BeanUtils.copyProperties(emoticonDO, emoticonDTO);
        return emoticonDTO;
    }

    /**
     * 表情DTO转VO
     *
     * @param emoticonDTO 表情DTO
     * @return 表情VO
     */
    public static EmoticonVO convertEmoticonDTOToVO(EmoticonDTO emoticonDTO) {
        EmoticonVO emoticonVO = new EmoticonVO();
        BeanUtils.copyProperties(emoticonDTO, emoticonVO);
        return emoticonVO;
    }

    /**
     * 标签DO转DTO
     *
     * @param tagDO 标签DO
     * @return 标签DTO
     */
    public static TagDTO convertTagDOToDTO(TagDO tagDO) {
        TagDTO tagDTO = new TagDTO();
        BeanUtils.copyProperties(tagDO, tagDTO);
        return tagDTO;
    }

    /**
     * 标签DTO转VO
     *
     * @param tagDTO 标签DTO
     * @return 标签VO
     */
    public static TagVO convertTagDTOTOVO(TagDTO tagDTO) {
        TagVO tagVO = new TagVO();
        BeanUtils.copyProperties(tagDTO, tagVO);
        return tagVO;
    }
}
