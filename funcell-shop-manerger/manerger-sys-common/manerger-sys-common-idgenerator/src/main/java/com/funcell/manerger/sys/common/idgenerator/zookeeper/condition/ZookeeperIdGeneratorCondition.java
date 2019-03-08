package com.funcell.manerger.sys.common.idgenerator.zookeeper.condition;

import com.funcell.manerger.sys.common.idgenerator.condition.IdGeneratorCondition;

public class ZookeeperIdGeneratorCondition extends IdGeneratorCondition {
    public ZookeeperIdGeneratorCondition() {
        super("idgenerator.type", "zookeeper");
    }
}
