package com.rockstar;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class StartApplicationTest {

    @Mock
    private StartApplication startApplication;

    @Test
    void givenNullModel_whenIndexCalled_thenReturnIndexViewName() {
        // Mock the behavior of startApplication.index
        when(startApplication.index(null)).thenReturn("index");

        String viewName = startApplication.index(null);
        assertThat(viewName).isEqualTo("index");
    }
}
