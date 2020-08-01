package com.moneybags.tempfly.command.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.moneybags.tempfly.TempFly;
import com.moneybags.tempfly.event.FlightEnabledEvent;
import com.moneybags.tempfly.user.FlightUser;
import com.moneybags.tempfly.util.Console;
import com.moneybags.tempfly.util.U;
import com.moneybags.tempfly.util.V;

public class CmdFly {

	public CmdFly(TempFly tempfly, CommandSender s, String[] args) {
		Player p = null;
		boolean
			toggle = false,
			toggleVal = false;
		
		
		
		if (args.length > 1) {
			
			p = Bukkit.getPlayer(args[1]);
			if (p != null) {
				if (!U.hasPermission(s, "tempfly.toggle.other")) {
					U.m(s, V.invalidPermission);
					return;
				}
				
				if (args.length > 2) {
					toggle = true;
					if (args[2].equalsIgnoreCase("on")) {
						toggleVal = true;
					} else if (args[2].equalsIgnoreCase("off")) {
						toggleVal = false;
					} else {
						U.m(s, U.cc("&c/tf toggle [player] [on / off]"));
						return;
					}
				}
				
			} else if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
				if (!U.isPlayer(s)) {
					U.m(s, V.invalidSender);
					return;
				}
				if (!U.hasPermission(s, "tempfly.toggle.self")) {
					U.m(s, V.invalidPermission);
					return;
				}
				
				toggle = true;
				if (args[1].equalsIgnoreCase("on")) {
					toggleVal = true;
				} else if (args[1].equalsIgnoreCase("off")) {
					toggleVal = false;
				}
				
			} else if (args.length > 2) {
				U.m(s, V.invalidPlayer.replaceAll("\\{PLAYER}", args[1]));
				return;
			} else {
				U.m(s, U.cc("&c/tf toggle [on / off]"));
				return;
			}
		
			
		} else {
			if (!U.isPlayer(s)) {
				U.m(s, V.invalidSender);
				return;
			}
			if (!U.hasPermission(s, "tempfly.toggle.self")) {
				U.m(s, V.invalidPermission);
				return;
			}
		}
		if (p == null) {
			p = (Player)s;
		}
		
		
		FlightUser user = tempfly.getFlightManager().getUser(p);
		// if command is /fly on and player is not flying || command is base && player is not flying
		// try to enable flight
		if (toggle && toggleVal || !toggle && !toggleVal && !user.hasFlightEnabled()) {
			if (user.hasFlightEnabled()) {
				U.m(p, V.flyAlreadyEnabled);
				return;
			}
			// Time check 
			double time = tempfly.getTimeManager().getTime(p.getUniqueId());
			if ((time <= 0) && (!p.hasPermission("tempfly.time.infinite"))) {
				U.m(s, s.equals(p)
						? V.invalidTimeSelf
						: V.invalidTimeOther.replaceAll("\\{PLAYER}", p.getName()));
				return;
			}
			
			// Requirements check 
			if (!user.evaluateFlightRequirements(p.getLocation(), true)) {
				if (!user.hasAutoFlyQueued()) {
					user.setAutoFly(true);
				}
				return;
			}
			
			// Event check 
			FlightEnabledEvent e = new FlightEnabledEvent(p);
			Bukkit.getPluginManager().callEvent(e);
			if (e.isCancelled()) {
				return;
			}
			
			user.enableFlight();
			U.m(p, V.flyEnabledSelf);
			if (!s.equals(p)) {
				U.m(s, V.flyEnabledOther.replaceAll("\\{PLAYER}", p.getName()));	
			}
		// if command is /fly off and player is flying || command is base && player is flying
		// disable flight
		} else if (toggle && !toggleVal && user.hasFlightEnabled() || !toggle && user.hasFlightEnabled()) {
			user.disableFlight(0, V.protectCommand);
			U.m(p, V.flyDisabledSelf);
			if (!s.equals(p)) {
				U.m(s, V.flyDisabledOther.replaceAll("\\{PLAYER}", p.getName()));	
			}
		} else if (toggle && !toggleVal && user.hasAutoFlyQueued()) {
			user.setAutoFly(false);
			U.m(p, V.flyDisabledSelf);
			if (!s.equals(p)) {
				U.m(s, V.flyDisabledOther.replaceAll("\\{PLAYER}", p.getName()));	
			}
		} else {
			U.m(p, V.flyAlreadyDisabled);
		}
	}
}