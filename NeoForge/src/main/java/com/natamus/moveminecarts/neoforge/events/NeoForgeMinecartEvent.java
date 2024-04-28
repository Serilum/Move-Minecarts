package com.natamus.moveminecarts.neoforge.events;

import com.natamus.moveminecarts.events.MinecartEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeMinecartEvent {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
		Player player = e.player;
		Level level = player.level();
		if (level.isClientSide || !e.phase.equals(TickEvent.Phase.START)) {
			return;
		}

		MinecartEvent.onPlayerTick((ServerLevel)level, (ServerPlayer)player);
	}

	@SubscribeEvent
	public static void onMinecartClick(PlayerInteractEvent.EntityInteract e) {
		if (MinecartEvent.onMinecartClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null).equals(InteractionResult.SUCCESS)) {
			e.setCanceled(true);
		}
	}
}
