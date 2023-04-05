package org.ashe.kappa.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截 & 响应提示返回前端
 */
@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler
    public ResponseEntity<String> doException(Exception e){
        // 记录日志
        // 通知运维
        // 通知开发
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
