package com.funcell.manerger.sys.common.sms.disruptor;

import com.funcell.manerger.sys.common.sms.data.SmsResult;

import java.io.Serializable;

public interface SmsHandlerCallBack extends Serializable {
    void onResult(SmsResult smsResult);
}
