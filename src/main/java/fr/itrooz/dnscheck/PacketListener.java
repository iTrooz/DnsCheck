package fr.itrooz.dnscheck;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class PacketListener extends PacketAdapter {
	public PacketListener(Plugin plugin) {
		super(plugin, PacketType.Handshake.Client.SET_PROTOCOL);
	}

	@Override
	public void onPacketReceiving(com.comphenix.protocol.events.PacketEvent event) {
		PacketContainer packet = event.getPacket();
		String hostname = packet.getStrings().read(0);

		List<String> allowedDomains = getPlugin().getConfig().getStringList("allowed-hostnames");
		if(!allowedDomains.contains(hostname)) {
			event.setCancelled(true);
		}
	}
}
