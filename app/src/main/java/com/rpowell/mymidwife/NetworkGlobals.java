/***************************************************************************
 * COPYRIGHT (C) 2015, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/
package com.rpowell.mymidwife;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.Response;

public class NetworkGlobals {

    public static final String SERVER_URL = "http://10.0.0.100:8080";

    private static String m_sessionCookie;

    /**
     * Provides a RestAdapter that doesn't include the a session token.
     * @return A RestAdapter for non-authenticated network calls.
     */
    public static RestAdapter getNonAuthenticatedRestAdapater() {

        return new RestAdapter.Builder()
                .setEndpoint(NetworkGlobals.SERVER_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
    /**
     * Provides a RestAdapter that doesn't include the a session token.
     * @return A RestAdapter for non-authenticated network calls.
     * @throws NoSessionException if the user does not have a session token.
     */
    public static RestAdapter getAuthenticatedRestAdapater()
        throws NoSessionException
    {
        if(m_sessionCookie == null) {
            throw new NoSessionException("The user does not have a session token");
        }

        return new RestAdapter.Builder()
                .setEndpoint(NetworkGlobals.SERVER_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Cookie", "JSESSIONID" + "=" + getSessionCookie());
                    }
                })
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    /**
     * Takes a HTTP response and parses the headers to retrieve the JSESSION cookie token.
     * @param response The received HTTP response.
     * @return The JSESSION cookie token or <code>null</code>.
     */
    public static String parseResponseForSessionToken(Response response) {
        for (Header header : response.getHeaders())
        {
            if(header.getName() != null && header.getName().equals("Set-Cookie")) {
                if (header.getValue().contains("JSESSIONID")) {
                    int index = header.getValue().indexOf("JSESSIONID=");

                    int endIndex = header.getValue().indexOf(";", index);

                    return header.getValue().substring(
                            index + "JSESSIONID=".length(), endIndex);
                }
            }
        }
        return null;
    }


    public static void setSessionCookie(String sessionCookie) {
        m_sessionCookie = sessionCookie;
    }

    public static String getSessionCookie() {
        if(m_sessionCookie != null) {
            return m_sessionCookie + "Path=/";
        }
        return null;
    }

}
