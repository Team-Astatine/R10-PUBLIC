package org.Astatine.r10.Data.Company.CompanyData.Value;

import org.Astatine.r10.Data.Company.CompanyData.Enumeration.BusinessSize;
import org.Astatine.r10.Data.Interface.RObject;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * 회사 정보를 나타내는 객체입니다.
 *
 * @param companyUUID        회사의 고유 식별자입니다.
 * @param companyOwnerUUID   회사 소유주의 고유 식별자입니다.
 * @param capital            회사 자본금입니다.
 * @param businessSize       회사 규모입니다.
 * @param employees  회사 {@link Employee} 목록입니다.
 */
public record Company (
        UUID companyUUID,
        UUID companyOwnerUUID,
        String companyName,
        BigDecimal capital,
        BusinessSize businessSize,
        Set<Employee> employees
) implements RObject {}
