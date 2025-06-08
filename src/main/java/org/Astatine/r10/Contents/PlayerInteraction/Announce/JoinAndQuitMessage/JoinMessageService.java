package org.Astatine.r10.Contents.PlayerInteraction.Announce.JoinAndQuitMessage;

import org.Astatine.r10.Contents.EventRegister;
import org.Astatine.r10.Contents.PlayerInteraction.Announce.Tip.Announcer;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatus;
import org.Astatine.r10.Data.User.UserKillStatus.UserKillStatusHandler;
import org.Astatine.r10.Enumeration.Type.ColorType;
import org.Astatine.r10.Util.Function.StringComponentExchanger;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import net.kyori.adventure.title.Title;

public class JoinMessageService extends StringComponentExchanger implements EventRegister {
    private Player joinPlayer;
    private UserKillStatus userKillStatus;
    private Title title;
    private String joinMsg;

    private final PlayerJoinEvent event;

    public JoinMessageService(PlayerJoinEvent event) {
        this.event = event;
        init();
        execute();
    }

    @Override
    public void init() {
        this.joinPlayer = this.event.getPlayer();
        this.userKillStatus = new UserKillStatusHandler().readUser(this.joinPlayer.getUniqueId());
        
        this.title = Title.title(
                componentExchanger("최신버전 무정부 플라이 반야생", ColorType.PURPLE),
                componentExchanger("Shift + F 를 눌러 메뉴를 열 수 있습니다.", ColorType.ORANGE)
        );

        int kills = userKillStatus.killCount();
        this.joinMsg = (kills == 0)
                ? " + " + this.joinPlayer.getName()
                : String.format(" + [ %dKILL ] %s", kills, this.joinPlayer.getName());
    }

    @Override
    public void execute() {
        this.joinPlayer.showTitle(this.title);
        this.joinPlayer.performCommand("help");
        Announcer.getAnnouncer().joinPlayerInformationAnnouncer(this.joinPlayer);
        this.event.joinMessage(
                componentExchanger(joinMsg, ColorType.RED)
        );
    }
}