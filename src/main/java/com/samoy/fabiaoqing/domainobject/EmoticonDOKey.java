package com.samoy.fabiaoqing.domainobject;

import java.io.Serializable;

public class EmoticonDOKey implements Serializable {
    private static final long serialVersionUID = 3029579725060750311L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emoticon.object_id
     *
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    private String objectId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emoticon.parent_id
     *
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    private String parentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emoticon.object_id
     *
     * @return the value of t_emoticon.object_id
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emoticon.object_id
     *
     * @param objectId the value for t_emoticon.object_id
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emoticon.parent_id
     *
     * @return the value of t_emoticon.parent_id
     *
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emoticon.parent_id
     *
     * @param parentId the value for t_emoticon.parent_id
     *
     * @mbg.generated Thu May 30 11:31:34 CST 2019
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
}