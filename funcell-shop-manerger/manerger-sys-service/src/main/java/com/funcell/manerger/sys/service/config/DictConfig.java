package com.funcell.manerger.sys.service.config;


import com.funcell.manerger.sys.common.utils.SpringContextHolder;
import com.funcell.manerger.sys.service.modules.sys.service.IDictService;
import com.funcell.manerger.sys.ui.beetl.tag.dict.Dict;
import com.funcell.manerger.sys.ui.beetl.tag.dict.InitDictable;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @description: 字典初始化
 */

@Configuration
public class DictConfig implements InitDictable {

    /**
     * 数据字典初始化
     * @return
     */
    @Override
    public Map<String, List<Dict>> initDict() {
        Map<String, List<Dict>> dictMap = new HashMap<String, List<Dict>>();
        IDictService dictService= SpringContextHolder.getBean(IDictService.class);
        for (com.funcell.manerger.sys.service.modules.sys.entity.Dict dict : dictService.selectDictList()) {
                List<Dict> dictList = dictMap.get(dict.getCode());
                if (dictList != null) {
                    dictList.add(new Dict(dict.getLabel(), dict.getValue()));
                } else {
                    dictMap.put(dict.getCode(),
                            Lists.newArrayList(new Dict(dict.getLabel(), dict.getValue())));
                }
        }
        return dictMap;
    }
}
