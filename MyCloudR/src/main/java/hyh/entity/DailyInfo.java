package hyh.entity;

public final class DailyInfo {
    private int newusers = 0;
    private int active = 0;
    private int newfiles = 0;
    private int newdownloads = 0;

    public int getNewusers() {
        return newusers;
    }

    public void setNewusers(int newusers) {
        this.newusers = newusers;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getNewfiles() {
        return newfiles;
    }

    public void setNewfiles(int newfiles) {
        this.newfiles = newfiles;
    }

    public int getNewdownloads() {
        return newdownloads;
    }

    public void setNewdownloads(int newdownloads) {
        this.newdownloads = newdownloads;
    }
}
