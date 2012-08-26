/**
 * Copyright (c) SpaceToad, 2011
 * http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */

package buildcraft.transport;

import buildcraft.api.core.Orientations;

public interface IPipeTransportPowerHook {

	public void receiveEnergy(Orientations from, double val);

	public void requestEnergy(Orientations from, int i);
}
