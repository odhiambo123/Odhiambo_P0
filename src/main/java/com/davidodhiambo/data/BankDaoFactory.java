package com.davidodhiambo.data;

import com.davidodhiambo.service.BankDaoImpl;

public class BankDaoFactory {
    private static BankDao bankDao;

    private BankDaoFactory() {
    }

    public static BankDao getAccounts() {
        if(bankDao == null) {
            bankDao = new BankDaoImpl();
        }
        return bankDao;
    }
}
