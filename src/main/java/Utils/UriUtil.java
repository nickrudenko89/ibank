package Utils;

import javax.servlet.http.HttpServletRequest;

public class UriUtil {
    public static String editUri(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        String editedQueryString = "";
        if (queryString != null) {
            String[] queryStringArray = queryString.split("&");
            for (int counter = 0; counter < queryStringArray.length; counter++) {
                if (!"error".equals(queryStringArray[counter].split("=")[0])) {
                    if (counter == 0)
                        editedQueryString += "?" + queryStringArray[counter];
                    else
                        editedQueryString += "&" + queryStringArray[counter];
                }
            }
            requestUri += editedQueryString;
        }
        return requestUri;
    }
}
