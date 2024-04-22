
import com.winandronux.models.Currency;
import com.winandronux.models.SupportedCodesResponse;
import com.winandronux.utils.GlobalVars;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static final String API_KEY = "581c46c1cd1b5e0aa0c1e15a";

    public static void main(String[] args) {

        System.out.println(Arrays.toString(args));

        GlobalVars.init();

        var mxn = new Currency("MXN", "Mexican Peso");

        try {
            var t = mxn.convert(200, "USD", API_KEY);

            for (var c : t) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
