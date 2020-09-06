package lk.spark.sample.service;

import lk.spark.sample.dao.User;
import lk.spark.sample.repository.UserRepo;

public class UserServiceImpl implements UserService{
    @Override
    public String registerUser(User user) {
        UserRepo userRepo = new UserRepo();
        String result = userRepo.userRegister(user);
        return result;
    }

    @Override
    public String loginUser(String userName, String password) {
        UserRepo userRepo = new UserRepo();
        String name = userRepo.userLogin(userName, password);
        return name;
    }
}
