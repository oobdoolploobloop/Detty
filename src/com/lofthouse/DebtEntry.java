package com.lofthouse;

public class DebtEntry {
    public int idOwe;
    public int idOwed;
    public int amt;

    public DebtEntry(int idOwe, int idOwed, int amt){
        this.idOwe = idOwe;
        this.idOwed = idOwed;
        this.amt = amt;
    }
}
