package io.agilehandy.bootuptesting;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *  By Haytham Mohamed
 */

public interface RegistrationRepository extends CrudRepository<Registration, Long> {

    @Query("select o from Registration o where o.name = :name")
    public Registration findByName(@Param("name") String name);
}
