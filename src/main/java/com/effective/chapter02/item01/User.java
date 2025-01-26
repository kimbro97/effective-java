package com.effective.chapter02.item01;

public class User {

    public static void main(String[] args) {
        User user = new User("김브로", "일반회원", "1234");

        User adminUser = User.adminUser("김브로", "1234");
        User nomalUser = User.nomalUser("김브로", "1234");
    }
    
    private String name;
    private String role;
    private String password;

    public User(String name, String role, String password) {
        this.name = name;
        this.role = role;
        this.password = password;
    }

    private static User nomalUser(String name,String password) {
        return new User(name, "nomal", password);
    }

    private static User adminUser(String name,String password) {
        return new User(name, "admin", password);
    }
}
