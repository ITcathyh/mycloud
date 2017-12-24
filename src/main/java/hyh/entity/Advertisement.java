package hyh.entity;

import java.sql.Timestamp;

public class Advertisement {
    private short id;
    private String imgpath;
    private String href;
    private Timestamp deadline;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "<th>" + href + "</th>\n" +
                "<th>" + deadline + "</th>\n";
    }
}
