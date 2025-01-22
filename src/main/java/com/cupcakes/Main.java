package src.main.java.com.cupcakes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        CargoManager cargoManager = new CargoManager();
        
        System.out.println("---------------------");
        System.out.println("--- 快递运费计算程序 ---");
        System.out.println("---------------------");
        
        int LoginCounts = 1;
        
        do {
            System.out.println("---------------------");
            System.out.println("请选择操作: ");
            System.out.println("- 1. 注册");
            System.out.println("- 2. 登录");
            System.out.println("- 0. 退出程序");
            System.out.print("请输入选项: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    userManager.registerUser(scanner);
                    break;
                case 2:
                    if (userManager.loginUser(scanner)) {
                        System.out.println("登录成功！");
                        cargoManager.displayMenu(scanner);
                    } else {
                        System.out.println("登录失败，请检查用户名或密码。");
                    }
                    break;
                case 0:
                    System.out.println("退出程序。");
                    userManager.saveUserData();
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选项，请重新输入。");
            }
            LoginCounts++;
        } while (LoginCounts < 3);
    }
}
