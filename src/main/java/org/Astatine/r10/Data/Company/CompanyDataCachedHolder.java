package org.Astatine.r10.Data.Company;

import java.util.HashSet;

import org.Astatine.r10.Data.Company.CompanyData.Company.Company;

public class CompanyDataCachedHolder {
    private static class CompanyListHolder {
        private final static HashSet<Company> INSTANCE = new HashSet<>();
    }

    public static HashSet<Company> getCompanyListInstance() {
        return CompanyListHolder.INSTANCE;
    }
    
}
