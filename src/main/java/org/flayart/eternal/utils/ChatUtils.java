package org.flayart.eternal.utils;

import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class ChatUtils {
    private final Pattern pattern = Pattern.compile("(?<!\\\\)(#[a-fA-F0-9]{6})");

    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String colorHEX(String text) {
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, "" + ChatColor.of(color));
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public List<String> color(String... text) {
        List<String> strings = Lists.newArrayList();
        for(String string : text) {
            strings.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return strings;
    }

    public List<String> colorHEX(String... text) {
        List<String> strings = Lists.newArrayList();

        for(String string : text) {
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                String color = string.substring(matcher.start(), matcher.end());
                string = string.replace(color, "" + ChatColor.of(color));
            }

            strings.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return strings;
    }
}
