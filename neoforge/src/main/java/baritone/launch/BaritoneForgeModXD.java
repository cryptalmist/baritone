/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.launch;

// ================= Imports =================
import baritone.Baritone;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
// ===========================================

@Mod("baritoe") // MUST match mods.toml
public class BaritoneForgeModXD {

    /**
     * NeoForge injects the Mod Event Bus directly into the constructor.
     * This replaces FMLJavaModLoadingContext from Forge.
     */
    public BaritoneForgeModXD(IEventBus modEventBus) {
        modEventBus.addListener(this::onCommonSetup);
    }

    /**
     * Called during the common setup phase.
     * enqueueWork ensures this runs on the correct thread.
     */
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Signal that the game is ready for Baritone ItemStack mixin logic
            Baritone.isGameReadyForBaritoneItemStackMixin = true;

            // Optional log
            System.out.println("[Baritone] CommonSetup: ItemStack mixin compatibility flag enabled.");
        });
    }
}