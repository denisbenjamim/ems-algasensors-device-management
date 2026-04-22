package com.algaworks.algasensors.device.management;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.algaworks.algasensors.device.management.common.IdGenerator;

import io.hypersistence.tsid.TSID;

public class TSIDTest {

    @BeforeAll
    static void setUpAll() {
        System.setProperty("tsid.node", "2");
        System.setProperty("tsid.node.count", "36");
    }

    @AfterAll
    static void tearDownAll() {
        System.clearProperty("tsid.node");
        System.clearProperty("tsid.node.count");
    }

    @Test
    void shouldGenerateTSID() {
        final TSID tsid = IdGenerator.generateTSID();
        Assertions.assertThat(tsid.getInstant())
            .isCloseTo(Instant.now(), Assertions.within(1, ChronoUnit.MINUTES));
    }
}
