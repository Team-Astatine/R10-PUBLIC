package org.Astatine.r10.Data.Company.CompanyData.Enumeration;

public enum BusinessSize {
    STARTUP("스타트업"),
    SMALL("중소기업"),
    SMALL_BUSINESS("강소기업"),
    MID_MARET("중견기업"),
    ENTERPRISE("대기업"),
    GLOBAL_ENTERPRISE("글로벌 기업");

    private final String displayName;
    BusinessSize(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
