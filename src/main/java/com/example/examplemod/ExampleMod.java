package com.example.examplemod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.swing.TextComponent;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "viewModel";
    public static final String VERSION = "1.0";
    public static double offsetX = 0d;
    public static double offsetY = 0d;
    public static double offsetZ = 0d;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        try {
			File f = new File("viewModelConfig.txt");
			String config;
			if(!f.exists()) {
				f.createNewFile();
				config = this.createDefaultConfig();
			}
			FileInputStream file = new FileInputStream("viewModelConfig.txt");
			config = IOUtils.toString(file);
			file.close();
			this.readConfig(config);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    public static boolean onCommand(String msg) {
    	if(!msg.startsWith("vm! ")) return false;
    	String[] args = msg.split(" ");
    	if(args.length<4) showMsg("Correct command: vm! <x> <y> <z>");
    	double x,y,z;
    	try {
    		x = Double.valueOf(args[1]);
    		y = Double.valueOf(args[2]);
    		z = Double.valueOf(args[3]);
    	} catch(Exception e) {
    		showMsg("Invalid Inputs");
    		return true;
    	}
    	offsetX = x;
    	offsetY = y;
    	offsetZ = z;
    	writeConfig();
    	return true;
    }
    
    private static void showMsg(String msg) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[*]ViewModel: "+msg));
    }
    private void readConfig(String config) {
    	String[] args = config.split(",");
		double x,y,z;
		try {
	    	x = Double.valueOf(args[0]);
	    	y = Double.valueOf(args[1]);
	   		z = Double.valueOf(args[2]);
	   	} catch(Exception e) {
	   		this.readConfig(this.createDefaultConfig());
	   		return;
	   	}
    	offsetX = x;
	    offsetY = y;
	   	offsetZ = z;
    }
    
    private static void writeConfig() {
    	String config = offsetX+","+offsetY+","+offsetZ;
    	try {
			FileWriter fileW = new FileWriter("viewModelConfig.txt");
			fileW.write(config);
			fileW.flush();
			fileW.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private String createDefaultConfig() {
    	return "0.0,0.0,0.5";
    }
}
