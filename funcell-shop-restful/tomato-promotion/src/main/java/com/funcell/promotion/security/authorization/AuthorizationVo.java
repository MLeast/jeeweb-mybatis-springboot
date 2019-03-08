package com.funcell.promotion.security.authorization;

import com.funcell.promotion.common.vo.BaseTomatoVo;
import lombok.Data;

@Data
public class AuthorizationVo extends BaseTomatoVo {
    String openId;
    String sessionKey;
    String token;
    int errorCode;
    String errorMsg;
}
