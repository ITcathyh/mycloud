package hyh.entity;

public class Invitationcode {
    private long id;

    private String invitationcode;

    private short surplus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInvitationcode() {
        return invitationcode;
    }

    public void setInvitationcode(String invitationcode) {
        this.invitationcode = invitationcode;
    }

    public short getSurplus() {
        return surplus;
    }

    public void setSurplus(short surplus) {
        this.surplus = surplus;
    }

    public static void main(String args[]){
        Invitationcode i = new Invitationcode();

        i.setSurplus((byte)1245);

        System.out.println(i.getSurplus() == 1245);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invitationcode that = (Invitationcode) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}