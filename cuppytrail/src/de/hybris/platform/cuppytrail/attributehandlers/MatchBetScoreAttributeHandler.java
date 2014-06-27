package de.hybris.platform.cuppytrail.attributehandlers;

import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.cuppy.services.MatchService;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MatchBetScoreAttributeHandler extends AbstractDynamicAttributeHandler<Integer, MatchBetModel>
{

	@Autowired
	private MatchService matchService;

	@Override
	public Integer get(final MatchBetModel model)
	{
		try
		{
			return Integer.valueOf(matchService.getScore(model));
		}
		catch (final IllegalStateException e)
		{
			return null;
		}
	}

	public void setMatchService(final MatchService matchService)
	{
		this.matchService = matchService;
	}
}