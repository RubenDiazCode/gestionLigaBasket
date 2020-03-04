package talent.campus.examenRubenDiaz.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import org.springframework.http.HttpHeaders;

public class HttpHeaderUtils {

    private HttpHeaderUtils() {  }

    public static HttpHeaders locationHeader(Object id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(toUri(id));
        return responseHeaders;
    }

    private static URI toUri(Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}
