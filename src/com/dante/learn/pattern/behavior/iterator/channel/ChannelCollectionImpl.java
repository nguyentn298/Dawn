package com.dante.learn.pattern.behavior.iterator.channel;

import java.util.ArrayList;
import java.util.List;

public class ChannelCollectionImpl implements ChannelCollection {

	private List<Channel> listChannel;
	
	public ChannelCollectionImpl() {
		listChannel = new ArrayList<>();
	}
	
	public List<Channel> getListChannel() {
		return listChannel;
	}
	
	@Override
	public void addChannel(Channel c) {
		listChannel.add(c);
		
	}

	@Override
	public void removeChannel(Channel c) {
		listChannel.remove(c);
	}

	@Override
	public ChannelIterator iterator(ChannelTypeEnum type) {
		return new ChannelIteratorImpl(type, listChannel);
	}
	
	private class ChannelIteratorImpl implements ChannelIterator {
		private int index;
		private ChannelTypeEnum typeIterator;
		private List<Channel> channels;
		
		public ChannelIteratorImpl(ChannelTypeEnum type, List<Channel> listChannel) {
			typeIterator = type;
			channels = listChannel;
		}

		@Override
		public boolean hasNext() {
			while(index < channels.size()) {
				Channel c = channels.get(index);
				
				// Argument is typeIterator and channels (list)
				if(typeIterator.equals(c.getType()) || typeIterator.equals(ChannelTypeEnum.ALL)) {
					return true;
				} else 
					index++;
			}
			return false;
		}

		@Override
		public Channel next() {
			Channel c = listChannel.get(index);
			index++;
			return c;
		}

		@Override
		public void remove() {
			
			// use index-- to get current element because next() above have index++ (get the next element)
			channels.remove(index--);
			
			// If don't have index++, the next element will be got by previous element.
			index++;
		}
	}

}
