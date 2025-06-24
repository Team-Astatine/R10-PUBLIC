package org.Astatine.r10.command.UserCommand.Function;

import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.Emoji;
import org.Astatine.r10.command.CommandRegister;
import org.Astatine.r10.command.GlobalCommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class Moderator extends CommandRegister {
    private final Set<UUID> moderatorName;

    public Moderator() {
        super(GlobalCommandHandler.MODERATOR);
        this.moderatorName = new HashSet<>();
        moderList();
    }

    private void moderList() {
        UUID jaxple = UUID.fromString("27d84b4f-5991-4001-89d5-0fdfd3374a3d");
        UUID kelriex = UUID.fromString("7e57dd28-bdba-4312-84ea-2da58cd6e598");
        UUID gunbunjule = UUID.fromString("581a57af-91c9-4cf1-a173-85a2b48b68a7");

        this.moderatorName.add(jaxple);
        this.moderatorName.add(kelriex);
        this.moderatorName.add(gunbunjule);
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender,
                             final @NotNull Command command,
                             final @NotNull String label,
                             final @NotNull String [] args) {

        Optional.of((Player) sender).ifPresent(
                player -> {
                    if (checkUPModerator(player)) {
                        String successComment = "지금부터 관리자 입니다.";
                        playerSendMsgComponentExchanger(player, successComment, ColorType.ORANGE);
                        player.setOp(true);
                    } else {
                        String failComment = "해당 명령어의 사용권한이 없습니다.";
                        player.sendMessage(
                            waringMessage(failComment)
                        );
                    }
                }
        );
        return true;
    }

    private boolean checkUPModerator(@NotNull Player sendPlayer) {
        UUID senderUUID = sendPlayer.getUniqueId(); //String 대쉬 표기
        return this.moderatorName.stream().anyMatch(senderUUID::equals);
    }
}