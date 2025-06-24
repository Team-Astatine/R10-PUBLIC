package org.Astatine.r10.Data.Company.CompanyData.Company;

import java.util.HashSet;
import java.util.UUID;

public class CompanyAccessManager {
    private static final HashSet<Company> COMPANY_LIST = new HashSet<>();

    private static class CompanyListHolder {
        private final static CompanyAccessManager INSTANCE = new CompanyAccessManager();
    }

    public static CompanyAccessManager getInstance() {
        return CompanyListHolder.INSTANCE;
    }

    public synchronized HashSet<Company> getAllCompanyList() {
        return COMPANY_LIST;
    }

    public synchronized Boolean insert(Company company) {
        COMPANY_LIST.add(company);
        return Boolean.TRUE;
    }

    public synchronized Company selectOwnerUUID(UUID ownerUUID) {
        return COMPANY_LIST.stream()
                .filter(c -> c.companyOwnerUUID().equals(ownerUUID))
                .findFirst()
                .orElse(null);
    }

    public synchronized Company selectCompanyUUID(UUID companyUUID) {
        return COMPANY_LIST.stream()
                .filter(c -> c.companyUUID().equals(companyUUID))
                .findFirst()
                .orElse(null);
    }

    public synchronized Boolean update(Company previous, Company continuation) {
        COMPANY_LIST.remove(previous);
        COMPANY_LIST.add(continuation);
        return Boolean.TRUE;
    }

    public synchronized Boolean remove(Company company) {
        return COMPANY_LIST.remove(company);
    }

    public synchronized void clear() {
        COMPANY_LIST.clear();
    }
}
