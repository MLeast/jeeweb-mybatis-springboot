package com.funcell.promotion.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/***
 * 用于设置redis_key
 */
public class RedisKeyUtils {
    //token缓存key
    private static String ACCESS_TOKEN_PREFIX = "funcell:tomato:promotion";

    private static String REDIS_FORM_KEY = "funcell:tomato:promotion:formid";

    private static String ACCESS_TOKEN_WX_KEY = "funcell:tomato:promotion:wx";

    private static String QRCODE_SHOW_KEY = "funcell:tomato:promotion:qr";

    private static String QRCODE_PRODUCT_KEY = "funcell:tomato:promotion:qrcode:product";

    private static String LOG_SRARCH_LKEY = "funcell:tomato:promotion:log:search";
    private static String LOG_Statistics_LKEY = "funcell:tomato:promotion:log:Statistics";

    private static String ORDER_TIMES = "funcell:tomato:promotion:order:times";

    private static String RATE_CONFIG = "funcell:tomato:promotion:rate:config";

    /**
     * token前缀key
     *
     * @param token
     * @return
     */
    public static String getAccessTokenKey(String token) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(token), "非法请求token参数不存在");
        return ACCESS_TOKEN_PREFIX + ":" + token;
    }

    public static String getOrderKey() {
        return ORDER_TIMES;
    }

    public static String getAccessWxTokenKey(String token) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(token), "非法请求token参数不存在");
        return ACCESS_TOKEN_WX_KEY + ":" + token;
    }

    public static String getSearchLogKey(long time) {
        return LOG_SRARCH_LKEY + ":" + time;

    }

    public static String getStatisticsLogKey(long time) {
        return LOG_Statistics_LKEY + ":" + time;

    }

    public static String getQrcodeProductKey(String productId) {
        return QRCODE_PRODUCT_KEY + ":" + productId;

    }

    /**
     * 获取佣金比例缓存
     *
     * @return
     */
    public static String getRateConfigKey() {

        return RATE_CONFIG;
    }

    /**
     * 保存formId
     *
     * @param openId
     * @return
     */
    public static String getFormIdKey(String openId) {

        return REDIS_FORM_KEY + ":" + openId;
    }

    /**
     * 统一key
     *
     * @param id
     * @return
     */
    public static String getQrCode() {

        return QRCODE_SHOW_KEY;
    }

}
