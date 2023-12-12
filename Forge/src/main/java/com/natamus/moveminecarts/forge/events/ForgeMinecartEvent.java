package com.natamus.moveminecarts.forge.events;

import com.natamus.moveminecarts.events.MinecartEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeMinecartEvent {
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.getCommandSenderWorld();
		if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		MinecartEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public void onMinecartClick(PlayerInteractEvent.EntityInteract e) {
		if (MinecartEvent.onMinecartClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
