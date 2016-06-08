package jp.hiradate.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

public class HttpRequest {
    
    private final HttpHeader header;
    private final HttpBody body;
    
    public HttpRequest(InputStream input) {
        try {
            this.header = new HttpHeader(input);
            this.body = new HttpBody(input, this.header);
            
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public String getHeaderText() {
        return this.header.getText();
    }

    public String getBodyText() {
        return this.body.getText();
    }

    public HttpHeader getHeader() {
        return this.header;
    }
    
}