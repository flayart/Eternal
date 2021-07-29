package org.flayart.eternal.builders;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ComponentBuilder {
    private final TextComponent component;

    public ComponentBuilder(String text) {
        component = new TextComponent(text);
    }

    public ComponentBuilder setHover(String text) {
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(text)));
        return this;
    }
    
    public ComponentBuilder setUrl(String url) {
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return this;
    }
    
    public ComponentBuilder setCommand(String command) {
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }
    
    public ComponentBuilder setSuggestCommand(String command) {
        component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        return this;
    }
    
    public TextComponent build() {
        return component;
    }
}
