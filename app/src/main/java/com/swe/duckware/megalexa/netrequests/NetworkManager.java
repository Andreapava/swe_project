package com.swe.duckware.megalexa.netrequests;

public class NetworkManager {

    private static final String HTTP_BASE_ADDRESS = "url_api_aws";

    private int timeout;

    public NetworkManager(int timeout) {
        this.timeout = timeout;
    }

    //TODO
    public void loginRequest() {}

    //TODO
    public void registrationRequest() {}

    //TODO
    public void checkConnection() {}

    public int getTimeout() {
        return timeout;
    }
}
