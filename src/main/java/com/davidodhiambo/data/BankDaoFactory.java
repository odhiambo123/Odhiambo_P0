package com.davidodhiambo.data;

import com.davidodhiambo.service.EmployeeDaoImpl;

public class BankDaoFactory {
    private static BankDao iemployeeDao;

    private BankDaoFactory() {
    }

    public static BankDao getIEmployeeDao() {
        if(iemployeeDao == null) {
            iemployeeDao = new EmployeeDaoImpl();
        }
        return iemployeeDao;
    }
}
