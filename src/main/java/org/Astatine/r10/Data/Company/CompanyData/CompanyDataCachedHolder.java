package org.Astatine.r10.Data.Company.CompanyData;

import org.Astatine.r10.Data.Company.CompanyData.Value.Company;

import java.util.HashSet;

public class CompanyDataCachedHolder {
    private static class CompanyListHolder {
        private final static HashSet<Company> INSTANCE = new HashSet<>();
    }

    public static HashSet<Company> getCompanyListInstance() {
        return CompanyListHolder.INSTANCE;
    }
}
