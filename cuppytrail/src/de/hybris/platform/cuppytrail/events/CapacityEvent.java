package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


public class CapacityEvent extends AbstractEvent implements ClusterAwareEvent
{
	private final Integer capacity;
	private final String code;

	public CapacityEvent(final String code, final Integer capacity)
	{
		super();
		this.code = code;
		this.capacity = capacity;
	}

	public Integer getCapacity()
	{
		return capacity;
	}

	public String getCode()
	{
		return code;
	}

	@Override
	public String toString()
	{

		return this.code + "(" + this.capacity + ")";
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return (sourceNodeId == targetNodeId);
	}

}