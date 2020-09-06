package lk.spark.sample.service;

import lk.spark.sample.dao.User;

public interface UserService {
    public String registerUser(User user);

    public String loginUser(String userName, String Password);
}
