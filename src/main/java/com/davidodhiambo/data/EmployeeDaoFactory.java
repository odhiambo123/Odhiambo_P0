package com.davidodhiambo.data;

import com.davidodhiambo.service.EmployeeDaoImpl;

public class EmployeeDaoFactory {
    private static IEmployeeDao iemployeeDao;

    private EmployeeDaoFactory() {
    }

    public static IEmployeeDao getIEmployeeDao() {
        if(iemployeeDao == null) {
            iemployeeDao = new EmployeeDaoImpl();
        }
        return iemployeeDao;
    }
}
