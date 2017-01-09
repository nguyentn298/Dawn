package com.dante.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dante.config.ProfileType;
import com.dante.db.entity.CardDetail;
import com.dante.db.repository.CardDetailRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestApplicationContext.class })
@Transactional
@Rollback
@Profile(ProfileType.TEST)
public class ClientRepositoryTest {
	
	@Autowired
	private CardDetailRepository cardDetailRepository;
	
	@Test
	public void findByProductId() {
		System.out.println("Begin:");
		CardDetail cardDetail = cardDetailRepository.findByClientId(1);
		if(null != cardDetail) {
			System.out.println("client name: " + cardDetail.getClient().getName());
			System.out.println("client name: " + cardDetail.getClient().getMoney());
		} else {
			System.out.println("dkm");
		}
		System.out.println("End");
		
	}
	
	@Ignore("Not Ready to Run") 
	@Test
	public void findByProductId2() {
		System.out.println("Begin22:");
		CardDetail cardDetail = cardDetailRepository.findByClientId(2);
		if(null != cardDetail) {
			System.out.println("client name22: " + cardDetail.getClient().getName());
			System.out.println("client name22: " + cardDetail.getClient().getMoney());
		} else {
			System.out.println("dkm22");
		}
		System.out.println("End22");
		
	}
	
	@Test(timeout = 1000) 
	public void findByProductId3() {
		System.out.println("Begin33:");
		CardDetail cardDetail = cardDetailRepository.findByClientId(1);
		if(null != cardDetail) {
			System.out.println("client name33: " + cardDetail.getClient().getName());
			System.out.println("client name33: " + cardDetail.getClient().getMoney());
		} else {
			System.out.println("dkm33");
		}
		System.out.println("End33");
		
	}
	
}
