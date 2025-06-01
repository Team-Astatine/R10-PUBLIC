package org.Astatine.r10.command;

import org.Astatine.r10.R10;
import org.Astatine.r10.Contents.UserInterface.Command.EnhanceTabOpen;
import org.Astatine.r10.Contents.UserInterface.Command.GSitTabOpen;
import org.Astatine.r10.Contents.UserInterface.Command.HomeTabOpen;
import org.Astatine.r10.Contents.UserInterface.Command.TpaTabOpen;
import org.Astatine.r10.Contents.UserInterface.Command.MainMenu.MainMenuTabCommandOpen;
import org.Astatine.r10.command.Company.UserCommand.CloseCompany;
import org.Astatine.r10.command.Company.UserCommand.CompanyProperty;
import org.Astatine.r10.command.Company.UserCommand.CreateCompany;
import org.Astatine.r10.command.Company.UserCommand.DemoteEmployee;
import org.Astatine.r10.command.Company.UserCommand.DepositCompany;
import org.Astatine.r10.command.Company.UserCommand.DismissEmployee;
import org.Astatine.r10.command.Company.UserCommand.PromoteEmployee;
import org.Astatine.r10.command.Company.UserCommand.RecruitEmployee;
import org.Astatine.r10.command.Company.UserCommand.SetSalary;
import org.Astatine.r10.command.Company.UserCommand.WithdrawCompany;
import org.Astatine.r10.command.ModeratorCommand.ExportAllData;
import org.Astatine.r10.command.ModeratorCommand.GetUserMainHandItem;
import org.Astatine.r10.command.ModeratorCommand.GetUserValue;
import org.Astatine.r10.command.ModeratorCommand.RegistBindItem;
import org.Astatine.r10.command.ModeratorCommand.ReloadConfigData;
import org.Astatine.r10.command.ModeratorCommand.RemoveDuplicateUserNameData;
import org.Astatine.r10.command.ModeratorCommand.SetEnhance;
import org.Astatine.r10.command.ModeratorCommand.SetGodMode;
import org.Astatine.r10.command.ModeratorCommand.SetHealth;
import org.Astatine.r10.command.ModeratorCommand.SetKillCount;
import org.Astatine.r10.command.ModeratorCommand.SetMotd;
import org.Astatine.r10.command.UserCommand.Announce.CommandTip;
import org.Astatine.r10.command.UserCommand.Announce.Community;
import org.Astatine.r10.command.UserCommand.Announce.Help;
import org.Astatine.r10.command.UserCommand.Announce.PlayTime;
import org.Astatine.r10.command.UserCommand.Announce.ServerTip;
import org.Astatine.r10.command.UserCommand.Announce.TitleAstatine;
import org.Astatine.r10.command.UserCommand.Announce.ToggleAnnouncing;
import org.Astatine.r10.command.UserCommand.Function.Hat;
import org.Astatine.r10.command.UserCommand.Function.Moderator;
import org.Astatine.r10.command.UserCommand.Function.OffHandItemSwapFunction;
import org.Astatine.r10.command.UserCommand.Function.ShowOffStuff;
import org.Astatine.r10.command.UserCommand.Function.StackingTotemFunction;
import org.Astatine.r10.command.UserCommand.Function.ToggleFly;
import org.bukkit.command.CommandExecutor;


/**
 * 명령어 추가 시 해당 Enumeration 에 추가합니다.
 * 관리자 명령어와, 유저 명령어를 구분 후 추가해야 하며, 각 명령어에 permission 설정을 통해 Tab Complete 에 제한을 둬야합니다.
 *
 * 명령어 추가방법
 * resources/plugin.yml 에 명령어 추가합니다.
 * @see GlobalCommandHandler 에 명령어 등록 및 명령 시 발생할 Instance 추가합니다.
 * @see CommandRegisterSection 을 extend하여 명령어 입력 시 execute 할 Instance를 추가합니다.
 *
 * @implSpec {@link R10} 에 registerCommandAndEvent() 함수에서 명령어를 일괄 등록합니다.
 */
public enum GlobalCommandHandler {
//    Company
    CREATE_COMPANY("회사설립", new CreateCompany()),
    CLOSE_COMPANY("회사폐업", new CloseCompany()),

    PROPERTY_COMPANY("소속회사조회", new CompanyProperty()),

    RECRUIT_EMPLOYEE("회사채용", new RecruitEmployee()),
    DISMISS_EMPLOYEE("회사해고", new DismissEmployee()),
    PROMOTE_EMPLOYEE("회사승진", new PromoteEmployee()),
    DEMOTE_EMPLOYEE("회사강등", new DemoteEmployee()),

    DEPOSIT_COMPANY("회사예치금입금", new DepositCompany()),
    WITHDRAW_COMPANY("회사자금출금", new WithdrawCompany()),

    SET_SALARY("회사급여설정", new SetSalary()),


//    User
//    info
    ASTN("Astn", new TitleAstatine()),
    HELP("Help", new Help()),
    PLAY_TIME("playtime", new PlayTime()),

//    Function
    MODERATOR("moderator", new Moderator()),
    SWAP_HAT_TO_HAND("hat", new Hat()),
    SWAP_HAND_TO_OFFHAND("swap", new OffHandItemSwapFunction()),
    TOTEM_STACKING("totem", new StackingTotemFunction()),
    SHOW_OFF_STUFF("자랑하기", new ShowOffStuff()),
    FLY("fly", new ToggleFly()),


//    UI
    MAIN_MENU_TAB_OPEN("mainMenuTabOpen", new MainMenuTabCommandOpen()),
    GSIT_TAB_OPEN("gsitTabOpen", new GSitTabOpen()),
    TPA_TAB_OPEN("tpaTabOpen", new TpaTabOpen()),
    HOME_TAB_OPEN("homeTabOpen", new HomeTabOpen()),
    ENHANCE_TAB_OPEN("강화", new EnhanceTabOpen()),

//    Tips
    ANNOUNCING("공지", new ToggleAnnouncing()),
    COMMAND_TIP("명령어", new CommandTip()),
    SERVER_TIP("서버팁", new ServerTip()),
    COMMUNITY("커뮤니티", new Community()),


//    Moderator
//    Data Export, Reload
    EXPORT_DATA_FILE("ExportDataFile", new ExportAllData()),
    CONFIG_RELOAD("configDataFileReload", new ReloadConfigData()),
    REMOVE_DUPLICATE_USER_DATA("RemoveDuplicateData", new RemoveDuplicateUserNameData()),

//    Check up Value
    LOOK_USER_VALUE("vo", new GetUserValue()),
    LOOK_USER_MAIN_HAND_ITEM("vi", new GetUserMainHandItem()),

//        Status set
    MOTD_SET("motd", new SetMotd()),
    GOD_MODE_SET("god", new SetGodMode()),
    HEALTH_SCALE_SET("setHealth", new SetHealth()),
    KILL_COUNT_SET("setKillCount", new SetKillCount()),
    
    // ITEMS STATUS
    ENHANCE_SET("enhance", new SetEnhance()),
    BINDING_ITEM("bindItem", new RegistBindItem());

    private final String command;
    private final CommandExecutor executor;

    GlobalCommandHandler(String command, CommandExecutor executor) {
        this.command = command;
        this.executor = executor;
    }

    public String getCommand() {
        return command;
    }

    public CommandExecutor getCommandInstance() {
        return executor;
    }

//    public TabCompleter newTabCompleterInstance() {
//        return executor;
//    }
}
