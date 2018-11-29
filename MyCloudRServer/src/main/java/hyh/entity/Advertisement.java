package hyh.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;
    private short id;
    private String imgpath;
    private String href;
    private long deadline;

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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "<th>" + href + "</th>\n" +
                "<th>" + new Timestamp(deadline) + "</th>\n";
    }
}
