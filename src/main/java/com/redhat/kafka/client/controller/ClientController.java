package com.redhat.kafka.client.controller;

import com.redhat.kafka.client.service.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping(value = "/send")
    public String send(@RequestParam(required = true) String message) {

        LOG.info(message);

        kafkaSender.sendMessage(topicName, message.trim());

        return "Sent [" + message.trim() + "]\n";
    }

}
