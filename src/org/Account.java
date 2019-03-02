package org;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
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

    public BigDecimal getBalance() {
        return balance;
    }

    protected void deposit(BigDecimal nominal, int quantity) {
        balance = balance.add(nominal.multiply(BigDecimal.valueOf((double) quantity)));
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
            if (value.compareTo(nominal) == 1) {
                BigDecimal a = value.divide(nominal, 0, RoundingMode.FLOOR);
                value = value.subtract(nominal.multiply(a));
                ret += "" + a + " x " + nominal + "\n";
            } else if (value.compareTo(nominal) == 0) {
                value = value.subtract(nominal);
                ret += "" + 1 + " x " + nominal + "\n";
            }
        }
        return ret;
    }
}
