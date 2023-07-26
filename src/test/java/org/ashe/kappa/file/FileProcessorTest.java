package org.ashe.kappa.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Slf4j
class FileProcessorTest {

    @Autowired
    private FileProcessor fileProcessor;


    @Test
    void splitPdf() {
        Assertions.assertDoesNotThrow(() -> fileProcessor.splitPdf("C:\\Users\\Administrator\\Downloads\\1021WS03-5.pdf"));
    }

}