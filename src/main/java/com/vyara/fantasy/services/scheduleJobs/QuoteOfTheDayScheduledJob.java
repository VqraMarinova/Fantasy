package com.vyara.fantasy.services.scheduleJobs;

import com.vyara.fantasy.services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional
public class QuoteOfTheDayScheduledJob implements ScheduledJob{
    private final QuoteService quoteService;

    @Override
    @Scheduled(cron = "0 1 1 * * ?")
    public void scheduledJob() {
        this.quoteService.chooseUpQuoteOfTheDay();


    }
}
