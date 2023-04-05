package org.ashe.kappa.infra;

import lombok.Getter;

@SuppressWarnings("unused")
@Getter
public class Response {

    private final boolean result;

    private final String msg;

    private final Object data;


    private Response(boolean result, String msg, Object data){
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功响应
     */
    public static Response ok() {
       return new Response(true,null, null);
    }

    /**
     * 成功响应 & 附加响应数据
     * @param data 返回数据
     */
    public static Response ok(Object data) {
       return new Response(true,null, data);
    }

    /**
     * 失败响应
     * @param msg 错误提示
     */
    public static Response no(String msg) {
        return new Response(false, msg, null);
    }

}
