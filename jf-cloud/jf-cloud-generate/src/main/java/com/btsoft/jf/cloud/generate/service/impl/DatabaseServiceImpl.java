package com.btsoft.jf.cloud.generate.service.impl;

import com.btsoft.jf.cloud.generate.entity.DatabaseEntity;
import com.btsoft.jf.cloud.generate.service.IDatabaseService;
import com.btsoft.jf.cloud.generate.vo.DatabaseVO;
import com.btsoft.jf.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 数据库Service实现类
 * @author jeo_cb
 * @date 2020/1/13
 **/
@Service
public class DatabaseServiceImpl extends BaseServiceImpl<DatabaseVO,DatabaseEntity,Long> implements IDatabaseService {
}
