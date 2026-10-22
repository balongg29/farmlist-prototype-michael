package com.farmlist;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

/** The book's front page: pick a checklist, make a new one, or import from the clipboard. */
public class PickerScreen extends Screen {

    private static final int BUTTON_WIDTH = 240;
    private static final int ROW_HEIGHT = 22;

    public PickerScreen() {
        super(Component.literal("Farm Checklist"));
    }

    @Override
    protected void init() {
        rebuild();
    }

    private void rebuild() {
        clearWidgets();
        int left = this.width / 2 - BUTTON_WIDTH / 2;

        addRenderableWidget(Button.builder(Component.literal("New Checklist"), b ->
                this.minecraft.gui.setScreen(new NameScreen())
        ).bounds(left, 32, BUTTON_WIDTH, 20).build());

        addRenderableWidget(Button.builder(Component.literal("Import from Clipboard"), b -> {
            String imported = FarmListClient.importFromClipboard();
            if (imported != null) this.minecraft.gui.setScreen(new ChecklistScreen(imported));
        }).bounds(left, 56, BUTTON_WIDTH, 20).build());

        int y = 84;
        List<String> files = FarmListClient.listTranscriptions();
        for (String file : files) {
            String name = file.replace(".txt", "");
            addRenderableWidget(Button.builder(Component.literal(name), b ->
                    this.minecraft.gui.setScreen(new ChecklistScreen(file))
            ).bounds(left, y, BUTTON_WIDTH, 20).build());
            y += ROW_HEIGHT;
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(g, mouseX, mouseY, partialTick);
        g.centeredText(this.font, Component.literal("Your Checklists"), this.width / 2, 14, 0xFFFFFFFF);
        if (!FarmListClient.status.isEmpty()) {
            g.centeredText(this.font, Component.literal(FarmListClient.status), this.width / 2, this.height - 14, 0xFFFFFFFF);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
