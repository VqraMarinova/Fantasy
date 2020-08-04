package com.vyara.fantasy.services.scheduleJobs;

import com.vyara.fantasy.services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional
@EnableAsync
public class QuoteOfTheDayScheduledJob implements ScheduledJob{
    private final QuoteService quoteService;

    @Override
    @Async
    @Scheduled(cron = "0 0 * * * ?")
    public void scheduledJob() {
        this.quoteService.chooseUpQuoteOfTheHour();

    }
}
