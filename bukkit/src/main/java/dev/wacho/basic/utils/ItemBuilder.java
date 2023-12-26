package dev.wacho.basic.utils;

import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.wacho.basic.utils.CC;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(XMaterial.matchXMaterial(material).parseMaterial(), 1, 0);
    }

    public ItemBuilder(Material material, int amount) {
        this(XMaterial.matchXMaterial(material).parseMaterial(), amount, 0);
    }

    public ItemBuilder(String material) {
        this(XMaterial.matchXMaterial(material).get().parseMaterial(), 1, 0);
    }

    public ItemBuilder(Material material, int amount, int data) {
        this.itemStack = new ItemStack(XMaterial.matchXMaterial(material).parseMaterial(), amount, (short) data);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDurability(int data) {
        this.itemStack.setDurability((short) data);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder addAmount(int amount) {
        this.itemStack.setAmount(this.itemStack.getAmount() + amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(CC.translate(name));
        return this;
    }

    public ItemBuilder setSkullOwner(String ownerOrValue) {
        if (ownerOrValue == null || ownerOrValue.isEmpty()) return this;
        SkullMeta meta = (SkullMeta) this.itemMeta;

        if (ownerOrValue.startsWith("eyJ")) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", ownerOrValue));

            Field profileField;

            try {
                profileField = meta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(meta, profile);
            }
            catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
        }
        else {
            meta.setOwner(ownerOrValue);
        }
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(CC.translate(lore));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(CC.translate(Arrays.asList(lore)));
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        this.itemMeta.getLore().add(CC.translate(line));
        return this;
    }

    public ItemBuilder setEnchanted(boolean enchanted) {
        if (enchanted) {
            this.itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemBuilder addEnchantment() {
        this.itemStack.addEnchantment(Enchantment.DURABILITY, 1);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder setRGBColor(int n, int n2, int n3) {
        if (this.itemMeta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta)this.itemMeta).setColor(Color.fromRGB(n, n2, n3));
        }
        return this;
    }

    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    public ItemStack toItemStack() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }
}