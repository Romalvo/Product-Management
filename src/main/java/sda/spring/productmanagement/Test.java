package sda.spring.productmanagement;


import org.springframework.stereotype.Component;

@Component()
public class Test {

    private final int number;

    public Test() {
        // 0- 100
        this.number = (int) (Math.random() * 100);
    }

    public void printRandomNumber() {
        System.out.println("The random number is: " + number);
    }
}
