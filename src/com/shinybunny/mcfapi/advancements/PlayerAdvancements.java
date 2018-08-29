package com.shinybunny.mcfapi.advancements;

import com.shinybunny.mcfapi.DatapackManager;
import com.shinybunny.mcfapi.server.ResourceLocation;
import com.shinybunny.mcfapi.entity.PlayerList;

public class PlayerAdvancements {

    private PlayerList owners;

    public PlayerAdvancements(PlayerList owners) {
        this.owners = owners;
    }

    public Advancement.Instance get(ResourceLocation id) {
        return new Advancement.Instance(this,DatapackManager.getDatapack().getWorld().getAdvancement(id));
    }

    public void grantAll() {
        DatapackManager.writeCommand("advancement grant " + owners + " everything");
    }

    public void revokeAll() {
        DatapackManager.writeCommand("advancement revoke " + owners + " everything");
    }

    public PlayerList getOwners() {
        return owners;
    }

    void change(String change, Advancement.Instance advancement) {
        DatapackManager.writeCommand("advancement " + change + " " + owners + " only " + advancement);
    }

    void change(String change, Advancement.Instance advancement, String criterion) {
        DatapackManager.writeCommand("advancement " + change + " " + owners + " only " + advancement + " " + criterion);
    }

    void action(String change, Advancement.Instance advancement, GrantRevoker gr) {
        DatapackManager.writeCommand("advancement " + change + " " + owners + " " + gr.toString().toLowerCase() + " " + advancement);
    }
}
