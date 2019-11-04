package com.btsoft.jf.cloud.platform.security.vo.group;

/**
 * 群组VO接口
 * @author jeo_cb
 * @date 2019/11/4 16:18
 **/
public interface IGroupVO {

    /**
     * 获取群组id
     * @author jeo_cb
     * @date 2019/11/4
     * @return 群组id
     */
    Long getGroupId();

    /**
     * 设置群组名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  groupName 群组名称
     */
    void setGroupName(String groupName);

    /**
     * 设置群组名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  groupCode 群组编码
     */
    void setGroupCode(String groupCode);

    /**
     * 设置群组名称
     * @author jeo_cb
     * @date 2019/11/4
     * @param  groupDesc 群组描述
     */
    void setGroupDesc(String groupDesc);
}
