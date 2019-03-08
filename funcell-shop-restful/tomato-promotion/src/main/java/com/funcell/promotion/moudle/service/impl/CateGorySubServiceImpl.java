package com.funcell.promotion.moudle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funcell.promotion.moudle.entity.CateGorySubEntity;
import com.funcell.promotion.moudle.mapper.CateGorySubMapper;
import com.funcell.promotion.moudle.service.ICateGorySubService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cateGorySubService")
@Transactional
public class CateGorySubServiceImpl extends ServiceImpl<CateGorySubMapper, CateGorySubEntity> implements ICateGorySubService {

    @Override
    public Page<CateGorySubEntity> getCateGorySubList(Page<CateGorySubEntity> page, int parentOptId) {


        return page.setRecords(baseMapper.getCategorySubList(page, parentOptId));
    }


    @Override
    public boolean insertCateGorySub(JSONObject json) {
        //前端传入父级opt_id获取分类子集

        int optId = json.getIntValue("opt_id");
        int order = json.getIntValue("order");
        int level = json.getIntValue("level");
        String icon = json.getString("icon");
        String opt_name = json.getString("opt_name");
        int parent_opt_id = json.getIntValue("parent_opt_id");

        CateGorySubEntity cateGorySubEntity = this.getCateGoryEntity(parent_opt_id, optId);

        if (null == cateGorySubEntity) {

            cateGorySubEntity = new CateGorySubEntity();
            cateGorySubEntity.setOpt_id(optId);
            cateGorySubEntity.setParent_opt_id(parent_opt_id);


        }
        cateGorySubEntity.setOrder(order);
        cateGorySubEntity.setLevel(level);
        cateGorySubEntity.setIcon(icon);
        cateGorySubEntity.setOpt_name(opt_name);


        return insertOrUpdate(cateGorySubEntity);

    }


    /**
     * 获取下级分类集合
     *
     * @param OptId
     * @return
     */
    @Override
    public List<CateGorySubEntity> getCateGoryEntitySubList(int OptId) {

        return baseMapper.getCateGoryEntitySubList(OptId);
    }

    @Override
    public CateGorySubEntity getCateGoryEntity(int parentOptId, int optId) {

        return baseMapper.getCateGoryEntity(parentOptId, optId);
    }
}
