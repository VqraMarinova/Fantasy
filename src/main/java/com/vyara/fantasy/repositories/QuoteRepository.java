package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.SmartQuote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<SmartQuote, Long> {

}
