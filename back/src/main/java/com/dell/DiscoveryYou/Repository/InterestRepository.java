package com.dell.DiscoveryYou.Repository;

import com.dell.DiscoveryYou.Entity.Interest;
import com.dell.DiscoveryYou.Entity.Skill;
import com.dell.DiscoveryYou.Entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterestRepository extends PagingAndSortingRepository<Interest, Long> {

    /**
     * Finds interest by its ID.
     *
     * @param id ID of user to find.
     * @return interest if found, null otherwise.
     */
    Optional<Interest> findById(Long id);

    /**
     * Finds interest by its name, case-sensitive.
     *
     * @param name Name of user to find.
     * @return interest if found, null otherwise.
     */
    Optional<Interest> findByName(String name);

    List<Interest> findAll();
}
