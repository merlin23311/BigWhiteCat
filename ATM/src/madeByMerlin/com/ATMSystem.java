package madeByMerlin.com;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Create By Merlin on 2021.1.24；
 **/
public class ATMSystem {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        showMain(accounts);
    }

    /**
     * 主页
     * @param accounts
     */
    public static void showMain(ArrayList<Account> accounts){
        while (true) {
            System.out.println("========欢迎进入苏维埃中央人民银行自助服务中心=========");
                System.out.println("同志，请输入您想做的操作：");
                System.out.println("1.登录");
                System.out.println("2.开户");
                System.out.println("请输入您的选择：");
                int command = sc.nextInt();
                switch (command){
                    case 1:
                        login(accounts);
                        break;
                    case 2:
                        register(accounts);
                        break;
                    default:
                        System.out.println("哦，我亲爱的同志，如果您执意输入非选择信息，我很难保证克格勃的同志不会将您送入西伯利亚建设社会主义");
                }
        }
    }

    /**
     *登录
     * @param accounts
     */
    private static void login(ArrayList<Account> accounts) {
        if (accounts.size() == 0) {
            System.out.println("苏维埃中央人民银行中心系统中无任何账户信息，请您注册登录信息！");
            return;
        }
        while (true) {
            System.out.println("同志，请您输入登录的卡号：");
            String carId = sc.next();
            Account acc = getAccountByCardId(carId,accounts);
            if (acc != null) {
                while (true) {
                    System.out.println("同志，请您输入登录的密码：");
                    String password = sc.next();
                    if (acc.getPassWord().equals(password)) {
                        System.out.println(acc.getUserName()+"同志，您已经成功进入苏维埃中央人民银行系统中心，您的卡号是："+acc.getCarId());
                        System.out.println("这里是卢比扬科克格勃伊万诺夫同志为您提供服务，祝您生活愉快！");
                        showUserCommand(acc,accounts);
                        return;//结束登录方法
                    }else{
                        System.out.println("同志，您的密码有误！");
                    }
                }
            }else {
                System.out.println("同志，您输入的卡号不存在于苏维埃中央人民银行中，请您检查自己的账号密码是否输入正确");
            }
        }
    }

    /**
     * 主操作面板
     */
    private static void showUserCommand(Account acc,ArrayList<Account> accounts) {
        while (true) {
            System.out.println("===========苏维埃中央人民银行用户操作界面=============");
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出");
            System.out.println("7.注销账户");
            System.out.println("请输入您的选择：");
            int command = sc.nextInt();
            switch (command){
                    case 1:
                        showAccount(acc);
                        break;
                    case 2:
                        depositMoney(acc);
                        break;
                    case 3:
                        drawMoney(acc);
                        break;
                    case 4:
                        transforMoney(accounts,acc);
                        break;
                    case 5:
                        updatePassword(acc);
                        return;//返回主页
                    case 6:
                        System.out.println("欢迎下次登录苏维埃中央人民银行！卢比扬科克格勃伊万诺夫同志祝您生活愉快！");
                        return;
                    case 7:
                        accounts.remove(acc);
                        System.out.println("销户成功！您账户的余额将直接转账给苏维埃人民福利中心，感谢您对世界无产阶级解放事业所作出的贡献！");
                        return;
                    default:
                        System.out.println("哦，我亲爱的同志，如果您执意输入非选择信息，我很难保证克格勃的同志不会将您送入西伯利亚建设社会主义");
                }
        }
    }

    private static void updatePassword(Account acc) {
        System.out.println("============修改密码===============");
        while (true) {
            System.out.println("同志，请您输入正确的密码：");
            String okPassword = sc.next();
            if (acc.getPassWord().equals(okPassword)){
                while (true) {
                    System.out.println("同志，请您输入新密码：");
                    String newPassword = sc.next();
                    System.out.println("同志，请您输入确认密码：");
                    String okNewPassword =sc.next();
                    if (newPassword.equals(okNewPassword)){
                        acc.setPassWord(newPassword);
                        System.out.println("同志，您的密码已经修改成功！请您返回苏维埃中央人民银行重新进行登录");
                        return;
                    }else {
                        System.out.println("同志，您两次输入的密码不一致");
                    }
                }
            }
            else {
                System.out.println("同志，您输入的密码不正确！");
            }
        }
    }

    /**
     * 转账
     * @param accounts
     * @param acc
     */
    private static void transforMoney(ArrayList<Account> accounts, Account acc) {
        if(accounts.size() < 2){
            System.out.println("同志，苏维埃中央人民银行系统中没有其他账户！您不可以进行转账！");
            return;
        }
        if (acc.getMoney() == 0){
            System.out.println("同志，您的余额不足以让您进行转账！");
            return;
        }
        while (true) {
            System.out.println("请您输入对方的卡号：");
            String carId = sc.next();
            Account account = getAccountByCardId(carId,accounts);
            if (account != null){
                if (account.getCarId().equals(acc.getCarId())){
                    System.out.println("同志，您不可以为自己转账！");
                }else {
                    String name = "*" + account.getUserName().substring(1);
                    System.out.print("请您确认"+name+"同志的姓氏:");
                    String preName = sc.next();
                    if (account.getUserName().startsWith(preName)){
                        System.out.println("请您输入转账的金额：");
                        double money = sc.nextDouble();
                        if (money >= acc.getMoney()){
                            System.out.println("对不起，您要转账的金额太多，您最多可以转账："+acc.getMoney());
                        }else {
                            acc.setMoney(acc.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            System.out.println("恭喜您，已经为"+acc.getUserName()+"同志转账成功！");
                            System.out.println("您的账户信息如下：");
                            showAccount(acc);
                            return;
                        }
                    }else {
                        System.out.println("您认证的信息有误！");
                    }
                }
            }else {
                System.out.println("您输入的转账卡号有问题！");
            }
        }
    }

    /**
     * 取钱
     * @param acc
     */
    private static void drawMoney(Account acc) {
        if (acc.getMoney() >= 100) {
            while (true) {
                System.out.println("同志，请您输入取款的金额：");
                double money = sc.nextDouble();
                if (money > acc.getQuotaMoney()){
                    System.out.println("您的取款金额超出当次限额，您的当次限额为："+acc.getQuotaMoney());
                }else {
                    if (acc.getMoney()>= money){
                        acc.setMoney(acc.getMoney()-money);
                        System.out.println("恭喜您！您成功取走"+money+"元，当前账户余额："+acc.getMoney());
                        return;//退出取钱页面
                    }else {
                        System.out.println("您的余额不足以提取如此数额的金钱！");
                    }
                }
            }
        }else {
            System.out.println("同志，您的余额不足100块，不可以进行取款操作！");
        }
    }

    /**
     * 存钱
     * @param acc
     */
    private static void depositMoney(Account acc) {
        System.out.println("====================存钱操作=======================");
        System.out.println("同志，请您输入存钱的金额：");
        double money = sc.nextDouble();
        acc.setMoney(acc.getMoney()+money);
        System.out.println("恭喜您，您成功存钱："+money+"元，您当前的账户余额："+acc.getMoney());
    }

    private static void showAccount(Account acc) {
        System.out.println("=======================当前账户详情=============================");
        System.out.println("卡号："+acc.getCarId());
        System.out.println("姓名："+acc.getUserName());
        System.out.println("余额："+acc.getMoney());
        System.out.println("当次限额："+acc.getQuotaMoney());
    }

    /**
     * 用户开户功能
     * @param accounts 账户集合对象
     */
    private static void register(ArrayList<Account> accounts) {
        System.out.println("=========用户开户功能===========");
        System.out.println("按 0 退出开户操作并返回主页 按 1 继续开户操作:");
        int accept = sc.nextInt();
        if (accept == 0){
            System.out.println("您已退出开户操作！");
            return;
        }
        System.out.println("同志，请您输入开户名：");
        String name = sc.next();
        String password = "";
        while (true) {
            System.out.println("同志，请输入您的密码：");
            password = sc.next();
            System.out.println("同志，请输入确认密码：");
            String okPassword = sc.next();
            if (okPassword.equals(password)) {
                 break;
            }else {
                System.out.println("同志，两次输入密码必须保持一致！！");
            }
        }
        System.out.println("同志，请您输入用户当此限额：");
        double quotaMoney = sc.nextDouble();
        String carId = creatCardId(accounts);
        Account account = new Account(carId,name,password,quotaMoney);
        accounts.add(account);
        System.out.println("恭喜您，"+account.getUserName()+"同志，您已经开户成功，您的卡号是： " +account.getCarId()+"请您牢记并且妥善管理您的开户账号");

    }

    /**
     * 生成随机卡号
     * @param accounts
     * @return 卡号
     */
    public static String creatCardId(ArrayList<Account> accounts){
        while (true) {
            String cardId = "";
            Random r = new Random();
            for (int i = 0; i < 8; i++) {
                cardId += r.nextInt(10);
            }
            Account acc = getAccountByCardId(cardId, accounts);
            if (acc == null){
                return cardId;
            }
        }
    }

    /**
     *查找有无相匹配的账户
     * @param cardId
     * @param accounts
     * @return
     */
    public static Account getAccountByCardId(String cardId ,ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            if (acc.getCarId().equals(cardId)){
                return acc;
            }
        }
        return null;
    }
}
