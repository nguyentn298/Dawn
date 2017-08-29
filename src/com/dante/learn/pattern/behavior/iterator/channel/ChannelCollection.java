package com.dante.learn.pattern.behavior.iterator.channel;

import java.util.List;

public interface ChannelCollection {
	public void addChannel(Channel c);
	public void removeChannel(Channel c);
	public ChannelIterator iterator(ChannelTypeEnum type);
	public List<Channel> getListChannel();
}
