package org.Astatine.r10.command.Company.UserCommand;

import org.Astatine.r10.Data.Company.CompanyData.Company.Company;
import org.Astatine.r10.Data.Company.CompanyData.Company.CompanyAccessManager;
import org.Astatine.r10.Data.Company.CompanyData.Company.CompanyController;
import org.Astatine.r10.Data.Company.CompanyData.Enumeration.BusinessSize;
import org.Astatine.r10.Util.EssentialsUtil;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.UUID;

public class CloseCompany extends CommandRegister {

    public CloseCompany() {
        super(GlobalCommandHandler.CLOSE_COMPANY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        Player companyOwner = (Player) sender;
        CompanyController controller = new CompanyController();

        Company senderCompany = controller.getCompanyUseOwnerUUID(companyOwner.getUniqueId());

        controller.closeCompany(senderCompany);
        return true;
    }
}
