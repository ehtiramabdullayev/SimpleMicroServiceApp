package testbean;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class PortfolioTester {
    Portfolio portfolio;
    StockService stockService;


    public static void main(String[] args){
        PortfolioTester tester = new PortfolioTester();
        tester.setUp();
        System.out.println(tester.testMarketValue()?"pass":"fail");
    }

    public void setUp(){
        portfolio = new Portfolio();

        stockService = mock(StockService.class);

        portfolio.setStockService(stockService);
    }

    public boolean testMarketValue(){

        List<Stock> stocks = new ArrayList<>();
        Stock googleStock = new Stock("1","Google", 10);
        Stock microsoftStock = new Stock("2","Microsoft",100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        portfolio.setStocks(stocks);

        System.out.println(stockService.getPrice(googleStock));
        System.out.println(stockService.getPrice(microsoftStock));

        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        System.out.println(stockService.getPrice(googleStock));
        System.out.println(stockService.getPrice(microsoftStock));

        double marketValue = portfolio.getMarketValue();
        return marketValue == 100500.0;
    }
}
