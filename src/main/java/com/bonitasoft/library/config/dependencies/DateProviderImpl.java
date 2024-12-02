package com.bonitasoft.library.config.dependencies;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateProviderImpl implements DateProvider {

  public int getCurrentYear() {
    return LocalDate.now().getYear();
  }

}
