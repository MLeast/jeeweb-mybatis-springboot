package com.funcell.manerger.sys.common.idgenerator.local.condition;

import com.funcell.manerger.sys.common.idgenerator.condition.IdGeneratorCondition;

public class CustomLocalIdGeneratorCondition extends IdGeneratorCondition {
    public CustomLocalIdGeneratorCondition() {
        super("idgenerator.type", "custom_local");
    }
}
