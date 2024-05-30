package com.w2m.w2m_technical_test.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class SpaceShipTest {
    public SpaceShip spaceShip;
    public static final long idTest = 2L;
    public static final String MILLENIUM_FALCON = "Mlillenium Falcon";

    @BeforeEach
    void setUp() {

        spaceShip = new SpaceShip();
        spaceShip.setId(1L);
        spaceShip.setName("X-wing");

    }

    @Test
    void testGetId() {

        assertThat(spaceShip.getId()).isEqualTo(1L);

    }

    @Test
    void testSetId() {

        spaceShip.setId(2L);
        assertThat(spaceShip.getId()).isEqualTo(idTest);

    }

    @Test
    void testGetName() {

        assertThat(spaceShip.getName()).isEqualTo("X-wing");

    }

    @Test
    void testSetName() {

        spaceShip.setName("Mlillenium Falcon");
        assertThat(spaceShip.getName()).isEqualTo(MILLENIUM_FALCON);

    }

}