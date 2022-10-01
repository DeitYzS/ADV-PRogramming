package se233.chapter2.view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import se233.chapter2.model.Currency;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CurrencyParentPane extends FlowPane {
    public  CurrencyParentPane(ArrayList<Currency> currencylist) throws ExecutionException , InterruptedException {
        this.setPadding(new Insets(0));
        refreshPane(currencylist);
    }

    public  void refreshPane(ArrayList<Currency> currencyList) throws ExecutionException , InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        this.getChildren().clear();
        for (int i=0; i<currencyList.size();i++) {
            FutureTask<CurrencyPane> futureTask = new FutureTask<>(new CurrencyPane(currencyList.get(i)));
            es.submit(futureTask);
            this.getChildren().add(futureTask.get());
        }
    }
}
