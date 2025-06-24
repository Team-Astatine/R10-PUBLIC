package org.Astatine.r10.command;

import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.command.CommandExecutor;

/**
 * 명령어를 등록하기 위한 구현체 클래스 입니다.
 * @see GlobalCommandHandler 에 명령어 등록 후 해당 class를 extend 하여 구현하면 명령어 생성이 가능합니다.
 */
public abstract class CommandRegister extends StringComponentExchanger implements CommandExecutor {
    private final GlobalCommandHandler typeOfCommand;

    public CommandRegister(GlobalCommandHandler typeOfCommand) {
        this.typeOfCommand = typeOfCommand;
    }
}
