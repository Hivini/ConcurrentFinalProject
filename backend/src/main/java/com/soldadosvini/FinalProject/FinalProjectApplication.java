package com.soldadosvini.FinalProject;

import com.soldadosvini.FinalProject.producerConsumer.Buffer;
import com.soldadosvini.FinalProject.producerConsumer.Consumer;
import com.soldadosvini.FinalProject.producerConsumer.Producer;
import com.soldadosvini.FinalProject.producerConsumer.ProducerConsumer;
import com.soldadosvini.FinalProject.producerConsumer.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectApplication {

	final static Logger logger = LoggerFactory.getLogger(FinalProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}
}
