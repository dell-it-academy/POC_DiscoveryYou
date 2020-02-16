package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Repository.InterestRepository;
import com.dell.DiscoveryYou.Request.CreateInterestDetailsRequestModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
public class InterestService {

    private InterestRepository interestRepository;
    Map<String, Interest> interests;

    public InterestService(InterestRepository interestrepository) {
        this.interests = new HashMap<String, Interest>();
        this.interestRepository = interestrepository;
    }

    public List<Interest> findAll(int startPage, int pageOffset){
        return this.interestRepository.findAll(PageRequest.of(startPage, pageOffset)).getContent();
    }

    public List<Interest> findAll(){
        return this.interestRepository.findAll();
    }

    public Interest getInterestByName(String name) {
        return this.interestRepository.findByName(name).orElse(null);
    }

    @Transactional
    public Interest createInterest(CreateInterestDetailsRequestModel interest) {
        Interest returnValue = interests.get(interest.getName());
        if (returnValue == null)
            returnValue = this.interestRepository.findByName(interest.getName()).orElse(null);
        if (returnValue == null) {
            returnValue = new Interest(interest.getName());
            returnValue.setName(interest.getName());
            interestRepository.save(returnValue);
            interests.put(returnValue.getName(), returnValue);
        }
        return returnValue;
    }

    @Transactional
    public Interest createInterest(String interestName) {
        Interest returnValue = interests.get(interestName);
        if (returnValue == null) {
            returnValue = this.getInterestByName(interestName);
            Optional.ofNullable(returnValue).ifPresent(interest -> interest.setId(null));
        }
        if (returnValue == null)
            returnValue = new Interest(interestName);
        interests.put(returnValue.getName(), returnValue);
        return returnValue;
    }
}
