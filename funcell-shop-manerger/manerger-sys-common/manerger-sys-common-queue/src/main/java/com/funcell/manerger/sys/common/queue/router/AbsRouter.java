package com.funcell.manerger.sys.common.queue.router;

public abstract class AbsRouter {
    public abstract Message handler(Message messageBean);
}
