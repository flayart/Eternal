package org.flayart.eternal.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ChatUtils {

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String colorHEX(String text) {
        Pattern pattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, "" + ChatColor.of(color));
        }

        return text;
    }
}
