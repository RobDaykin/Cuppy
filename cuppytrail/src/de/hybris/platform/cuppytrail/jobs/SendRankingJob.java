/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2011 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package de.hybris.platform.cuppytrail.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.MailService;
import de.hybris.platform.cuppy.services.PlayerService;
import de.hybris.platform.cuppy.services.RankingData;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.List;

import org.apache.log4j.Logger;


public class SendRankingJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(SendRankingJob.class);

	private PlayerService playerService;
	private MailService mailService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		LOG.info("Sending ranking mails");
		final List<RankingData> rankings = playerService.getRankings();

		if (rankings.isEmpty())
		{
			LOG.info("No competitions have changed, skipping send of ranking mails");
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}

		for (final PlayerModel player : playerService.getAllPlayers())
		{
			final List<RankingData> playerRankings = playerService.filterRankingsForPlayer(rankings, player);
			if (!playerRankings.isEmpty() && player.isSendNewsletter())
			{
				mailService.sendRankingMail(player, playerRankings);
			}
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public void setPlayerService(final PlayerService playerService)
	{
		this.playerService = playerService;
	}

	public void setMailService(final MailService trailMailService)
	{
		this.mailService = trailMailService;
	}
}