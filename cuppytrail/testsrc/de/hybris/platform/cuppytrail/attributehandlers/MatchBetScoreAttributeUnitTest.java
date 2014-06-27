package de.hybris.platform.cuppytrail.attributehandlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.cuppy.model.MatchBetModel;
import de.hybris.platform.cuppy.services.MatchService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


@UnitTest
public class MatchBetScoreAttributeUnitTest
{
	private MatchService serviceMock;
	private MatchBetScoreAttributeHandler handler;
	private MatchBetModel bet;

	@Before
	public void setUp()
	{
		serviceMock = Mockito.mock(MatchService.class);

		handler = new MatchBetScoreAttributeHandler();
		handler.setMatchService(serviceMock);

		bet = new MatchBetModel();
	}

	@Test
	public void testHandler()
	{
		setupMatchServiceResult(bet, 0);
		assertEquals(Integer.valueOf(0), handler.get(bet));

		setupMatchServiceResult(bet, 10);
		assertEquals(Integer.valueOf(10), handler.get(bet));

		setupMatchServiceResult(bet, -20);
		assertEquals(Integer.valueOf(-20), handler.get(bet));
	}

	@Test
	public void testHandlerInUnfinishedMatchMode()
	{
		setupMatchServiceException(bet, new IllegalStateException());
		assertNull(handler.get(bet));

		setupMatchServiceException(bet, new IllegalArgumentException());
		try
		{
			handler.get(bet);
			fail("expected exception passed through");
		}
		catch (final IllegalArgumentException e)
		{
			// fine
		}
	}

	private void setupMatchServiceResult(final MatchBetModel bet, final int result)
	{
		Mockito.reset(serviceMock);
		Mockito.when(Integer.valueOf(serviceMock.getScore(bet))).thenReturn(Integer.valueOf(result));
	}

	private void setupMatchServiceException(final MatchBetModel bet, final Exception e)
	{
		Mockito.reset(serviceMock);
		Mockito.when(Integer.valueOf(serviceMock.getScore(bet))).thenThrow(e);
	}
}