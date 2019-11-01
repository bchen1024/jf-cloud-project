package com.btsoft.jf.cloud.platform.config.service.impl;

import com.btsoft.jf.cloud.core.base.dto.impl.BaseIdDTO;
import com.btsoft.jf.cloud.core.base.result.impl.CommonResult;
import com.btsoft.jf.cloud.core.base.result.impl.Result;
import com.btsoft.jf.cloud.core.context.impl.JfCloud;
import com.btsoft.jf.cloud.core.enums.impl.LanguageEnum;
import com.btsoft.jf.cloud.core.enums.impl.OperationTypeEnum;
import com.btsoft.jf.cloud.core.util.CommonResultUtils;
import com.btsoft.jf.cloud.core.util.EntityUtils;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationQueryDTO;
import com.btsoft.jf.cloud.platform.config.dto.organization.OrganizationSaveDTO;
import com.btsoft.jf.cloud.platform.config.entity.OrganizationEntity;
import com.btsoft.jf.cloud.platform.config.mapper.IOrganizationMapper;
import com.btsoft.jf.cloud.platform.config.service.IOrganizationService;
import com.btsoft.jf.cloud.platform.config.vo.organization.OrganizationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织架构Service实现类
 * @author jeo_cb
 * @date 2019/10/18
 **/
@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private IOrganizationMapper mapper;

    @Override
    public CommonResult<List<OrganizationVO>> findOrganizationList(OrganizationQueryDTO dto) {
        OrganizationEntity entity= EntityUtils.dtoToEntity(OrganizationEntity.class,dto);
        List<OrganizationEntity> list=mapper.findList(entity);
        String lang= JfCloud.getCurrent().getLanguage();
        List<OrganizationVO> organizationList=list.stream().map(v->{
            OrganizationVO vo=new OrganizationVO();
            BeanUtils.copyProperties(v,vo);
            vo.setValue(vo.getOrgId());
            if(LanguageEnum.EN.getKey().equals(lang)){
                vo.setTitle(vo.getOrgEn());
            }else{
                vo.setTitle(vo.getOrgCn());
            }
            return vo;
        }).collect(Collectors.toList());

        List<OrganizationVO> result=organizationList.stream().filter(v-> v.getParentId()==null || v.getParentId()==0)
                .collect(Collectors.toList());
        result.forEach(v->{
            recurseOrganization(organizationList,v.getOrgId(),v);
        });
        return CommonResultUtils.success(result);
    }

    @Override
    public Result saveOrganization(OrganizationSaveDTO dto) {
        OrganizationEntity entity= EntityUtils.dtoToEntity(OrganizationEntity.class,dto);
        int rows;
        if(entity.getOrgId()==null){
            rows=mapper.createSingle(entity);
        }else{
            rows=mapper.updateSingle(entity);
        }
        return CommonResultUtils.result(rows, OperationTypeEnum.Save);
    }

    @Override
    public CommonResult<OrganizationVO> findOrganization(Long id) {
        OrganizationEntity appEntity=mapper.findSingleById(id);
        return CommonResultUtils.result(OrganizationVO.class,appEntity);
    }

    @Override
    public Result deleteOrganization(BaseIdDTO dto) {
        int rows=mapper.deleteSingleById(dto.getId());
        //TODO 删除应用关联数据
        return CommonResultUtils.result(rows,OperationTypeEnum.Delete);
    }

    private void recurseOrganization(List<OrganizationVO> all, Long parentId, OrganizationVO item){
        List<OrganizationVO> children=all.stream().filter(v->parentId.equals(v.getParentId()))
                .collect(Collectors.toList());
        item.setChildren(children);
        if(item.getParentIds()==null){
            item.setParentIds(new ArrayList<>());
        }
        item.getParentIds().add(item.getParentId());
        children.forEach(v->{
            recurseOrganization(all,v.getOrgId(),v);
        });
    }
}
