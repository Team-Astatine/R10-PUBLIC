package org.Astatine.r10.Data.Company.CompanyData.Enumeration;

import java.math.BigDecimal;

public enum BusinessSize {
    STARTUP("스타트업", BigDecimal.valueOf(1000000)),
    SMALL("중소기업", BigDecimal.valueOf(5000000)),
    SMALL_BUSINESS("강소기업", BigDecimal.valueOf(10000000)),
    MID_MARET("중견기업", BigDecimal.valueOf(50000000)),
    ENTERPRISE("대기업", BigDecimal.valueOf(100000000)),
    GLOBAL_ENTERPRISE("글로벌 기업", BigDecimal.valueOf(1000000000));

    private final String displayName;
    private final BigDecimal minimumCapital;

    BusinessSize(String displayName, BigDecimal minimumCapital) {
        this.displayName = displayName;
        this.minimumCapital = minimumCapital;
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getMinimumCapital() {
        return minimumCapital;
    }
}
