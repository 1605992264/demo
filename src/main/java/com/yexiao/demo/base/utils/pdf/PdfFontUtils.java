package com.yexiao.demo.base.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xuhf
 * */
@Component
public class PdfFontUtils {


    private static BaseFont baseFont = null;
    private static final String fontPath = "C:\\Users\\ASUS\\AppData\\Local\\Microsoft\\Windows\\Fonts\\SIMPLE.ttf";

    /**
     * 初始化地址
     * */
    static {
        try {
            baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文档超级  排版
     * @param type
     */
    public static Paragraph addText(TextType type, String text) {
        Font font = new Font(baseFont);
        Paragraph paragraph = new Paragraph(text, font);
        font.setSize(type.getFontSize());
        switch (type){
            case bigTitle:
            case commonTitle:
            case smallTitle:
                // 居中
                paragraph.setAlignment(Paragraph.ALIGN_CENTER);
                paragraph.setSpacingBefore(7f);
                paragraph.setSpacingAfter(7f);
                if(type.isBold()) {
                    font.setStyle(Font.BOLD);
                }
                break;
            case bigText:
            case commonText:
            case smallText:
                // 首行缩进
                paragraph.setFirstLineIndent(24);
                paragraph.setSpacingBefore(1f);
                paragraph.setSpacingAfter(1f);
                if(type.isBold()) {
                    font.setStyle(Font.BOLD);
                }
                break;
                default:
        }
        return paragraph;
    }
}

