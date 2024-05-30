package com.w2m.w2m_technical_test.service.impl;

import com.w2m.w2m_technical_test.model.SpaceShip;
import com.w2m.w2m_technical_test.repository.SpaceShipRepository;
import com.w2m.w2m_technical_test.service.SpaceShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceShipServiceImpl implements SpaceShipService {
    @Autowired
    SpaceShipRepository spaceShipRepository;

    @Override
    public Page<SpaceShip> findAll(Pageable pageable) {

        return spaceShipRepository.findAll(pageable);

    }

    @Override
    public Optional<SpaceShip> findSpaceShipById(long id) {

        return spaceShipRepository.findSpaceShipById(id);

    }

    @Override
    public List<SpaceShip> findSpaceShipListByName(String name) {

        return spaceShipRepository.findSpaceShipListByName(name);

    }

    @Override
    public SpaceShip save(SpaceShip s) {

        return spaceShipRepository.save(s);

    }

    @Override
    public void deleteById(long id) {

        spaceShipRepository.deleteById(id);

    }


}
