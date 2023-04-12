package com.example.examplemod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.gui.GuiScreen;

@Mixin(GuiScreen.class)
public class sendMessageMixin {
	
	@Inject(method="sendChatMessage(Ljava/lang/String;Z)V",at=@At(value="HEAD"),cancellable=true)
	public void sendChatMessage(String msg, boolean addToChat,CallbackInfo callback) {
		if(ExampleMod.onCommand(msg)) callback.cancel();
	}
}
