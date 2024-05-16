package cannolicat.plotsquaredmythicaddon.conditions;

import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.math.BlockVector3;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;

public class InPlot implements IEntityCondition {
    @Override
    public boolean check(AbstractEntity abstractEntity) {
        org.bukkit.Location location = BukkitAdapter.adapt(abstractEntity.getLocation());
        Plot plot = Location.at(location.getWorld().getName(), BlockVector3.at(location.getX(), location.getY(), location.getZ())).getOwnedPlot();
        return plot != null;
    }
}
