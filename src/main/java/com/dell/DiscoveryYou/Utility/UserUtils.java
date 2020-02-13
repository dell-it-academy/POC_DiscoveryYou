package com.dell.DiscoveryYou.Utility;

import com.dell.DiscoveryYou.Entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUtils {

    public UserUtils() {}

    public String generateid(){
        return UUID.randomUUID().toString();
    }
    public int calculateMatchingPercentage(User user, User relUser) {
        return 0;
    }
}
