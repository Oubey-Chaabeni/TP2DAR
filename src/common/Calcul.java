package common;

import java.io.Serializable;

public class Calcul implements Serializable {

    
    private int num1;
    private char operator;
    private int num2;

    public Calcul(int num1, char operator, int num2) {
        this.num1 = num1;
        this.operator = operator;
        this.num2 = num2;
    }

    public int getNum1() {
        return num1;
    }

    public char getOperator() {
        return operator;
    }

    public int getNum2() {
        return num2;
    }
}