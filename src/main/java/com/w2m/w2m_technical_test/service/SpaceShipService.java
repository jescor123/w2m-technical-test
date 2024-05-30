package com.w2m.w2m_technical_test.service;

import com.w2m.w2m_technical_test.model.SpaceShip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SpaceShipService {
    Page<SpaceShip> findAll(Pageable pageable);
    Optional<SpaceShip> findSpaceShipById(long id);
    List<SpaceShip> findSpaceShipListByName(String name);
    SpaceShip save(SpaceShip s);
    void deleteById(long id);

}
