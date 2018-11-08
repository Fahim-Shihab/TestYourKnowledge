package debs;

public class Mediator implements IMediator {
    String userID;
    @Override
    public void setUser(String userID) {
        this.userID= userID;
    }

    @Override
    public String getUser() {
        return userID;
    }
}
