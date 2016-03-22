package nuno.monteiro.services;

import nuno.monteiro.models.User;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 15/03/16.
 */
public class MockUserService implements UserService {

    private List<User> users = new LinkedList<>();



    @Override
    public boolean authenticate(String username, String password) {

        return findName(username) != null && findName(username).getPassword().equals(password);
    }

    @Override
    public void addUser(User user) {

        if(!(findName(user.getUsername()) == null)){
            return;
        }

        users.add(user);

    }


    @Override
    public User findName(String username) {

        Iterator<User> userIterator = users.iterator();
        User user;

        // iterate user List
        while (userIterator.hasNext()){
            // the user we want to find is the user that the iterator is at
            user = userIterator.next();

            // if the user we want to find has the same username as the username we are finding...
            if (user.getUsername().equals(username)){

                // return the user we want to find
                return user;
            }
        }
        return null;
    }

    @Override
    public int count() {

        return users.size();
    }
}
