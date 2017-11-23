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
}