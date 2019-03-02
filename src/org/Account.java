package org;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;

public class Account {
    private BigDecimal balance;

    //map containing quantity of ech nominal, will be updated during deposit and withdraw
    private Map<BigDecimal, Integer> nominalsQuantity = new LinkedHashMap<>();

    public Account(BigDecimal balance) {
        this.balance = balance;

        //initializing map with nominals quantity
        for (BigDecimal nominal : NOMINALS) {
            nominalsQuantity.put(nominal, 0);
        }
    }

    private static final BigDecimal[] NOMINALS = {
            BigDecimal.valueOf(500.0),
            BigDecimal.valueOf(200.0),
            BigDecimal.valueOf(100.0),
            BigDecimal.valueOf(50.0),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(10.0),
            BigDecimal.valueOf(5.0),
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1.0),
            BigDecimal.valueOf(0.50),
            BigDecimal.valueOf(0.20),
            BigDecimal.valueOf(0.10),
            BigDecimal.valueOf(0.05),
            BigDecimal.valueOf(0.02),
            BigDecimal.valueOf(0.01)
    };

    //show quantity of certain nominal
    private Integer getNominalsQuantity(BigDecimal nominal){
        return nominalsQuantity.get(nominal);
    }

    //update quantity of certain nominal, when subtracting Integer will be negative
    private void setNominalsQuantity(BigDecimal nominal, Integer quantity){
        nominalsQuantity.put(nominal, nominalsQuantity.get(nominal)+quantity);
    }

    protected BigDecimal getBalance() {
        return balance;
    }

    protected void getAllNominalsQuantity(){
        System.out.println("Available nominal:");
        for (Map.Entry<BigDecimal, Integer> entry :nominalsQuantity.entrySet()){
            StringBuilder msg = new StringBuilder();
            msg.append(entry.getValue());
            msg.append(" x ");
            msg.append(entry.getKey());
            System.out.println(msg);
        }
    }

    protected void deposit(BigDecimal nominal, int quantity) {
        balance = balance.add(nominal.multiply(BigDecimal.valueOf((double) quantity)));
        setNominalsQuantity(nominal, quantity);
    }

    protected void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) == -1 || amount.compareTo(balance) == 0) {
            balance = balance.subtract(amount);
            System.out.println(value(amount));
        } else {
            System.out.println("your too poor!");
        }
    }

    public boolean isNominal(BigDecimal amount) {
        for (BigDecimal nominal : NOMINALS) {
            if (amount.compareTo(nominal) == 0) {
                return true;
            }
        }
        return false;
    }

    private static String value(BigDecimal value) {
        String ret = "Amount " + value + "\n";
        for (BigDecimal nominal : NOMINALS) {
            if (value.compareTo(nominal) >= 0) {
                BigDecimal a = value.divide(nominal, 0, RoundingMode.FLOOR);
                value = value.subtract(nominal.multiply(a));
                ret += "" + a + " x " + nominal + "\n";
            }
        }
        return ret;
    }
}
