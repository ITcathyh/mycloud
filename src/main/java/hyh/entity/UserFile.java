package hyh.entity;

public class UserFile {
    private long id;

    private long userid;

    private String filename;

    private String summary;

    private String path;

    private int downloads;

    private int size;

    private short type;

    private String tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFile userFile = (UserFile) o;

        return id == userFile.id && userid == userFile.userid;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userid ^ (userid >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", userid=" + userid +
                ", filename='" + filename + '\'' +
                ", summary='" + summary + '\'' +
                ", path='" + path + '\'' +
                ", downloads=" + downloads +
                ", size=" + size +
                ", type=" + type +
                ", tag='" + tag + '\'' +
                '}';
    }
}