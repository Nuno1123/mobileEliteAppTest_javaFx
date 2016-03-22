package nuno.monteiro.services;

import nuno.monteiro.models.User;

/**
 * Created by codecadet on 15/03/16.
 */
public interface UserService {


    boolean authenticate(String username, String password);

    void addUser(User user);

    User findName(String username);

    int count();


}
