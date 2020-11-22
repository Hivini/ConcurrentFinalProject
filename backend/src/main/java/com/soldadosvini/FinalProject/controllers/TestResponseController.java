package com.soldadosvini.FinalProject.controllers;

import com.soldadosvini.FinalProject.messages.TestMessage;
import com.soldadosvini.FinalProject.messages.TestResponse;

import com.soldadosvini.FinalProject.producerConsumer.ProducerConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Observable;
import java.util.StringTokenizer;

@Controller
public class TestResponseController {

    @Autowired
    public SimpMessageSendingOperations messagingTemplate;

    Logger logger = LoggerFactory.getLogger(TestResponseController.class);

    /*
     * @MessageMapping("/hello")
     * 
     * @SendTo("/topic/greetings") public TestResponse send(TestMessage message)
     * throws Exception { // We can take as long as we want here to send back the
     * message. ProducerConsumer pc = new ProducerConsumer(2, 2, 10, new Character[]
     * { '+', '-' }, 1, 9, 1000);
     * 
     * pc.start();
     * 
     * Observer myObserver = new Observer(); pc.buffer.addObserver(myObserver);
     * 
     * return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) +
     * "!"); }
     */

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestResponse init(TestMessage message) throws Exception {
        // Format: "Cons, Prods, Buffer Size, minRange, maxRange, wait time, Operator1,
        // Operator2... Operatorn"
        // Format: "2,2,10,1,9,500,+,-,/,*"

        String parametersStr = HtmlUtils.htmlEscape(message.getName());

        StringTokenizer auxTokenizer = new StringTokenizer(parametersStr, ",");
        int nConsumers = Integer.parseInt(auxTokenizer.nextToken());
        int nProducers = Integer.parseInt(auxTokenizer.nextToken());
        int bufferSize = Integer.parseInt(auxTokenizer.nextToken());
        int minRange = Integer.parseInt(auxTokenizer.nextToken());
        ;
        int maxRange = Integer.parseInt(auxTokenizer.nextToken());
        ;
        int waitRime = Integer.parseInt(auxTokenizer.nextToken());
        ArrayList<Character> operatorsArrayList = new ArrayList<>();

        while (auxTokenizer.hasMoreTokens()) {
            operatorsArrayList.add(auxTokenizer.nextToken().charAt(0));
        }

        Character[] operatorsArray = new Character[operatorsArrayList.size()];
        operatorsArrayList.toArray(operatorsArray);

        ProducerConsumer pc = new ProducerConsumer(nConsumers, nProducers, bufferSize, operatorsArray, minRange,
                maxRange, waitRime);

        pc.start();

        return new TestResponse("Producer Consumer Started correctly");

    }

    class Observer implements java.util.Observer {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println("From obs: ");
            System.out.println(arg);
            messagingTemplate.convertAndSend("/topic/greetings", arg.toString());
        }
    }
}
