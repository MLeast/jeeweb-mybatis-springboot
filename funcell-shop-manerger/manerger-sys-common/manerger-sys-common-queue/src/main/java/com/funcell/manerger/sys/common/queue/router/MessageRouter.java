package com.funcell.manerger.sys.common.queue.router;

public class MessageRouter extends AbsRouter{
    @Override
    public Message handler(Message message) {
        return message;
    }
}
