package p008cz.msebera.android.httpclient.impl.client;

import p008cz.msebera.android.httpclient.params.AbstractHttpParams;
import p008cz.msebera.android.httpclient.params.HttpParams;
import p008cz.msebera.android.httpclient.util.Args;

@Deprecated
/* renamed from: cz.msebera.android.httpclient.impl.client.ClientParamsStack */
public class ClientParamsStack extends AbstractHttpParams {
    protected final HttpParams applicationParams;
    protected final HttpParams clientParams;
    protected final HttpParams overrideParams;
    protected final HttpParams requestParams;

    public ClientParamsStack(HttpParams aparams, HttpParams cparams, HttpParams rparams, HttpParams oparams) {
        this.applicationParams = aparams;
        this.clientParams = cparams;
        this.requestParams = rparams;
        this.overrideParams = oparams;
    }

    public ClientParamsStack(ClientParamsStack stack) {
        this(stack.getApplicationParams(), stack.getClientParams(), stack.getRequestParams(), stack.getOverrideParams());
    }

    public ClientParamsStack(ClientParamsStack stack, HttpParams aparams, HttpParams cparams, HttpParams rparams, HttpParams oparams) {
        HttpParams httpParams;
        HttpParams httpParams2;
        HttpParams httpParams3;
        HttpParams applicationParams2 = aparams != null ? aparams : stack.getApplicationParams();
        if (cparams != null) {
            httpParams = cparams;
        } else {
            httpParams = stack.getClientParams();
        }
        if (rparams != null) {
            httpParams2 = rparams;
        } else {
            httpParams2 = stack.getRequestParams();
        }
        if (oparams != null) {
            httpParams3 = oparams;
        } else {
            httpParams3 = stack.getOverrideParams();
        }
        this(applicationParams2, httpParams, httpParams2, httpParams3);
    }

    public final HttpParams getApplicationParams() {
        return this.applicationParams;
    }

    public final HttpParams getClientParams() {
        return this.clientParams;
    }

    public final HttpParams getRequestParams() {
        return this.requestParams;
    }

    public final HttpParams getOverrideParams() {
        return this.overrideParams;
    }

    public Object getParameter(String name) {
        Args.notNull(name, "Parameter name");
        Object result = null;
        HttpParams httpParams = this.overrideParams;
        if (httpParams != null) {
            result = httpParams.getParameter(name);
        }
        if (result == null) {
            HttpParams httpParams2 = this.requestParams;
            if (httpParams2 != null) {
                result = httpParams2.getParameter(name);
            }
        }
        if (result == null) {
            HttpParams httpParams3 = this.clientParams;
            if (httpParams3 != null) {
                result = httpParams3.getParameter(name);
            }
        }
        if (result != null) {
            return result;
        }
        HttpParams httpParams4 = this.applicationParams;
        if (httpParams4 != null) {
            return httpParams4.getParameter(name);
        }
        return result;
    }

    public HttpParams setParameter(String name, Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Setting parameters in a stack is not supported.");
    }

    public boolean removeParameter(String name) {
        throw new UnsupportedOperationException("Removing parameters in a stack is not supported.");
    }

    public HttpParams copy() {
        return this;
    }
}
