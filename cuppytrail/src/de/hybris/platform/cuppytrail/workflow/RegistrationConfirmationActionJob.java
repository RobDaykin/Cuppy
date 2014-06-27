package de.hybris.platform.cuppytrail.workflow;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Automated workflow job that sends registration email to the new confirmed cuppy player. Part of the player
 * registration workflow.
 */
@Component
public class RegistrationConfirmationActionJob extends AbstractPlayerRegistrationActionJob
{
	private static final Logger LOG = Logger.getLogger(RegistrationConfirmationActionJob.class);

	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		final PlayerModel player = getAttachedPlayer(action);

		LOG.info("Player " + player.getUid() + " confirmed. Confirmation email will be sent.");

		if (!player.isConfirmed())
		{
			player.setConfirmed(true);
			getModelService().save(player);
		}

		getMailService().sendConfirmationMail(player);

		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			return decision;
		}
		return null;
	}
}