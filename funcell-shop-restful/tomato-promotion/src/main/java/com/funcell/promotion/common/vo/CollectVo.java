package com.funcell.promotion.common.vo;

import com.funcell.promotion.moudle.entity.CollectEntity;
import lombok.Data;

import java.util.List;

@Data
public class CollectVo extends BaseTomatoVo {
    List<CollectEntity> list;
    long total;
    boolean isCollect;
}
