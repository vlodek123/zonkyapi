package ct.vt.zonkyapi.callingservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import ct.vt.zonkyapi.entity.serviceentity.ServiceLoan;
import ct.vt.zonkyapi.entity.zonkyresponseentity.ZonkyResponseLoan;
import ct.vt.zonkyapi.exception.ZonkyErrorResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallingClass {

    private static final Logger log = LoggerFactory.getLogger(CallingClass.class);

    private URL url;
    private HttpURLConnection conn;
    private String bearer;
    private InputStreamReader in = null;
    private BufferedReader br = null;


    public List<ServiceLoan>  getZonkyLoans() throws Exception {

            getToken();

            url = new URL("https://api.zonky.cz/loans/marketplace");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", this.bearer);
            conn.setRequestMethod("GET");
            ZonkyResponseLoan[] zonkyResponse;

        int status = conn.getResponseCode();
            log.info("Get Zonky loans Http_SC: " + status );

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

    public List<ServiceLoan>  getZonkyLoans(String bearer) throws Exception {

        url = new URL("https://api.zonky.cz/loans/marketplace");
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", bearer);
        conn.setRequestMethod("GET");
        ZonkyResponseLoan[] zonkyResponse;

        int status = conn.getResponseCode();
        log.info("Get Zonky loans Http_SC: " + status );

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

    public void getToken() throws Exception {
        String scope = "SCOPE_APP_BASIC_INFO SCOPE_INVESTMENT_READ SCOPE_INVESTMENT_WRITE SCOPE_NOTIFICATIONS_READ SCOPE_NOTIFICATIONS_WRITE";
        String state = "123456789";
        String clientId = "mujrobot";
        String clientSecret = "mujrobot";
        String responseType = "code";
        String redirectUri = "https://app.zonky.cz/api/oauth/code";
        String authUrl = "https://api.zonky.cz/oauth/authorize";
        String accessTokenUrl = "https://api.zonky.cz/oauth/token";

        url = new URL(authUrl);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("response_type", responseType);
        parameters.put("client_id", clientId);
        parameters.put("state", state);
        parameters.put("scope", scope);
        parameters.put("redirect_uri", redirectUri);

        conn.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = conn.getResponseCode();
        log.info("OAuth call Http_SC: " + status );

        String returnedRedirectUrl = conn.getHeaderFields().toString();
        log.info(returnedRedirectUrl);

        String content;

        if (status > 299) {
            in = new InputStreamReader(conn.getErrorStream());
            content = getContent(in).toString().replace("\"","");
            throw new ZonkyErrorResponseException(content);
        } else {

            in = new InputStreamReader(conn.getInputStream());
            content = getContent(in).toString();

        }
        log.info(content); //returns html page

        setBearer("Bearer 864449ae-a854-42b7-ba73-d49cdabd4dc6"); //hardcoded
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
