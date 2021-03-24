import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    private IStockmarketService stockmarketService;

    @InjectMocks
    private StocksPortfolio portfolio;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalValue() {
        portfolio.addStock( new Stock("EDP", 10) );
        portfolio.addStock( new Stock("TAP", 5) );


        when(stockmarketService.getPrice("EDP")).thenReturn(15.0);
        when(stockmarketService.getPrice("TAP")).thenReturn(5.0);

        double total = 15*10 + 5*5;
        //assertEquals(portfolio.getTotalValue(), total);
        assertThat(portfolio.getTotalValue(), is(total));

        verify(stockmarketService, times(2)).getPrice(anyString());
    }
}