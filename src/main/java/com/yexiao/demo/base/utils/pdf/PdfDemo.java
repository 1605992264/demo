package com.yexiao.demo.base.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author xuhf
 * @date 2020/11/11 16:41
 **/
public class PdfDemo {


    public static void main(String[] args) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D://a.pdf"));
        document.open();
        document.add(PdfFontUtils.addText(TextType.bigTitle,"我是标题"));
        document.add(PdfFontUtils.addText(TextType.bigText,"我是大正文"));
        document.add(PdfFontUtils.addText(TextType.smallText,"我是小正文"));
        document.close();
        writer.close();

    }

}
