package com.example.examplemod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.example.examplemod.ExampleMod;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;

@Mixin(ItemRenderer.class)
public class renderHandMixin {
	
	@Inject(method="renderItemInFirstPerson",at=@At(value="HEAD"))
	public void renderItemInFirstPerson(float partialTicks,CallbackInfo callback) {
		GlStateManager.translate(-ExampleMod.offsetX, -ExampleMod.offsetY, -ExampleMod.offsetZ);
	}
}
