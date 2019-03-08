package com.funcell.promotion.common.vo;

import com.funcell.promotion.moudle.entity.CateGoryEntity;
import lombok.Data;

import java.util.List;
@Data
public class CategoryVo extends BaseTomatoVo{
    List<CateGoryEntity> list;
    long total;
}
