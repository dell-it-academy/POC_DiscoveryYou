package com.dell.DiscoveryYou.Repository;

import com.dell.DiscoveryYou.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {


    /**
     * Finds user by its ID.
     *
     * @param id ID of user to find.
     * @return User if found, null otherwise.
     */
    public Optional<User> findById(Long id);

    /**
     * Finds user by its BADGE.
     *
     * @param badge of user to find.
     * @return User if found, null otherwise.
     */
    public Optional<User> findByBadge(String badge);

    /**
     * Finds user by its name, case-sensitive.
     *
     * @param name Name of user to find.
     * @return User if found, null otherwise.
     */
    public User findByFirstName(String name);

    public Iterable<User> findAllByFirstName(String name);

    /**
     * Finds users whose names contain searchName as substring.
     *
     * @param searchName Name (substring) to search for.
     * @return List of users.
     */
    public List<User> findByFirstNameIgnoreCaseContaining(String searchName);

    List<User> findAll();
}
