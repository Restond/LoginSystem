package src.main.java.com.cupcakes;

import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

public class UserManager {
    private final HashMap<String, String> users = new HashMap<>();
    private static final String USER_DATA_FILE = "src/main/resources/user_data.txt";
    
    public UserManager() {
        loadUserData();
    }
    
    public void registerUser(Scanner scanner) {
        System.out.print("请输入用户名: ");
        String userName = scanner.nextLine();
        
        if (users.containsKey(userName)) {
            System.out.println("用户名已存在，请使用其他用户名");
            return;
        }
        
        System.out.print("请输入密码： ");
        String password = scanner.nextLine();
        
        users.put(userName, password);
        saveUserData();
        System.out.println("注册成功！");
    }
    
    public boolean loginUser(Scanner scanner) {
        System.out.print("请输入用户名: ");
        String userName = scanner.nextLine();
        System.out.print("请输入密码： ");
        String password = scanner.nextLine();
        
        return users.containsKey(userName) && users.get(userName).equals(password);
    }
    
    public void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
            System.out.println("用户数据加载成功。");
        } catch (IOException e) {
            System.out.println("用户数据文件不存在，将创建新文件。");
        }
    }
    
    public void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (String username : users.keySet()) {
                writer.write(username + ":" + users.get(username));
                writer.newLine();
            }
            System.out.println("用户数据保存成功。");
        } catch (IOException e) {
            System.out.println("保存用户数据时出错: " + e.getMessage());
        }
    }
}
