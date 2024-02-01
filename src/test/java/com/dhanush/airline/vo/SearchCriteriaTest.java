package com.dhanush.airline.vo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SearchCriteriaTest {

    @Test
    void givenEntity_thenCreateSearchCriteria() {
        final SearchCriteria searchCriteria = createSearchCriteria();

        assertTrue(searchCriteria.toString().contains("Search"));
    }
    private static SearchCriteria createSearchCriteria() {
        SearchCriteria searchCriteria = new SearchCriteria("Origin", "Destination", new Date(), 2);

        searchCriteria.setCount(2);
        searchCriteria.setDestination("Destination");
        searchCriteria.setOrigin("Origin");
        searchCriteria.setFlightDate(new Date());

        return searchCriteria;
    }

}