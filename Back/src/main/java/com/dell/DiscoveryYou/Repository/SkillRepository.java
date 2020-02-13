package com.dell.DiscoveryYou.Repository;

import com.dell.DiscoveryYou.Entity.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

    /**
     * Finds skill by its ID.
     *
     * @param id ID of user to find.
     * @return skill if found, null otherwise.
     */
    Optional<Skill> findById(Long id);

    /**
     * Finds skill by its name, case-sensitive.
     *
     * @param name Name of user to find.
     * @return skill if found, null otherwise.
     */
    Optional<Skill> findByName(String name);

    List<Skill> findAll();
}
