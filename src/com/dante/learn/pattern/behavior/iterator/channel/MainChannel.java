package com.dante.learn.pattern.behavior.iterator.channel;

import java.util.ArrayList;
import java.util.List;

public class MainChannel {
	public static void main(String[] args) {
		// ASSASSIN, MARK_MEN, TANK, MAGE;
		ChannelCollection collection = myListChannel();
		ChannelIterator iterator = collection.iterator(ChannelTypeEnum.TANK);
		while (iterator.hasNext()) {
			Channel c = iterator.next();
			
			if(c.getHero().equalsIgnoreCase("Jarvan")) {
				iterator.remove();
			} else {
				System.out.println(c);
			}
		}
	}

	public static ChannelCollection myListChannel() {

		ChannelCollection collection = new ChannelCollectionImpl();
		collection.addChannel(new Channel("Zed", ChannelTypeEnum.ASSASSIN));
		collection.addChannel(new Channel("Garen", ChannelTypeEnum.TANK));
		collection.addChannel(new Channel("Darius", ChannelTypeEnum.TANK));
		collection.addChannel(new Channel("Jarvan", ChannelTypeEnum.TANK));

		collection.addChannel(new Channel("Lucian", ChannelTypeEnum.MARK_MEN));
		collection.addChannel(new Channel("Ahri", ChannelTypeEnum.MAGE));
		collection.addChannel(new Channel("Tristana", ChannelTypeEnum.MARK_MEN));
		collection.addChannel(new Channel("Ezreal", ChannelTypeEnum.MARK_MEN));

		collection.addChannel(new Channel("Kalista", ChannelTypeEnum.MARK_MEN));
		collection.addChannel(new Channel("Mundo", ChannelTypeEnum.TANK));
		collection.addChannel(new Channel("Shen", ChannelTypeEnum.TANK));
		collection.addChannel(new Channel("Talon", ChannelTypeEnum.ASSASSIN));

		return collection;
	}
}
