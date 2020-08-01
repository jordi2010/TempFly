package com.moneybags.tempfly.hook.skyblock.plugins;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import com.moneybags.tempfly.TempFly;
import com.moneybags.tempfly.fly.FlightResult;
import com.moneybags.tempfly.hook.TempFlyHook;
import com.moneybags.tempfly.hook.HookManager.HookType;
import com.moneybags.tempfly.hook.region.CompatRegion;
import com.moneybags.tempfly.hook.skyblock.IslandWrapper;
import com.moneybags.tempfly.hook.skyblock.SkyblockHook;
import com.moneybags.tempfly.user.FlightUser;

public class BentoHook extends SkyblockHook {

	public BentoHook(TempFly plugin) {
		super(HookType.BENTO_BOX, plugin);
	}

	@Override
	public IslandWrapper getIslandOwnedBy(UUID p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IslandWrapper getTeamIsland(UUID p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IslandWrapper getIslandAt(Location loc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isChallengeCompleted(UUID p, String challenge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean islandRoleExists(IslandWrapper island, String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean islandRoleExists(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getIslandRole(UUID p, IslandWrapper island) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID getIslandOwner(IslandWrapper island) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIslandIdentifier(IslandWrapper island) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIslandMember(UUID p, IslandWrapper island) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getIslandLevel(UUID p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getIslandLevel(IslandWrapper island) {
		// TODO Auto-generated method stub
		return 0;
	}



}