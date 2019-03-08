package com.funcell.promotion.security.user;

import com.funcell.promotion.common.constant.TomatoConstant;
import com.funcell.promotion.security.utils.JwtTokenUtil;
import com.funcell.promotion.utils.Log;
import com.funcell.promotion.utils.RedisCacheUtils;
import com.funcell.promotion.utils.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void storeToken(String tokenKey, String value) {//存储token
        Log.i("store redis,tokenKey:" + tokenKey + ",value:" + value);
        try {
            RedisCacheUtils.getRedisCacheManager().set(RedisKeyUtils.getAccessTokenKey(tokenKey), value, TomatoConstant.Common.EXPIRE_TIME_REDIS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getInfoByToken(String tokenKey) {
        try {
            return (String) RedisCacheUtils.getRedisCacheManager().get(RedisKeyUtils.getAccessTokenKey(tokenKey));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }
}
