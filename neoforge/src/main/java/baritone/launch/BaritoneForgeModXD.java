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

// --- Required Imports ---
import baritone.Baritone; // Import the main Baritone class to access the flag
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
// --- End Imports ---

@Mod("baritoe") // Ensure mod ID is correct
public class BaritoneForgeModXD {

    public BaritoneForgeModXD() {
        // Get the Mod Event Bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // --- Register the listener for the FMLCommonSetupEvent ---
        modEventBus.addListener(this::onCommonSetup);
    }

    /**
     * This method is called during the FMLCommonSetupEvent phase.
     * We use enqueueWork to ensure thread safety.
     * This is where we signal that the game is ready for the ItemStack mixin logic.
     */
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Set the flag in the core Baritone class to true
            Baritone.isGameReadyForBaritoneItemStackMixin = true;

            // Optional: Log that the flag has been set
            System.out.println("[Baritone Mod Entry] CommonSetup: ItemStack mixin compatibility flag enabled.");
        });
    }
}