package cannolicat.plotsquaredmythicaddon;

import cannolicat.plotsquaredmythicaddon.conditions.InPlot;
import cannolicat.plotsquaredmythicaddon.conditions.InRoad;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.math.BlockVector3;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import io.lumine.mythic.bukkit.events.MythicReloadedEvent;
import io.lumine.mythic.core.skills.placeholders.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlotSquaredMythicAddon extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        registerPlaceholders();
    }

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent e) {
        switch (e.getConditionName().toUpperCase()) {
            case "INPLOT" ->
                    e.register(new InPlot());
            case "INROAD" ->
                    e.register(new InRoad());
            default -> {}
        }
    }

    @EventHandler
    public void onMythicReload(MythicReloadedEvent e) {
        registerPlaceholders();
    }

    private void registerPlaceholders() {
        MythicBukkit.inst().getPlaceholderManager().register("caster.plot.owner.name", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getCaster().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : Bukkit.getPlayer(plot.getOwner()).getName();
        }));
        MythicBukkit.inst().getPlaceholderManager().register("target.plot.owner.name", Placeholder.target((m, t, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(t.getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : Bukkit.getPlayer(plot.getOwner()).getName();
        }));
        MythicBukkit.inst().getPlaceholderManager().register("trigger.plot.owner.name", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getTrigger().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : Bukkit.getPlayer(plot.getOwner()).getName();
        }));
        MythicBukkit.inst().getPlaceholderManager().register("caster.plot.owner.uuid", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getCaster().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : plot.getOwner() + "";
        }));
        MythicBukkit.inst().getPlaceholderManager().register("target.plot.owner.uuid", Placeholder.target((m, t, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(t.getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : plot.getOwner() + "";
        }));
        MythicBukkit.inst().getPlaceholderManager().register("trigger.plot.owner.uuid", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getTrigger().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
            return plot == null ? "UNOWNED" : plot.getOwner() + "";
        }));
        MythicBukkit.inst().getPlaceholderManager().register("caster.plot.id", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getCaster().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getPlot();
            return plot == null ? "NULL" : plot.getId().toDashSeparatedString();
        }));
        MythicBukkit.inst().getPlaceholderManager().register("target.plot.id", Placeholder.target((m, t, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(t.getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getPlot();
            return plot == null ? "NULL" : plot.getId().toDashSeparatedString();
        }));
        MythicBukkit.inst().getPlaceholderManager().register("trigger.plot.id", Placeholder.meta((m, s) -> {
            org.bukkit.Location location = BukkitAdapter.adapt(m.getTrigger().getLocation());
            Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getPlot();
            return plot == null ? "NULL" : plot.getId().toDashSeparatedString();
        }));
    }
}
