package ua.com.training.service;

import ua.com.training.dao.entity.User;

import java.util.List;

public interface Service {

    boolean existUserByRoleAndLogin(String role, String login, String password);

    List<User> findAllCashiersLogin();

    List<User> findAllCommodityExpertsLogin();
}