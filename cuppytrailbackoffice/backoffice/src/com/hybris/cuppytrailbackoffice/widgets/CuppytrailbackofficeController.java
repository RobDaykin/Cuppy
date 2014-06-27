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
 */
package com.hybris.cuppytrailbackoffice.widgets;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;

import com.hybris.cuppytrailbackoffice.services.CuppytrailbackofficeService;

import com.hybris.cockpitng.util.DefaultWidgetController;


public class CuppytrailbackofficeController extends DefaultWidgetController
{
	private static final long serialVersionUID = 1L;
	private Label label;

	@WireVariable
	private CuppytrailbackofficeService cuppytrailbackofficeService;

	@Override
	public void initialize(final Component comp)
	{
		super.initialize(comp);
		label.setValue(cuppytrailbackofficeService.getHello() + " CuppytrailbackofficeController");
	}
}
