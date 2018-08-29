package com.shinybunny.mcfapi.scoreboard;

import com.shinybunny.mcfapi.ChatColor;
import com.shinybunny.mcfapi.MCObject;
import com.shinybunny.mcfapi.TextComponent;
import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.entity.EntityList;

public class Team extends MCObject {

    private final String name;
    private final TextComponent displayName;
    public final TeamOption<Boolean> seeFriendlyInvisibles;
    public final TeamOption<Boolean> friendlyFire;
    public final TeamOption<Show> nameTagVisibility;
    public final TeamOption<Show> deathMessageVisibility;
    public final TeamOption<Push> collisionRule;
    public final TeamOption<ChatColor> color;

    public Team(String name, TextComponent displayName) {
        this.name = name;
        this.displayName = displayName;
        this.friendlyFire = new TeamOption<>(this,"friendlyfire",true);
        this.seeFriendlyInvisibles = new TeamOption<>(this,"seeFriendlyInvisibles",true);
        this.nameTagVisibility = new TeamOption<>(this,"nametagVisibility",Show.ALWAYS);
        this.deathMessageVisibility = new TeamOption<>(this,"deathMessageVisibility",Show.ALWAYS);
        this.collisionRule = new TeamOption<>(this,"collisionRule",Push.ALWAYS);
        this.color = new TeamOption<>(this,"color", ChatColor.WHITE);
        create();
    }

    public Team(String name) {
        this(name,null);
    }

    @Override
    protected String getGetterSyntax(String property) {
        return "";
    }

    @Override
    protected String getCommand() {
        return "team";
    }

    @Override
    protected String getUpdateSyntax(String property, Object value) {
        return "option " + name + " " + property + " " + value.toString();
    }

    @Override
    protected String getCreationSyntax() {
        return "add " + name + (displayName == null ? "" : " " + displayName);
    }

    @Override
    protected String getDeletionSyntax() {
        return "remove " + name;
    }

    public void join(EntityList entities) {
        DatapackManager.writeCommand("team join " + this + " " + entities);
    }

    public void empty() {
        DatapackManager.writeCommand("team empty " + this);
    }

    @Override
    public String toString() {
        return name;
    }

    public <T> void updateOption(TeamOption<T> option) {
        updateProperty(option.getName(),option.get());
    }

    public enum Show {
        ALWAYS("always"), NEVER("never"), TO_OTHER_TEAMS("hideForOwnTeam"), TO_OWN_TEAM("hideForOtherTeams");

        private final String id;

        Show(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public enum Push {
        ALWAYS("always"), NEVER("never"), OTHER_TEAMS("pushOtherTeams"), OWN_TEAM("pushOwnTeam");

        private final String id;

        Push(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
