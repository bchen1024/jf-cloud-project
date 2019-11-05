package com.btsoft.jf.cloud.platform.security.service;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.PageResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupQueryDTO;
import com.btsoft.jf.cloud.platform.security.dto.group.GroupSaveDTO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupBaseVO;
import com.btsoft.jf.cloud.platform.security.vo.group.GroupVO;
import com.btsoft.jf.cloud.platform.security.vo.IGroupVO;

import java.util.List;

/**
 * 群组Service接口
 * @author jeo_cb
 * @date 2019/9/30
 **/
public interface IGroupService {

    /**
     * 分页查询群组列表
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 查询参数
     * @return 群组分页列表
     **/
    CommonResult<PageResult<GroupVO>> findGroupPage(GroupQueryDTO dto);

    /**
     * 根据群组id获取单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  id 群组
     * @return 群组信息
     **/
    CommonResult<GroupVO> findGroup(Long id);

    /**
     * 群组保存，存在群组id则修改，不存在则新增
     * @author jeo_cb
     * @date 2019/9/1
     * @param  dto 群组包存参数
     * @return 保存结果
     **/
    Result saveGroup(GroupSaveDTO dto);

    /**
     * 删除单个群组
     * @author jeo_cb
     * @date 2019/9/5
     * @param  dto 删除参数
     * @return 删除结果
     **/
    Result deleteGroup(BaseIdDTO dto);

    /**
     * 群组列表
     * @author jeo_cb
     * @date 2019/11/4
     * @param dto 查询参数
     * @return 群组列表
     */
    CommonResult<List<GroupBaseVO>> findGroupList(GroupQueryDTO dto);

    /**
     * 填充群组
     * @author jeo_cb
     * @date 2019/10/31
     * @param list 集合数据
     */
    void fillGroupInfo(List<? extends IGroupVO> list);

}
