package org.ashe.kappa;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.ashe.kappa.infra.RsaEncryptor2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class KappaApplicationTests {

	@Resource
	private RsaEncryptor2 rsaEncryptor2;

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> {
			String encryptedMessage = rsaEncryptor2.encrypt("ss123456");
			log.info(encryptedMessage);
			String decrypt = rsaEncryptor2.decrypt(encryptedMessage);
			log.info(decrypt);
		});
	}

}
