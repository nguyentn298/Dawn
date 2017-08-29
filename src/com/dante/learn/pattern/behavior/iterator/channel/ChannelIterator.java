package com.dante.learn.pattern.behavior.iterator.channel;

public interface ChannelIterator {
	public boolean hasNext();
	public Channel next();
	public void remove();
}
