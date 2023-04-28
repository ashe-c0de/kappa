package org.ashe.kappa.infra;

public class RedisKey {

    private RedisKey(){}

    /**
     * 项目名+类名+业务名+业务值
     */
    private static final String ORIGIN_KEY = "kappa:redis_key";
    /**
     * 验证码
     */
    public static final String VERIFY_CODE = "verify_code";
    /**
     * IP请求次数
     */
    public static final String REQUEST_COUNT = "ip_request_count";


    /**
     * 获取redis_key
     * @param business      业务名
     * @param value         业务值
     */
    public static String getRedisKey(String business, String value) {
        // 特殊处理本机IP存于redis中的key值
        if ("0:0:0:0:0:0:0:1".equals(value)) {
            value = "localhost";
        }
        return String.format("%s:%s:%s", ORIGIN_KEY, business, value);
    }

}
