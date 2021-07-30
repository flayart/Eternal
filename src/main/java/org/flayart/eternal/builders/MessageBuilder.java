package org.flayart.eternal.builders;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.flayart.eternal.utils.ChatUtils;

public class MessageBuilder {
    private final Player player;
    private String text;

    public MessageBuilder(Player player, String text) {
        this.player = player;
        this.text = text;
    }

    public MessageBuilder color() {
        text = ChatUtils.color(text);
        return this;
    }

    public MessageBuilder placeholder(String placeholder, String result) {
        text = text.replace("%" + placeholder + "%", result);
        return this;
    }

    public void send() {
        player.sendMessage(text);
    }

    public void send(ChatMessageType type) {
        if (type == ChatMessageType.ACTION_BAR)
            player.sendActionBar(Component.translatable(text));
        else
            player.sendMessage(Component.translatable(text));
    }
}
