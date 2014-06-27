package de.hybris.platform.cuppytrail.impl;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.cuppy.services.impl.DefaultPlayerService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.workflow.WorkflowProcessingService;
import de.hybris.platform.workflow.WorkflowService;
import de.hybris.platform.workflow.WorkflowTemplateService;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowModel;
import de.hybris.platform.workflow.model.WorkflowTemplateModel;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Extended DefaultPlayerService from cuppy. Note: for simplicity we just extended the class DefaultPlayerService,
 * better practice would be to use the delegation pattern.
 */
public class CuppytrailPlayerService extends DefaultPlayerService
{
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private WorkflowTemplateService workflowTemplateService;
	@Autowired
	private WorkflowProcessingService workflowProcessingService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelService modelService;

	/**
	 * Overrides the registerPlayer method from the cuppy to start player registration workflow additionally.
	 */
	@Override
	public void registerPlayer(final PlayerModel player)
	{
		// execute logic from the cuppy player service
		super.registerPlayer(player);

		// start the registration workflow for the new player
		final WorkflowTemplateModel workflowTemplate = this.workflowTemplateService
				.getWorkflowTemplateForCode("NewPlayerRegistration");

		final WorkflowModel workflow = this.workflowService.createWorkflow(workflowTemplate, player, userService.getAdminUser());
		modelService.save(workflow);
		for (final WorkflowActionModel action : workflow.getActions())
		{
			modelService.save(action);
		}

		this.workflowProcessingService.startWorkflow(workflow);
	}

	public void setWorkflowService(final WorkflowService workflowService)
	{
		this.workflowService = workflowService;
	}

	public void setWorkflowTemplateService(final WorkflowTemplateService workflowTemplateService)
	{
		this.workflowTemplateService = workflowTemplateService;
	}

	public void setWorkflowProcessingService(final WorkflowProcessingService workflowProcessingService)
	{
		this.workflowProcessingService = workflowProcessingService;
	}

	@Override
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}