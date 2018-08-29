package com.shinybunny.mcfapi;

import com.shinybunny.mcfapi.entity.Entity;
import com.shinybunny.mcfapi.entity.EntityList;
import com.shinybunny.mcfapi.entity.EntitySelector;

import java.util.function.Consumer;

public class Segment {

    private String keyBind;
    private String text;
    private String translationKey;
    private TextComponent[] translationWith;
    private ChatColor color;
    private int formatFlags;
    private EntityList selector;

    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int UNDERLINE = 4;
    public static final int STRIKETHROUGH = 8;
    public static final int OBFUSCATED = 16;
    private String insertion;
    private ClickAction clickAction;
    private String clickValue;
    private HoverAction hoverAction;
    private String hoverValue;

    public Segment(String text) {
        this.text = text;
    }

    public Segment(String translationKey, TextComponent... translationWith) {
        this.translationKey = translationKey;
        this.translationWith = translationWith;
    }

    public Segment(EntityList selector) {
        this.selector = selector;
    }

    public Segment(String key, boolean keybind) {
        if (keybind) {
            this.keyBind = key;
        }
    }

    public static Segment text(String text) {
        return new Segment(text);
    }

    public static Segment translation(String key, TextComponent... with) {
        return new Segment(key,with);
    }

    public static Segment selector(EntityList selector) {
        return new Segment(selector);
    }

    public static Segment keybind(String key) {
        return new Segment(key,true);
    }

    public Segment color(ChatColor color) {
        if (color.isFormat()) {
            throw new IllegalArgumentException("Chat Color for Json Text Component must be a color! Use .italic(), .bold(), etc. for format");
        }
        this.color = color;
        return this;
    }

    public Segment bold() {
        formatFlags |= BOLD;
        return this;
    }

    public Segment italic() {
        formatFlags |= ITALIC;
        return this;
    }

    public Segment underline() {
        formatFlags |= UNDERLINE;
        return this;
    }

    public Segment strikeThrough() {
        formatFlags |= STRIKETHROUGH;
        return this;
    }

    public Segment obfuscated() {
        formatFlags |= OBFUSCATED;
        return this;
    }

    public Segment chatInsertion(String insert) {
        this.insertion = insert;
        return this;
    }

    public Segment openURL(String url) {
        this.clickAction = ClickAction.OPEN_URL;
        this.clickValue = url;
        return this;
    }

    void setClickValue(String clickValue) {
        this.clickValue = clickValue;
    }

    public <T extends CommandExecutor> Segment runCommand(T executor , Consumer<T> run) {
        this.clickAction = ClickAction.RUN_COMMAND;
        DatapackManager.getFunctionWriter().setCurrentTextComponent(this);
        run.accept(executor);
        DatapackManager.getFunctionWriter().setCurrentTextComponent(null);
        return this;
    }

    public Segment changePage(int page) {
        this.clickAction = ClickAction.CHANGE_PAGE;
        this.clickValue = page + "";
        return this;
    }

    public <T extends CommandExecutor> Segment suggestCommand(T executor , Consumer<T> suggestion) {
        this.clickAction = ClickAction.SUGGEST_COMMAND;
        DatapackManager.getFunctionWriter().setCurrentTextComponent(this);
        suggestion.accept(executor);
        DatapackManager.getFunctionWriter().setCurrentTextComponent(null);
        return this;
    }

    public Segment showText(TextComponent text) {
        this.hoverAction = HoverAction.SHOW_TEXT;
        this.hoverValue = text.toString();
        return this;
    }

    public Segment showItem(Item item) {
        this.hoverAction = HoverAction.SHOW_ITEM;
        this.hoverValue = item.toNBT().toString();
        return this;
    }

    public Segment showEntity(EntitySelector<Entity> selector) {
        this.hoverAction = HoverAction.SHOW_ENTITY;
        this.hoverValue = selector.toNBT().toString();
        return this;
    }

    public enum ClickAction {
        OPEN_URL, RUN_COMMAND, CHANGE_PAGE, SUGGEST_COMMAND
    }

    public enum HoverAction {
        SHOW_TEXT, SHOW_ITEM, SHOW_ENTITY
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{");
        if (text != null) {
            addProperty(b,"text",text);
        }
        if (keyBind != null) {
            addProperty(b,"keybind",keyBind);
        }
        if (translationKey != null) {
            addProperty(b,"translate",translationKey);
            if (translationWith.length != 0) {
                b.append("\"with\":[");
                for (TextComponent comp : translationWith) {
                    b.append(comp.toString());
                    b.append(",");
                }
                b.deleteCharAt(b.length()-1);
                b.append("]");
            }
        }
        if (selector != null) {
            addProperty(b,"selector",selector.toString());
        }
        if (color != null) {
            addProperty(b,"color",color.toString().toLowerCase());
        }
        if (formatFlags != 0) {
            if ((formatFlags & BOLD) == BOLD) {
                addProperty(b,"bold",true);
            }
            if ((formatFlags & ITALIC) == ITALIC) {
                addProperty(b,"italic",true);
            }
            if ((formatFlags & UNDERLINE) == UNDERLINE) {
                addProperty(b,"underline",true);
            }
            if ((formatFlags & STRIKETHROUGH) == STRIKETHROUGH) {
                addProperty(b,"strikethrough",true);
            }
            if ((formatFlags & OBFUSCATED) == OBFUSCATED) {
                addProperty(b,"obfuscated",true);
            }
        }
        if (insertion != null) {
            addProperty(b,"insertion",insertion);
        }
        if (clickAction != null) {
            b.append(",\"clickEvent\":{\"action\":\"" + clickAction.toString().toLowerCase() + "\",\"value\":\"" + clickValue + "\"}");
        }
        if (hoverAction != null) {
            b.append(",\"hoverEvent\":{\"action\":\"" + hoverAction.toString().toLowerCase() + "\",\"value\":\"" + hoverValue + "\"}");
        }
        b.append("}");
        return b.toString();
    }

    private void addProperty(StringBuilder b, String key, Object value) {
        if (b.charAt(b.length()-1) != '{') {
            b.append(",");
        }
        b.append("\"" + key + "\":");
        if (value instanceof Boolean) {
            b.append(value);
        } else {
            b.append("\"" + value + "\"");
        }
    }
}
