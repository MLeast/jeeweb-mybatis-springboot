package com.funcell.manerger.sys.common.email.disruptor;


import com.funcell.manerger.sys.common.email.data.EmailResult;

public interface EmailHandlerCallBack {
    void onResult(EmailResult emailResult);
}