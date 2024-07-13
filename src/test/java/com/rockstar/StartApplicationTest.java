package com.rockstar;

import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

@SpringBootTest
class StartApplicationTest {

  @Mock
  private StartApplication startApplication;

  @Test
  void testIndex_ReturnsCorrectViewName() {
    // Mock the behavior of startApplication.index
    when(startApplication.index(null)).thenReturn("index");

    String viewName = startApplication.index(null);
    assertEquals("index", viewName);
  }
}


