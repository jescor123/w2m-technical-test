package com.w2m.w2m_technical_test.repository;

import com.w2m.w2m_technical_test.model.SpaceShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShip, Long> {
    Page<SpaceShip> findAll(Pageable pageable);
    Optional<SpaceShip> findSpaceShipById(long id);
    @Query("SELECT s FROM SpaceShip s WHERE s.name LIKE %:name%")
    List<SpaceShip> findSpaceShipListByName(@Param("name") String name);

}
