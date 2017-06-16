package client;

import stockexchange.client.StockCommandType;
import stockexchange.client.StockExchangeClient;
import stockexchange.client.StockExchangeClientFactory;
import stockexchange.client.StockExchangeCommand;

public class Main {
	private StockExchangeClient client;
	
	
	public Main(StockExchangeClient client) {
		super();
		this.client = client;
	}


	public static void main(String[] args) throws Exception {
		StockExchangeClient client = StockExchangeClientFactory.getClient();
		new Main(client).execute();
		
	}


	public void execute() throws Exception{
		String myId = "client1";
		client.startListening(myId, (o, event)->System.err.println(event));
		
		Long askId = client.sendCommand(new StockExchangeCommand(StockCommandType.ASK, myId, "GOOG", 15.0, 15.0, 10));
		client.sendCommand(new StockExchangeCommand(StockCommandType.BID, myId, "GOOG", 15.0, 15.0, 5));
		client.sendCommand(new StockExchangeCommand(StockCommandType.BID, myId, "GOOG", 24.0, 24.0, 5));
		
		
		Thread.sleep(5000);
		client.getTransactionsForCommand(askId).forEach(t->System.err.println("\t" + t));
		
		System.err.println("tickers:");
		client.getStocksId()
			.stream()
			.map(id->client.getQuote(id))
			.forEach(s->System.err.println(s));
		
		System.err.println("GOOG last transactions:");
		client.getTransactionsByStockId("GOOG", 2)
			.forEach(t->System.err.println("\t" + t));
		
		System.err.println("GOOG last commands:");
		client.getCommandsByStockId("GOOG", 100)
			.forEach(c->System.err.println("\t" + c));

		client.close();
	}

}
