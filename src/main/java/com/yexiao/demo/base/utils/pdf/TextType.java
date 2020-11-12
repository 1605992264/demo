package com.yexiao.demo.base.utils.pdf;

/**
 * @author xuhf
 * @date 2020/11/11 15:52
 **/
public enum TextType {

    bigTitle(24f,true,false),
    commonTitle(20f,true,false),
    smallTitle(16f,true,false),
    bigText(20f,false,false),
    commonText(16f,false,false),
    smallText(12f,false,false);

    private float fontSize;
    private boolean isBold;
    private boolean isUnderscore;

    TextType(float fontSize, boolean isBold, boolean isUnderscore){
        this.fontSize = fontSize;
        this.isBold = isBold;
        this.isUnderscore = isUnderscore;
    }

    public float getFontSize() {
        return fontSize;
    }

    public boolean isBold() {
        return isBold;
    }

    public boolean isUnderscore() {
        return isUnderscore;
    }
}
