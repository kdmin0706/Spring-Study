package com.example.scheduler;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class SchedulerTest {

  @SpyBean
  ScheduledTasks scheduledTasks;

  @Test
  @DisplayName("2개의 스케쥴러를 사용")
  void reportCurrentTime() {
    await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
      verify(scheduledTasks, atLeast(1)).reportCurrentTime();
      verify(scheduledTasks, atLeast(10)).reportCurrentTime2();
    });
  }
}
