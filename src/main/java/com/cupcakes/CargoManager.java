package src.main.java.com.cupcakes;

import java.util.Scanner;

public class CargoManager {
    
    private static final double[] INITIAL_WEIGHT_COST = {10, 12, 15, 18, 30};
    private static final double[] ADDITIONAL_WEIGHT_COST = {5, 8, 10, 12, 25};
    
    private double weight;
    
    public void displayMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("---------------------");
            System.out.println("- 1. 货物数据录入");
            System.out.println("- 2. 计算运费");
            System.out.println("- 3. 货物信息显示");
            System.out.println("- 0. 退出程序");
            System.out.print("请选择操作：");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    inputCargoData(scanner);
                    break;
                case 2:
                    calculateShippingCost();
                    break;
                case 3:
                    displayCargoInfo();
                    break;
                case 0:
                    System.out.println("退出程序。");
                    break;
                default:
                    System.out.println("无效选项，请重新输入。");
            }
        } while (choice != 0);
    }
    
    public void inputCargoData(Scanner scanner) {
        while (true) {
            System.out.print("请输入货物重量（kg，必须大于0）：");
            try {
                double inputWeight = scanner.nextDouble();
                if (inputWeight > 0) {
                    this.weight = inputWeight;
                    System.out.println("货物数据已成功录入。");
                    break;
                } else {
                    System.out.println("重量必须大于0，请重新输入。");
                }
            } catch (Exception e) {
                System.out.println("输入无效，请输入数字。");
                scanner.nextLine();
            }
        }
    }
    
    public void calculateShippingCost() {
        if (weight <= 0) {
            System.out.println("货物重量必须大于0。");
            return;
        }
        double cost = calculateCost(this.weight);
        System.out.println("运费计算完成，总运费为：" + cost + "元。");
    }
    
    public void displayCargoInfo() {
        System.out.println("货物信息显示：");
        System.out.println("货物重量：" + this.weight + " kg");
    }
    
    private double calculateCost(double weight) {
        int index = getWeightIndex(weight);
        double initialCost = INITIAL_WEIGHT_COST[index];
        double additionalCost = ADDITIONAL_WEIGHT_COST[index];
        
        if (weight <= 1) {
            return initialCost;
        } else {
            return initialCost + (Math.ceil(weight - 1) * additionalCost);
        }
    }
    
    private int getWeightIndex(double weight) {
        if (weight <= 5) return 0;
        else if (weight <= 10) return 1;
        else if (weight <= 20) return 2;
        else if (weight <= 30) return 3;
        else return 4;
    }
}
