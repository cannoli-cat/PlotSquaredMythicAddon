package cannolicat.plotsquaredmythicaddon;

import cannolicat.plotsquaredmythicaddon.conditions.InPlot;
import cannolicat.plotsquaredmythicaddon.conditions.InRoad;
import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlotSquaredMythicAddon extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent event) {
        switch (event.getConditionName().toUpperCase()) {
            case "INPLOT" ->
                    event.register(new InPlot());
            case "INROAD" ->
                    event.register(new InRoad());
            default -> {}
        }
    }
}
