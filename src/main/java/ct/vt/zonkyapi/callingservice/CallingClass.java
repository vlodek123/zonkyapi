package ct.vt.zonkyapi.callingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import ct.vt.zonkyapi.entity.serviceentity.ServiceLoan;
import ct.vt.zonkyapi.entity.zonkyresponseentity.ZonkyResponseLoan;
import ct.vt.zonkyapi.exception.ZonkyErrorResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CallingClass {

    private static final Logger log = LoggerFactory.getLogger(CallingClass.class);

    private URL url;
    private HttpURLConnection conn;
    private String bearer = "Bearer 69364ffb-4fd8-4468-8f93-557277330914";
    private InputStreamReader in = null;
    private BufferedReader br = null;


    public List<ServiceLoan>  getZonkyLoans() throws Exception {

            url = new URL("https://api.zonky.cz/loans/marketplace");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", bearer);
            conn.setRequestMethod("GET");
            ZonkyResponseLoan[] zonkyResponse;

        int status = conn.getResponseCode();
            log.info("Http_SC: " + status );

            if (status > 299) {
                in = new InputStreamReader(conn.getErrorStream());
                String content = getContent(in).toString().replace("\"","");
                throw new ZonkyErrorResponseException(content);
            } else {

                in = new InputStreamReader(conn.getInputStream());
                String content = getContent(in).toString();

                ObjectMapper om = new ObjectMapper();
                zonkyResponse = om.readValue(content, ZonkyResponseLoan[].class);

            }
        List<ServiceLoan> loanList = new ArrayList<>();
        for (ZonkyResponseLoan item: zonkyResponse
             ) {
            loanList.add(new ServiceLoan(item.getId(), item.getInterestRate(), item.getAmount()));
        }

        return loanList;
    }

    public StringBuffer getContent(InputStreamReader in) throws Exception{

        br = new BufferedReader(in);
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        br.close();
        in.close();
        conn.disconnect();

        //log.info("Response: " + content);
        return content;
    }

}
