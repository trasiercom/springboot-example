package com.trasier.showcase;

import com.trasier.client.api.ContentType;
import com.trasier.client.api.Span;
import com.trasier.client.api.TrasierConstants;
import com.trasier.client.opentracing.TrasierSpan;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Controller
public class ShowcaseController {
    @Autowired
    private Tracer tracer;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello world");
        return "world";
    }

    @RequestMapping("/sub")
    public String sub() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);

        io.opentracing.Span span = tracer.buildSpan("sub")
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_CLIENT)
                .start();

        Span trasierSpan = null;

        if (span instanceof TrasierSpan) {
            trasierSpan = ((TrasierSpan) span).unwrap();
        }

        if (trasierSpan != null) {
            trasierSpan.setIncomingContentType(ContentType.TEXT);
            trasierSpan.setBeginProcessingTimestamp(System.currentTimeMillis());
            trasierSpan.setIncomingData("inner - in");
        }

        try (Scope scope = this.tracer.activateSpan(span)) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("sub - " + trasierSpan.getConversationId());

            trasierSpan.setOutgoingContentType(ContentType.TEXT);
            trasierSpan.setFinishProcessingTimestamp(System.currentTimeMillis());
            trasierSpan.setOutgoingData("inner - out");
            trasierSpan.setStatus(TrasierConstants.STATUS_OK);
        } finally {
//            io.opentracing.ScopeManager scopeManager = tracer.scopeManager();
//            if (scopeManager instanceof TrasierScopeManager) {
//                ((TrasierScopeManager) scopeManager).activeScope().close();
//            }

            span.finish();
        }

        TimeUnit.MILLISECONDS.sleep(100);

        return "sub";
    }

    @GetMapping("/async")
    public Mono<String> async() {
        System.out.println("async");
        return Mono.just("async");
    }
}