package com.farmlist;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class FarmList implements ModInitializer {

    public static final ResourceKey<Item> CHECKLIST_BOOK_KEY =
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("farmlist", "checklist_book"));

    public static final Item CHECKLIST_BOOK = Registry.register(
            BuiltInRegistries.ITEM,
            CHECKLIST_BOOK_KEY,
            new ChecklistBookItem(new Item.Properties().setId(CHECKLIST_BOOK_KEY).stacksTo(1)));

    @Override
    public void onInitialize() {
    }
}
