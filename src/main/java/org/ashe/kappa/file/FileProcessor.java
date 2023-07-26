package org.ashe.kappa.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileProcessor {


    /**
     * 拆分PDF
     */
    public void splitPdf(String sourcePath) {
        Path path = Paths.get(sourcePath);
        try {
            if (!Files.exists(path) || Files.size(path) == 0) {
                log.error("路径: {} 不存在文件", sourcePath);
                return;
            }
            // 根据页数拆分PDF文件
            try (PDDocument document = Loader.loadPDF(new File(sourcePath))) {
                // 检查文件类型
                if (document.getNumberOfPages() == 0) {
                    log.error("不是有效的 PDF 文件");
                    return;
                }
                int i = 0;
                for (PDPage page : document.getPages()) {
                    try (PDDocument pdDocument = new PDDocument()) {
                        pdDocument.addPage(page);
                        pdDocument.save(getFilename(i));
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            log.error("拆分PDF文件失败, path = {}", sourcePath, e);
        }
    }

    private String getFilename(int i) {
        return String.format("C:\\Users\\Administrator\\Downloads\\_%s.pdf", i);
    }

}
