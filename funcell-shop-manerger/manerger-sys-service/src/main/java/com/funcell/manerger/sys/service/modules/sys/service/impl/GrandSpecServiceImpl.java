package com.funcell.manerger.sys.service.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.common.mybatis.wrapper.EntityWrapper;
import com.funcell.manerger.sys.common.utils.StringUtils;
import com.funcell.manerger.sys.service.modules.sys.entity.Dict;
import com.funcell.manerger.sys.service.modules.sys.entity.Grand;
import com.funcell.manerger.sys.service.modules.sys.entity.GrandClassify;
import com.funcell.manerger.sys.service.modules.sys.service.IGrandSpecService;
import com.funcell.manerger.sys.service.modules.sys.entity.GrandSpec;
import com.funcell.manerger.sys.service.modules.sys.mapper.GrandSpecMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


/**
*
* @version V1.0
* @package com.funcell.manerger.sys.service.modules.sys.service.impl
* @title: 商品规格服务实现
* @description: 显示商品规格服务实现
* @date: 2018-12-03 14:41:15
*/
@Transactional
@Service("grandSpecService")
public class GrandSpecServiceImpl  extends CommonServiceImpl<GrandSpecMapper,GrandSpec> implements  IGrandSpecService {

    @Override
    public List<GrandSpec> selectGrandSpecList() {
        return baseMapper.selectGrandSpecList();
    }

}