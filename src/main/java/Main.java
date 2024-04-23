
import com.winandronux.App.App;
import com.winandronux.utils.GlobalVars;


public class Main {

    static final String API_KEY = "581c46c1cd1b5e0aa0c1e15a";

    public static void main(String[] args) {

        System.out.println("Loading...");
        GlobalVars.init(API_KEY);
        System.out.println("Ready!!!");

        App.init();
    }
}
