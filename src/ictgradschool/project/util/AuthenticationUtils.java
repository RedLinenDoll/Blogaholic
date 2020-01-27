package ictgradschool.project.util;

import ictgradschool.project.model.User;

import java.util.Random;

public class AuthenticationUtils {
    private static Random RANDOM_GENERATOR = new Random();

    public static User createUser(String username, String password) {
        int randomSaltLength = RANDOM_GENERATOR.nextInt(20) + 1;
        int randomIterationNum = RANDOM_GENERATOR.nextInt(20) + 1;

        byte[] salt = new byte[randomSaltLength];
        RANDOM_GENERATOR.nextBytes(salt);

        String hashedSalt = PasswordUtil.base64Encode(salt);
        String hashedPassword = PasswordUtil.base64Encode(PasswordUtil.hash(password.toCharArray(), salt, randomIterationNum));


        return new User(username, hashedPassword, hashedSalt, randomSaltLength, randomIterationNum);
    }


    // TODO authenticateUser, given a User object that contains authentication info
    //  (created from data queried from database with username) and a user input password, return
    //  a boolean indicating whether username and password match with each other.

}
