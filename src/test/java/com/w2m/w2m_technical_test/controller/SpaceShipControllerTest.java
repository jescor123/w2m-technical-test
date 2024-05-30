package com.w2m.w2m_technical_test.controller;

import com.w2m.w2m_technical_test.model.SpaceShip;
import com.w2m.w2m_technical_test.service.SpaceShipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SpaceShipControllerTest {
    @InjectMocks
    SpaceShipController spaceShipController;
    @Mock
    private SpaceShipService spaceShipService;
    List<SpaceShip> spaceShipList = new ArrayList<>();


    @BeforeEach
    void setUp() {

        SpaceShip spaceShip1 = new SpaceShip();
        spaceShip1.setId(1L);
        spaceShip1.setName("X-wing");

        SpaceShip spaceShip2 = new SpaceShip();
        spaceShip2.setId(2L);
        spaceShip2.setName("Apolo-11");

        SpaceShip spaceShip3 = new SpaceShip();
        spaceShip3.setId(3L);
        spaceShip3.setName("Millenium-Falcon");

        SpaceShip spaceShip4 = new SpaceShip();
        spaceShip4.setId(4L);
        spaceShip4.setName("Star-Treck");

        spaceShipList.add(spaceShip1);
        spaceShipList.add(spaceShip2);
        spaceShipList.add(spaceShip3);
        spaceShipList.add(spaceShip4);

    }
    @Test
    void testGetAllSpaceShipsByName_When_Request_Is_Ok() {

        Mockito.when(spaceShipService.findSpaceShipListByName("wing")).thenReturn(spaceShipList);
        ResponseEntity<List<SpaceShip>> response = spaceShipController.getAllSpaceShipsByName("wing");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);

    }

    @Test
    void testGetSpaceShipById_When_Request_Is_Ok() {

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(2L);
        spaceShip.setName("Apolo-11");

        Mockito.when(spaceShipService.findSpaceShipById(2L)).thenReturn(Optional.of(spaceShip));
        ResponseEntity<SpaceShip> response = spaceShipController.getSpaceShipById("2");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(spaceShip.getId());
        assertThat(response.getBody().getName()).isEqualTo(spaceShip.getName());

    }


}