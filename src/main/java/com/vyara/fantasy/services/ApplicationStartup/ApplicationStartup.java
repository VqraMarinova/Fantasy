package com.vyara.fantasy.services.ApplicationStartup;

import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.services.QuoteService;
import com.vyara.fantasy.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Transactional
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    private final QuoteService quoteService;
    private final UserService userService;



    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @SneakyThrows
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        //for presentational purpose

      if (this.quoteService.getAllQuotes().size() == 0){
          QuoteCreateEditServiceModel q1 = new QuoteCreateEditServiceModel("Terry Pratchett","The trouble with having an open mind, of course, is that people will insist on coming along and trying to put things in it.");
          QuoteCreateEditServiceModel q2 = new QuoteCreateEditServiceModel("J.R.R. Tolkien","It's the job that's never started as takes longest to finish.");
          QuoteCreateEditServiceModel q3 = new QuoteCreateEditServiceModel("Brandon Sanderson","The most important words a man can say are : I will do better.");
          QuoteCreateEditServiceModel q4 = new QuoteCreateEditServiceModel("Robert Jordan","Always plan for the worst, child, that way all your surprises are pleasant ones.");
          this.quoteService.addNewQuote(q1);
          this.quoteService.addNewQuote(q2);
          this.quoteService.addNewQuote(q3);
          this.quoteService.addNewQuote(q4);

          this.quoteService.chooseUpQuoteOfTheHour();
      }

      // for easier testing
        /*
      if (this.userService.getUsersCount() == 0){
          UserRegisterServiceModel user = new UserRegisterServiceModel("test", "test@test.com", "test", "test");
          this.userService.register(user);
      }

         */

    }
}
