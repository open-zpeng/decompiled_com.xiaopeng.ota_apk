package com.xiaopeng.ota.sdk.common.log;

import android.util.Log;
import com.lzy.okgo.utils.IOUtils;
import com.lzy.okgo.utils.OkLogger;
import com.xiaopeng.ota.sdk.common.ContentType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
/* loaded from: classes2.dex */
public class HttpLogInterceptor implements Interceptor {
    static final String TAG = "OTAHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request, chain.connection());
        long nanoTime = System.nanoTime();
        try {
            return logForResponse(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        } catch (Exception e) {
            log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void logForRequest(Request request, Connection connection) throws IOException {
        StringBuilder sb;
        RequestBody body = request.body();
        boolean z = body != null;
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        try {
            try {
                log("--> " + request.method() + ' ' + request.url() + ' ' + protocol);
                if (z) {
                    if (body.contentType() != null) {
                        log("\tContent-Type: " + body.contentType());
                    }
                    if (body.contentLength() != -1) {
                        log("\tContent-Length: " + body.contentLength());
                    }
                }
                Headers headers = request.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String name = headers.name(i);
                    if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                        log("\t" + name + ": " + headers.value(i));
                    }
                }
                log(" ");
                if (z) {
                    if (isPlaintext(body.contentType())) {
                        bodyToString(request);
                    } else {
                        log("\tbody: maybe [binary body], omitted!");
                    }
                }
                sb = new StringBuilder();
            } catch (Exception e) {
                Log.d(TAG, "Log request occurs exception", e);
                sb = new StringBuilder();
            }
            sb.append("--> END ");
            sb.append(request.method());
            log(sb.toString());
        } catch (Throwable th) {
            log("--> END " + request.method());
            throw th;
        }
    }

    private Response logForResponse(Response response, long j) {
        Response build = response.newBuilder().build();
        ResponseBody body = build.body();
        try {
            try {
                log("<-- " + build.code() + ' ' + build.message() + ' ' + build.request().url() + " (" + j + "ms）");
                Headers headers = build.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    log("\t" + headers.name(i) + ": " + headers.value(i));
                }
                log(" ");
                if (HttpHeaders.hasBody(build)) {
                    if (body == null) {
                        return response;
                    }
                    if (isPlaintext(body.contentType())) {
                        byte[] byteArray = IOUtils.toByteArray(body.byteStream());
                        String str = new String(byteArray, getCharset(body.contentType()));
                        log("\tbody:" + str);
                        return response.newBuilder().body(ResponseBody.create(body.contentType(), byteArray)).build();
                    }
                    log("\tbody: maybe [binary body], omitted!");
                }
            } catch (Exception e) {
                Log.d(TAG, "Log response occurs exception", e);
            }
            return response;
        } finally {
            log("<-- END HTTP");
        }
    }

    private static Charset getCharset(MediaType mediaType) {
        Charset charset = mediaType != null ? mediaType.charset(UTF8) : UTF8;
        return charset == null ? UTF8 : charset;
    }

    private void log(String str) {
        Logger.d(TAG, str, new Object[0]);
    }

    private static boolean isPlaintext(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() == null || !mediaType.type().equals("text")) {
            String subtype = mediaType.subtype();
            if (subtype != null) {
                String lowerCase = subtype.toLowerCase();
                if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains(ContentType.SUBTYPE_XML) || lowerCase.contains("html")) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private void bodyToString(Request request) {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body == null) {
                return;
            }
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset charset = getCharset(body.contentType());
            log("\tbody:" + buffer.readString(charset));
        } catch (Exception e) {
            OkLogger.printStackTrace(e);
        }
    }
}
