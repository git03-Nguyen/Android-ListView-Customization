package team08.project.listviewcustomization;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private String name;
    private String phoneNumber;
    private int avatar;

    public static ArrayList<User> getRandomUsers(int numberOfUsers) {
        ArrayList<User> userList = new ArrayList<>();
        if (numberOfUsers > 0) {
            Random rng = new Random();
            String surname = "Nguyễn Văn ";
            String phone = "0989383944";
            int[] avatars = {   R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
                                R.drawable.icon4, R.drawable.icon5, R.drawable.icon6,
                                R.drawable.icon7, R.drawable.icon8 };

            for (int i = 0; i < numberOfUsers; i++) {
                String lastname = generateLastName(i);
                String name = surname + lastname;
                int avatarIndex = rng.nextInt(avatars.length);
                int randomAvatar = avatars[avatarIndex];
                userList.add(new User(name, phone, randomAvatar));
            }

        }
        return userList;
    }

    private static String generateLastName(int i) {
        return i < 0 ? "" : generateLastName((i / 26) - 1) + (char)(65 + i % 26);
    }

    public User(String name, String phoneNumber, int avatar) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public int getAvatar() {
        return avatar;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
