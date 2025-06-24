package org.Astatine.r10.command.Company.UserCommand;

import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WithdrawCompany extends CommandRegister {

    public WithdrawCompany() {
        super(GlobalCommandHandler.WITHDRAW_COMPANY);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String[] args) {

        return true;
    }
}
