package com.shinybunny.mcfapi.advancements;

import com.shinybunny.mcfapi.BlockItem;
import com.shinybunny.mcfapi.Item;
import com.shinybunny.mcfapi.Namespace;
import com.shinybunny.mcfapi.TextComponent;
import com.shinybunny.mcfapi.server.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Advancement {

    private final ResourceLocation id;
    private Item icon;
    private TextComponent description;
    private TextComponent title;
    private FrameType frame = FrameType.TASK;
    private ResourceLocation parent;
    private ResourceLocation background;
    private Map<String,AdvancementCriterion> criteria;
    private Map<String,List<String>> requirements;
    private Reward reward;

    public Advancement(ResourceLocation id) {
        this.id = id;
        criteria = new HashMap<>();
        requirements = new HashMap<>();
    }

    public Item getIcon() {
        return icon;
    }

    public TextComponent getDescription() {
        return description;
    }

    public FrameType getFrame() {
        return frame;
    }

    public TextComponent getTitle() {
        return title;
    }

    public static class Instance {

        private PlayerAdvancements list;
        private Advancement advancement;

        Instance(PlayerAdvancements list, Advancement advancement) {
            this.list = list;
            this.advancement = advancement;
        }

        public void grant() {
            list.change("grant", this);
        }

        public void grant(String criterion) {
            list.change("grant", this, criterion);
        }

        public void revoke() {
            list.change("revoke", this);
        }

        public void revoke(String criterion) {
            list.change("revoke", this, criterion);
        }

        public void grant(GrantRevoker by) {
            list.action("grant", this, by);
        }

        public void revoke(GrantRevoker by) {
            list.action("revoke", this, by);
        }

        @Override
        public String toString() {
            return advancement.toString();
        }
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    public static class Builder {

        private Advancement advancement;

        public Builder(ResourceLocation id) {
            advancement = new Advancement(id);
        }

        public Builder parent(ResourceLocation id) {
            if (advancement.background == null) {
                advancement.parent = id;
            }
            return this;
        }

        public Builder background(ResourceLocation id) {
            if (advancement.parent == null) {
                advancement.background = id;
            }
            return this;
        }

        public Builder icon(Item icon) {
            advancement.icon = icon;
            return this;
        }

        public Builder description(TextComponent text) {
            advancement.description = text;
            return this;
        }

        public Builder description(String text) {
            advancement.description = TextComponent.plain(text);
            return this;
        }

        public Builder title(TextComponent text) {
            advancement.title = text;
            return this;
        }

        public Builder title(String text) {
            advancement.title = TextComponent.plain(text);
            return this;
        }

        public Builder frame(FrameType type) {
            advancement.frame = type;
            return this;
        }

        public Builder addCriterion(String id, AdvancementCriterion criterion) {
            advancement.criteria.put(id,criterion);
            return this;
        }

        public Builder addCriterion(String id, AdvancementCriterion criterion, String groupId) {
            advancement.criteria.put(id,criterion);
            advancement.requirements.merge(groupId,new ArrayList<>(),(o,v)->{
                o.add(id);
                return o;
            });
            return this;
        }
    }
}
