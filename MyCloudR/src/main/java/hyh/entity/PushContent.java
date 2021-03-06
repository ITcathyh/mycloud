package hyh.entity;

public class PushContent {
    private String content;
    private String href;

    public PushContent(){
        content = null;
        href = null;
    }

    public PushContent(String content, String href) {
        this.content = content;
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
