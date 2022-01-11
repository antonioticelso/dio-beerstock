package com.github.antoniocelso.beerstock.service;

import com.github.antoniocelso.beerstock.builder.BeerDTOBuilder;
import com.github.antoniocelso.beerstock.dto.BeerDTO;
import com.github.antoniocelso.beerstock.entity.Beer;
import com.github.antoniocelso.beerstock.exception.BeerAlreadyRegisteredException;
import com.github.antoniocelso.beerstock.mapper.BeerMapper;
import com.github.antoniocelso.beerstock.repository.BeerRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void whenBeerInformedThenItShoulBeCreated() throws BeerAlreadyRegisteredException {
        BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedSaveBeer = beerMapper.toModel(expectedBeerDTO);

        Mockito.when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
        Mockito.when(beerRepository.save(expectedSaveBeer)).thenReturn(expectedSaveBeer);

        BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);

        MatcherAssert.assertThat(createdBeerDTO.getId(), Matchers.is(Matchers.equalTo(expectedBeerDTO.getId())));
        MatcherAssert.assertThat(createdBeerDTO.getName(), Matchers.is(Matchers.equalTo(expectedBeerDTO.getName())));
        MatcherAssert.assertThat(createdBeerDTO.getQuantity(), Matchers.is(Matchers.equalTo(expectedBeerDTO.getQuantity())));

//        Assertions.assertEquals(expectedBeerDTO.getId(), createdBeerDTO.getId());
    }

}
