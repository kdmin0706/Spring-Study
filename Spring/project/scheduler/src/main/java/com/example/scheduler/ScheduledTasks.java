package com.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
  private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
  private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(fixedDelay = 1000)   //작업이 끝나는 시점부터 1초 이후에 수행
  public void reportCurrentTime() throws InterruptedException {
    logger.info("test1: {}", dataFormat.format(new Date()));
    Thread.sleep(10000);        //10초 대기
  }

  @Scheduled(fixedDelay = 1000)   //작업이 끝나는 시점부터 1초 이후에 수행
  public void reportCurrentTime2() {
    logger.info("test2: {}", dataFormat.format(new Date()));
  }

}
