package sk.lacko.reality_scanner;

import java.util.List;

public interface IPage {

    /**
     * It will process page and returns page to process later ro nothing if it is last page (detail of reality)
     * @return
     */
    List<IPage> process();
}
