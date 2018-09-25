/*
 *jiji java
 */
package com.demo.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;


public class TokenWebAuthenticationDetails extends WebAuthenticationDetails implements TokenAuthenticationDetails {
    private static final long serialVersionUID = 1L;
    private String token;
    private String secret;
    private String sessid;
    private String logid;
    private String domain;

    public TokenWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.token = StringUtils.trimToNull(request.getParameter("token"));
        this.secret = StringUtils.trimToNull(request.getParameter("secret"));
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public String getSecret() {
        return this.secret;
    }

    @Override
    public String getSessionId() {
        return this.sessid != null ? this.sessid : super.getSessionId();
    }

    void setSessionId(String sessionId) {
        this.sessid = sessionId;
    }

    @Override
    public String getLogId() {
        return this.logid;
    }

    void setLogId(String logId) {
        this.logid = logId;
    }

    @Override
    public String getDomain() {
        return this.domain;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if ((obj instanceof TokenWebAuthenticationDetails)) {
            TokenWebAuthenticationDetails rhs = (TokenWebAuthenticationDetails) obj;
            if ((StringUtils.equals(this.token, rhs.token)) && (StringUtils.equals(this.secret, rhs.secret))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int code = super.hashCode();
        if (this.token != null) {
            code *= this.token.hashCode() % 7;
        }
        if (this.secret != null) {
            code *= this.secret.hashCode() % 7;
        }
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Token: ").append(getToken()).append("; ");
        sb.append("; Secret: ").append(getSecret());
        return sb.toString();
    }

    @Override
    public String getRemoteType() {
        return "WEB";
    }

    @Override
    public int getAuthPhase() {
        return 1;
    }
}
