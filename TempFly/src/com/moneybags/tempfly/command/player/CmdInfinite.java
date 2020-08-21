package com.moneybags.tempfly.command.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.moneybags.tempfly.TempFly;
import com.moneybags.tempfly.user.FlightUser;
import com.moneybags.tempfly.util.U;
import com.moneybags.tempfly.util.V;

public class CmdInfinite {

	public CmdInfinite(CommandSender s, String[] args, TempFly tempfly) {
		if (!U.hasPermission(s, "tempfly.infinite.toggle")) {
			U.m(s, V.invalidPermission);
			return;
		}
		if (!U.isPlayer(s)) {
			U.m(s, V.invalidSender);
		}
		FlightUser user = tempfly.getFlightManager().getUser((Player)s);
		boolean toggleVal = false;
		if (args.length > 1) {
			switch (args[1].toLowerCase()) {
			case "on": case "enable":
				toggleVal = true;
			case "off": case "disable":
				break;
			default:
				U.m(s, "&c/tempfly infinite [on/off]");
				return;
			}
		} else {
			toggleVal = !user.hasInfiniteFlight();
		}
		user.setInfiniteFlight(toggleVal);
		// If the player has auto fly queued we will auto enable their flight when they toggle infinite time. 
		if (toggleVal && user.hasAutoFlyQueued()) {
			user.enableFlight();
		}
		U.m(s, toggleVal ? V.flyInfiniteEnabled : V.flyInfiniteDisabled);
	}

}
