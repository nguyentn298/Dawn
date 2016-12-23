package com.dante.db.entity;

import java.util.Comparator;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class ComparatorCustom implements Comparator<Client>{

	@Override
	public int compare(Client c1, Client c2) {
		// TODO Auto-generated method stub
		return new CompareToBuilder()
				.append(c1.getName(), c2.getName())
				.append(c1.getAge(), c2.getAge())
				.toComparison();
//        .append(c1.entityType, c2.entityType)
//        .append(c1.brandId, c2.brandId)
//        .append(c1.productId, c2.productId)
//        .toComparison();
	}

}
