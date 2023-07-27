package org.ashe.kappa.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class FileProcessor {

    /**
     * 拆分PDF
     */
    public void splitPdf(String sourcePath) {
        try (PDDocument document = Loader.loadPDF(new File(sourcePath))) {
            if (document.getNumberOfPages() == 0) {
                log.error("不是有效的 PDF 文件");
                return;
            }
            splitDocument(document);
        } catch (IOException e) {
            log.error("拆分PDF文件失败, path = {}", sourcePath, e);
        }
    }

    private void splitDocument(PDDocument document) throws IOException {
        int i = 0;
        for (PDPage page : document.getPages()) {
            try (PDDocument pdDocument = new PDDocument()) {
                pdDocument.addPage(page);
                pdDocument.save(getFilename(i));
            }
            i++;
        }
    }

    private String getFilename(int i) {
        return String.format("C:\\Users\\Administrator\\Downloads\\_%s.pdf", i);
    }

}

