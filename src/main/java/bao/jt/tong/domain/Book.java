package bao.jt.tong.domain;

import java.math.BigDecimal;


public class Book {
    private Integer num;
    private String name;
    private BigDecimal money;
    private String src;

    public Book(Integer num, String name, BigDecimal money, String src) {
        this.num = num;
        this.name = name;
        this.money = money;
        this.src = src;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "Book{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
