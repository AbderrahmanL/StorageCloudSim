/*
 * Title:        StorageCloudSim
 * Description:  StorageCloudSim (Storage as a Service Cloud Simulation), an extension for CloudSim
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2013, Karlsruhe Institute of Technology, Germany
 * https://github.com/toebbel/StorageCloudSim
 * http://www.tobiassturm.de/projects/storagecloudsim.html
 */
package edu.kit.cloudSimStorage.ObjectStorageSLAs;

import edu.kit.cloudSimStorage.CdmiCloudCharacteristics;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Represents a {@link edu.kit.cloudSimStorage.ObjectStorageSLAs.SLARequirement} that must be met by a Cloud before the attached {@link edu.kit.cloudSimStorage.UsageSequence} can be dispatched to it.
 *
 * Requires some numerical value of a characteristic to be smaller than the given level.
 * Can be used for example to model the requirement 'write latency must be lower than x'
 *
 * The existence of the characteristic key can be mandatory. The SLA requirement is fulfilled if the key is optional and not present.
 *
 * @author Tobias Sturm, 6/26/13 5:41 PM */
@Root
public class MaximumCharacteristicsValue extends SLARequirement {

	@Attribute(name="key")
	private String key;

	@Attribute(name="max")
	private double maxValue;

	@Attribute(name="must_exist")
	private boolean mustExist;

	public MaximumCharacteristicsValue(@Attribute(name="key") String key, @Attribute(name="max") double maxValue, @Attribute(name="must_exist") boolean mustExist) {
		this.key = key;
		this.maxValue = maxValue;
		this.mustExist = mustExist;
		if(mustExist)
			description = key + "!<=!" + maxValue;
		else
			description = key + "<=" + maxValue;
	}

	@Override
	public boolean match(CdmiCloudCharacteristics characteristics) {
		boolean result;
		if(mustExist)
			result = characteristics.contains(key) && Double.valueOf(characteristics.get(key)) <= maxValue;
		else
			result = !characteristics.contains(key) || Double.valueOf(characteristics.get(key)) <= maxValue;
		return logAndReturn(result, characteristics.getCloudID());
	}

}
