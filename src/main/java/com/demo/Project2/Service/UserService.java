package com.demo.Project2.Service;

import com.demo.Project2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private ArrayList<User> users = new ArrayList();

    public ArrayList<User> getUsers() {
        return users;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean updateUser(Integer index, User user) {
        if (index >= users.size() || index < 0) {
            return false;
        }
        users.set(index, user);
        return true;
    }

    public boolean removeUser(String id) {
        users.remove(id);
        return true;
    }

    public User getUser(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID().equals(id)) {
                return users.get(i);
            }
        }
        return null;
    }

}
