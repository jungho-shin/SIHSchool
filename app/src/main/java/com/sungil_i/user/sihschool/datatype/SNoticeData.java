package com.sungil_i.user.sihschool.datatype;

/**
 * Created by taos9938 on 2016. 12. 14..
 */

public class SNoticeData {

    private String index;
    private String attachment;
    private String title;
    private String name;
    private String date;
    private String content;
    private SAttachFileData attachFile1;
    private SAttachFileData attachFile2;
    private int hit;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SAttachFileData getAttachFile1() {
        return attachFile1;
    }

    public void setAttachFile1(SAttachFileData attachFile1) {
        this.attachFile1 = attachFile1;
    }

    public SAttachFileData getAttachFile2() {
        return attachFile2;
    }

    public void setAttachFile2(SAttachFileData attachFile2) {
        this.attachFile2 = attachFile2;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
