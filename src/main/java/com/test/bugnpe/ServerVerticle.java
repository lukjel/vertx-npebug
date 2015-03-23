package com.test.bugnpe;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.apex.Router;
import io.vertx.ext.apex.handler.StaticHandler;
import io.vertx.ext.apex.handler.sockjs.BridgeOptions;
import io.vertx.ext.apex.handler.sockjs.PermittedOptions;
import io.vertx.ext.apex.handler.sockjs.SockJSHandler;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author lzeligowski
 */
@Log4j2
public class ServerVerticle extends AbstractVerticle {

    public void start() {
        log.info("start()");
        HttpServerOptions options = new HttpServerOptions();
        HttpServer http = vertx.createHttpServer(options);

        SockJSHandler sockJS = SockJSHandler.create(vertx);
        BridgeOptions allAccessOptions = new BridgeOptions();
        allAccessOptions.addInboundPermitted(new PermittedOptions());
        allAccessOptions.addOutboundPermitted(new PermittedOptions());
        sockJS.bridge(allAccessOptions);

        Router router = Router.router(vertx);

        router.route("/eventbus/*").handler(sockJS);
        router.route("/*").handler(StaticHandler.create("webroot").setDirectoryListing(true));

        http.requestHandler(router::accept).listen(8484);

    }
}
