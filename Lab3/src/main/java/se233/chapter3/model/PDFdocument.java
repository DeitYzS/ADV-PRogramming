package se233.chapter3.model;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PDFdocument {
    private String name;
    private String filePath;
    private PDDocument document;
    private LinkedHashMap<String, ArrayList<FileFreq>> uniqueSets;
    public PDFdocument(String filePath) throws IOException{
        this.name = Paths.get(filePath).getFileName().toString();
        this.filePath = filePath;
        File pdfFile = new File(filePath);
        this.document = PDDocument.load(pdfFile);
    }
    public PDDocument getDoucument() { return  document;}
    public String getFilePath() { return  this.filePath; }
    public String getName() { return this.name; }
}
