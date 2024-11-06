package dev.jlkeesh.module9.scheduler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SimpleScheduler {


//    @Scheduled(initialDelay = 5, fixedRate = 3, timeUnit = TimeUnit.SECONDS)
    @Scheduled(initialDelay = 1, fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    @SneakyThrows
    public void emitEveryThreeSeconds() {
        log.info("Begin");
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println("SimpleScheduler emitEveryThreeSeconds-" + LocalTime.now());
    }

    @Scheduled(cron = "0 10 9 * JAN-MAR MON")
    @Scheduled(cron = "0 10-59/3 * * * Sun")
    @SneakyThrows
    public void everyDayAt9oclock() {
        System.out.println( LocalTime.now());
    }

}
