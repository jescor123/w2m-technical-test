package com.w2m.w2m_technical_test.controller;

import com.w2m.w2m_technical_test.exception.NegativeIdException;
import com.w2m.w2m_technical_test.exception.SpaceShipInternalServerErrorException;
import com.w2m.w2m_technical_test.exception.SpaceShipNotContentException;
import com.w2m.w2m_technical_test.exception.SpaceShipNotFoundException;
import com.w2m.w2m_technical_test.model.SpaceShip;
import com.w2m.w2m_technical_test.service.SpaceShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpaceShipController {
    @Autowired
    SpaceShipService spaceShipService;
    @GetMapping(value = "/spaceships", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllSpaceShips(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "3") int size) {
        try {

            Pageable paging = PageRequest.of(page, size);
            Page<SpaceShip> pageableResult = spaceShipService.findAll(paging);
            List<SpaceShip> spaceShips = pageableResult.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("spaceShips", spaceShips);
            response.put("currentPage", pageableResult.getNumber());
            response.put("totalItems", pageableResult.getTotalElements());
            response.put("totalPages", pageableResult.getTotalPages());

            if (response != null && !response.isEmpty()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new SpaceShipNotContentException();
            }

        } catch (Exception e) {
            throw new SpaceShipInternalServerErrorException();
        }
   }

    @GetMapping(value = "/spaceships-search-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpaceShip>> getAllSpaceShipsByName(@RequestParam(required = false) String name) {
        try {

            List<SpaceShip> response = spaceShipService.findSpaceShipListByName(name);

            if (response != null && !response.isEmpty()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new SpaceShipNotContentException();
            }

        } catch (Exception e) {
            throw new SpaceShipInternalServerErrorException();
        }
    }

    @Cacheable(value = "spaceShips", key = "#id", condition = "#id == '2'")
    @GetMapping(value = "/one-spaceship", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpaceShip> getSpaceShipById(@RequestParam(required = true) String id) {

        if (Long.valueOf(id) < 0) {
            throw new NegativeIdException();
        }

        Optional<SpaceShip> spaceShip = spaceShipService.findSpaceShipById(Long.valueOf(id));
        if (spaceShip.isPresent()) {
            return new ResponseEntity<>(spaceShip.get(), HttpStatus.OK);
        } else {
            throw new SpaceShipNotFoundException();
        }

    }

    @PostMapping(value = "/create-spaceship", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpaceShip> createSpaceShip(@RequestBody SpaceShip request) {
        try {
            SpaceShip spaceShip = spaceShipService.save(new SpaceShip(request.getName()));
            return new ResponseEntity<>(spaceShip, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new SpaceShipInternalServerErrorException();
        }
    }

    @PutMapping(value = "/update-spaceship", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpaceShip> updateSpaceShip(@RequestParam(required = true) long id, @RequestBody SpaceShip request) {
        Optional<SpaceShip> spaceShip = spaceShipService.findSpaceShipById(id);

        if (spaceShip.isPresent()) {
            SpaceShip updatedSpaceShip = spaceShip.get();
            updatedSpaceShip.setName(request.getName());
            return new ResponseEntity<>(spaceShipService.save(updatedSpaceShip), HttpStatus.OK);
        } else {
            throw new SpaceShipNotFoundException();
        }

    }

    @DeleteMapping(value = "/delete-spaceship", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteSpaceShip(@RequestParam(required = true) long id) {
        try {
            spaceShipService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new SpaceShipInternalServerErrorException();
        }
    }

}
