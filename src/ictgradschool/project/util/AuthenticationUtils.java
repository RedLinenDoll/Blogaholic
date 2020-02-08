package ictgradschool.project.util;

import ictgradschool.project.model.User;

import java.util.Random;

/* This class processes the username/password pairs provided by users (A),
and hashed password information saved in the database (B)
* */

public class AuthenticationUtils {
    private static Random RANDOM_GENERATOR = new Random();

    // saves A as B
    public static User createUser(String username, String password) {
        int randomSaltLength = RANDOM_GENERATOR.nextInt(20) + 1;
        int randomIterationNum = RANDOM_GENERATOR.nextInt(20) + 1;

        byte[] salt = new byte[randomSaltLength];
        RANDOM_GENERATOR.nextBytes(salt);

        String hashedSalt = PasswordUtil.base64Encode(salt);
        String hashedPassword = PasswordUtil.base64Encode(PasswordUtil.hash(password.toCharArray(), salt, randomIterationNum));


        return new User(username, hashedPassword, hashedSalt, randomSaltLength, randomIterationNum);
    }


    // matches A with B
    public static boolean authenticateUser(User user,String password){
        if (user == null) return false;

        return PasswordUtil.isExpectedPassword(
                password.toCharArray(),
                PasswordUtil.base64Decode(user.getSaltHashBase64()),
                user.getIterationNum(),
                PasswordUtil.base64Decode(user.getPasswordHashBase64())
                ) ;
    }


}
