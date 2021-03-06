package sk.lacko.reality_scanner;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Component
public class PageRunner {

    Queue<IPage> pagesQueue = new PriorityQueue<>();

    public void runSearch(IPage mainPage){
        try {
            List<IPage> pagesToProcessLater = mainPage.process();
            pagesQueue.addAll(pagesToProcessLater);

            for (IPage subPage = pagesQueue.poll(); subPage != null; subPage = pagesQueue.poll()) {
                try{

                pagesQueue.addAll(subPage.process());

                }catch(IOException e){

                }

            }
        }
        catch(Throwable e){

        }
    }
}
