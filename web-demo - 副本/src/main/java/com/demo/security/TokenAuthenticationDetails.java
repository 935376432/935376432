package com.demo.security;

public abstract interface TokenAuthenticationDetails {
    public abstract String getRemoteAddress();

    public abstract String getSessionId();

    public abstract String getToken();

    public abstract String getSecret();

    public abstract String getRemoteType();

    public abstract int getAuthPhase();

    public abstract String getLogId();

    public abstract String getDomain();

    public abstract void setDomain(String paramString);
}