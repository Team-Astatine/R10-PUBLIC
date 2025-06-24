package org.Astatine.r10.Data.Company.CompanyData.Company;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.UUID;

public class CompanyController {
    private final CompanyAccessManager companyAccessManager = CompanyAccessManager.getInstance();

    public boolean createCompany(Company company) {
        return this.companyAccessManager.insert(company);
    }

    public Company getCompanyUseOwnerUUID(UUID ownerUUID) {
        return this.companyAccessManager.selectOwnerUUID(ownerUUID);
    }

    public Company getCompanyUseCompanyUUID(UUID companyUUID) {
        return this.companyAccessManager.selectCompanyUUID(companyUUID);
    }

    public boolean updateCompany(Company previous, Company continuation) {
        return this.companyAccessManager.update(previous, continuation);
    }

    public boolean closeCompany(Company company) {
        if (ObjectUtils.isEmpty(company))
            return false;

        return this.companyAccessManager.remove(company);
    }

    public void updateAllCompanies(ArrayList<Company> newCompanies) {
        this.companyAccessManager.clear();

        if (ObjectUtils.isEmpty(newCompanies))
            return;

        newCompanies.forEach(this::createCompany);
    }

    public ArrayList<Company> getAllCompanies() {
        return new ArrayList<>(this.companyAccessManager.getAllCompanyList());
    }
}
