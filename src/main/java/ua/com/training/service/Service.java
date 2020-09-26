package ua.com.training.service;

public interface Service {

    boolean existUserByRoleAndLogin(String role, String login, String password);
}