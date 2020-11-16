package com.soldadosvini.FinalProject.controllers;

import com.soldadosvini.FinalProject.messages.TestMessage;
import com.soldadosvini.FinalProject.messages.TestResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class TestResponseController {

    Logger logger = LoggerFactory.getLogger(TestResponseController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestResponse send(TestMessage message) throws Exception {
        // We can take as long as we want here to send back the message.
        logger.info("This is an example log, it's useful.");
        Thread.sleep(1000);
        return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
