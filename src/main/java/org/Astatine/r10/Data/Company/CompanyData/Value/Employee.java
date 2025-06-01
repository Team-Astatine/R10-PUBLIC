package org.Astatine.r10.Data.Company.CompanyData.Value;

import org.Astatine.r10.Data.Company.CompanyData.Enumeration.Position;

import java.util.Date;
import java.util.UUID;

public record Employee(
    UUID employeeUUID,
    UUID affiliatedCompanyUUID,
    Date dateOfJoining,
    Position position
) {}
