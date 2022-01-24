package madeByMerlin.com;

/**
 * Create By Merlin on date.
 **/
public class Account {
    private String carId;//卡号
    private String userName;//客户名称
    private String passWord;//密码
    private double money;//余额
    private double quotaMoney;//当次取现额度

    public Account() {
    }

    public Account(String carId, String userName, String passWord,double quotaMoney) {
        this.carId = carId;
        this.userName = userName;
        this.passWord = passWord;
        this.quotaMoney = quotaMoney;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }
}
