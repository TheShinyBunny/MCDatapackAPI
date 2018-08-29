package com.shinybunny.mcfapi.entity;

import com.shinybunny.mcfapi.*;
import com.shinybunny.mcfapi.advancements.PlayerAdvancements;
import com.shinybunny.mcfapi.server.Enchantment;
import com.shinybunny.mcfapi.server.GameMode;
import com.shinybunny.mcfapi.server.ResourceLocation;

public class PlayerList extends EntityList {

    private PlayerAdvancements advancements;
    private XP xp;
    private Recipes recipes;

    public PlayerList(EntitySelector selector) {
        super(selector);
        this.advancements = new PlayerAdvancements(this);
        this.xp = createXP();
        this.recipes = new Recipes(this);
    }

    public PlayerList() {
        this.advancements = new PlayerAdvancements(this);
        this.xp = createXP();
        this.recipes = new Recipes(this);
    }

    protected XP createXP() {
        return new XP(this);
    }

    public final PlayerAdvancements getAdvancements() {
        return advancements;
    }

    public final void clearInventory() {
        if (this instanceof FunctionContainer) {
            DatapackManager.writeCommand("clear");
        } else {
            DatapackManager.writeCommand("clear " + this);
        }
    }

    public final void clear(Item item) {
        DatapackManager.writeCommand("clear " + this + " " + item);
    }

    public final void clear(Item item, int count) {
        DatapackManager.writeCommand("clear " + this + " " + item + " " + count);
    }

    public final void enchant(Enchantment ench) {
        DatapackManager.writeCommand("enchant " + this + " " + ench.toString().toLowerCase());
    }

    public final void enchant(Enchantment ench, int level) {
        DatapackManager.writeCommand("enchant " + this + " " + ench.toString().toLowerCase() + " " + level);
    }

    public XP getXP() {
        return xp;
    }

    public final void setGameMode(GameMode gm) {
        if (this instanceof FunctionContainer) {
            DatapackManager.writeCommand("gamemode " + gm.toString().toLowerCase());
        } else {
            DatapackManager.writeCommand("gamemode " + gm.toString().toLowerCase() + " " + this);
        }
    }

    public final void give(Item item) {
        DatapackManager.writeCommand("give " + this + " " + item);
    }

    public final void give(Item item, int count) {
        DatapackManager.writeCommand("give " + this + " " + item + " " + count);
    }

    public final void playSound(ResourceLocation sound, SoundSource src) {
        DatapackManager.writeCommand("playsound " + sound + " " + src);
    }

    public final void playSound(ResourceLocation sound, SoundSource src, Position pos) {
        DatapackManager.writeCommand("playsound " + sound + " " + src + " " + pos);
    }

    public final void playSound(ResourceLocation sound, SoundSource src, Position pos, float volume) {
        DatapackManager.writeCommand("playsound " + sound + " " + src + " " + pos + " " + volume);
    }

    public final void playSound(ResourceLocation sound, SoundSource src, Position pos, float volume, float pitch) {
        DatapackManager.writeCommand("playsound " + sound + " " + src + " " + pos + " " + volume + " " + pitch);
    }

    public final void playSound(ResourceLocation sound, SoundSource src, Position pos, float volume, float pitch, float minVolume) {
        DatapackManager.writeCommand("playsound " + sound + " " + src + " " + pos + " " + volume + " " + pitch + " " + minVolume);
    }

    public final void stopSound() {
        DatapackManager.writeCommand("stopsound " + this);
    }

    public final void stopSound(SoundSource src) {
        DatapackManager.writeCommand("stopsound " + this + " " + src);
    }

    public final void stopSound(SoundSource src, ResourceLocation sound) {
        DatapackManager.writeCommand("stopsound " + this + " " + src + " " + sound);
    }

    public final Recipes getRecipes() {
        return recipes;
    }

    public final void setSpawnPoint(Position pos) {
        if (this instanceof FunctionContainer) {
            DatapackManager.writeCommand("spawnpoint " + pos);
        } else {
            DatapackManager.writeCommand("spawnpoint " + pos + " " + this);
        }
    }

    public final void tellraw(TextComponent text) {
        DatapackManager.writeCommand("tellraw " + this + " " + text);
    }

    public final void title(TextComponent title) {
        DatapackManager.writeCommand("title " + this + " title " + title);
    }

    public final void subtitle(TextComponent subtitle) {
        DatapackManager.writeCommand("title " + this + " subtitle " + subtitle);
    }

    public final void clearTitle() {
        DatapackManager.writeCommand("title " + this + " clear");
    }

    public final void resetTitle() {
        DatapackManager.writeCommand("title " + this + " reset");
    }

    public final void setTitleTimes(int fadeIn, int stay, int fadeOut) {
        DatapackManager.writeCommand("title " + this + " times " + fadeIn + " " + stay + " " + fadeOut);
    }

    public final void actionbar(TextComponent actionbar) {
        DatapackManager.writeCommand("title " + this + " actionbar " + actionbar);
    }

}
