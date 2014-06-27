/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2012 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.cuppytrail.systemsetup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.cuppy.constants.CuppyConstants;
import de.hybris.platform.cuppy.systemsetup.CuppySystemSetup;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class to load the pre-requisite wc2010 data for projectdataStadium.impex imports
 */
@SystemSetup(extension = "cuppytrail", process = Process.ALL, type = Type.PROJECT)
public class CuppyTrailSystemSetup extends CuppySystemSetup
{

	@SystemSetup
	public void importPreReqsFromCuppy()
	{
		//set up the items to import
		final Map<String, String[]> params = new HashMap<String, String[]>();
		final String[] p_basics =
		{ CuppyConstants.PARAM_BASICS_PLAYERS };
		params.put(CuppyConstants.EXTENSIONNAME + "_" + CuppyConstants.PARAM_BASICS, p_basics);

		final String[] p_wc2010 =
		{ CuppyConstants.PARAM_WC2010_SETUP };
		params.put(CuppyConstants.EXTENSIONNAME + "_" + CuppyConstants.PARAM_WC2010, p_wc2010);

		final SystemSetupContext ctx = new SystemSetupContext(params, Type.PROJECT, Process.ALL, CuppyConstants.EXTENSIONNAME);

		//here we use the same context for both. since it is a fairly flat data object, this works just fine
		//super.importBasics(ctx);
		super.importWC2010(ctx);
	}

	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getSystemSetupParameters()
	{
		return Collections.emptyList();
	}
}