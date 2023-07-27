package org.ashe.kappa.infra;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * RSA算法    非对称加密解密
 * 动态公钥私钥
 */
@Slf4j
public class RsaEncryptor {

    private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    public static KeyPair generateKeyPair() {
        try {
            // 使用RSA算法
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 加密
     *
     * @param message   明文
     * @param publicKey 公钥
     * @return 加密后的密文
     */
    public static String encrypt(String message, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 解密
     *
     * @param encryptedMessage 密文
     * @param privateKey       私钥
     * @return 解密后的明文
     */
    public static String decrypt(String encryptedMessage, PrivateKey privateKey) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public static void main(String[] args) {
        // 生成密钥对
        KeyPair keyPair = RsaEncryptor.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // 明文
        String plaintext = "hell0 world";
        // 加密
        String ciphertext = RsaEncryptor.encrypt(plaintext, publicKey);
        log.info("密文--> " + ciphertext);
        // 解密
        String decryptedMessage = RsaEncryptor.decrypt(ciphertext, privateKey);
        log.info("原文--> " + decryptedMessage);
        // 将公钥和私钥编码为 Base64 字符串并输出
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        log.info("公钥Base64字符串--> " + publicKeyString);
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        log.info("私钥Base64字符串--> " + privateKeyString);
    }

}