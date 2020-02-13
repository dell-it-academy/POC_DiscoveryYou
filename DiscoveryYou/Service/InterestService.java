package com.dell.DiscoveryYou.Service;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.User;
import com.dell.DiscoveryYou.Repository.InterestRepository;
import com.dell.DiscoveryYou.Repository.UserRepository;
import com.dell.DiscoveryYou.Request.CreateInterestDetailsRequestModel;
import com.dell.DiscoveryYou.Util.ReturnMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;
    Map<String, Interest> interests;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public InterestService(InterestRepository interestrepository) {
        this.interests = new HashMap<String, Interest>();
        this.interestRepository = interestrepository;
    }

    public String createInterest(String name) {
        if (name != null) {
            if (name.length() <= 3) {
                interestRepository.save(new Interest(name));
                return ReturnMessages.CREATED;
            }
            return ReturnMessages.LENGTH_ERROR;
        }
        return ReturnMessages.NULL;
    }

    public List<Interest> findAll(){
        return this.interestRepository.findAll();
    }

    public Interest getInterestByName(String name) {
        return this.interestRepository.findByName(name).orElse(null);
    }

    public List<Interest> getInterestsByUser(String id) {
        if (id != null) {
            //return interestRepository.findByid(id);
        }
        return new ArrayList<>();
    }

    public Interest createInterest(CreateInterestDetailsRequestModel interest) {
        Interest returnValue = interests.get(interest.getName());

        if(returnValue == null)
            returnValue = this.interestRepository.findByName(interest.getName()).orElse(null);
        else
            return returnValue;

        if (returnValue != null)
            return returnValue;

        returnValue = new Interest();
        returnValue.setName(interest.getName());
        returnValue.setUsers(interest.getUsers());

        interestRepository.save(returnValue);
        interests.put(returnValue.getName(), returnValue);
        return returnValue;
    }

    public boolean deleteInterestFromUser(Long interestId, Long userId) {
        if(interestId != null && userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Interest> interest = interestRepository.findById(interestId);
            if(userOptional.isPresent() && interest.isPresent()){
                User user = userOptional.get();
                user.removeInterest(interest.get());
                userRepository.save(user);
                interestRepository.save(interest.get());
                return true;
            }
        }
        return false;
    }
}
