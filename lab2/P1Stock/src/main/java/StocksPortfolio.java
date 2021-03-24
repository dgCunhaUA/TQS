import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio {

    private IStockmarketService stockmarketService;
    private List<Stock> stocks = new ArrayList<Stock>();

    public StocksPortfolio(IStockmarketService stockmarketService) {
        this.stockmarketService = stockmarketService;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double getTotalValue() {
        double totalValue = 0;

        for(Stock s : this.stocks){
            totalValue += (stockmarketService.getPrice(s.getLabel()) * s.getQuantity());
        }

        return totalValue;
    }
}
