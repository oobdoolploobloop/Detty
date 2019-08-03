package com.lofthouse;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DebtProfile dp = new DebtProfile();
        dp.addDebtEntry(new DebtEntry(0,1,100));
        dp.addDebtEntry(new DebtEntry(1,2,150));
        dp.addDebtEntry(new DebtEntry(2,3,200));
        dp.addDebtEntry(new DebtEntry(2,3,250));
        dp.addDebtEntry(new DebtEntry(2,0,100));


    }
}
