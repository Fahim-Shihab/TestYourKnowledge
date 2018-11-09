import java.util.ArrayList;

public class UserRepository {
    ArrayList<User> users= new ArrayList<>();
    int i;

    public UserRepository() {
        this.i = 0;
    }

    public Iterator getIterator(){
        return new UserIterator();
    }

    public void add(User u){
        users.add(u);
    }

    private class UserIterator implements Iterator{
        @Override
        public boolean hasNext() {
            return i<users.size();
        }

        @Override
        public Object next() {
            return users.get(i++);
        }
    }
}
