package org.ashe.kappa.temp;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

@Slf4j
public class Study {

    public static void main(String[] args) {
//        // 实例化
//        IntBuffer intBuffer = IntBuffer.allocate(6);
//        // 写
//        intBuffer.put(0, 9527)
//                .put(1)
//                .put(1)
//                .put(777);
//        log.info(Arrays.toString(intBuffer.array()));
        // 读
        IntBuffer intBuffer = IntBuffer.wrap(new int[]{9527, 1, 2, 777});
        log.info(String.valueOf(intBuffer.get()));
        log.info(String.valueOf(intBuffer.get()));
        log.info(String.valueOf(intBuffer.get()));
        log.info(String.valueOf(intBuffer.get()));

        // 文件处理器
    }

}
