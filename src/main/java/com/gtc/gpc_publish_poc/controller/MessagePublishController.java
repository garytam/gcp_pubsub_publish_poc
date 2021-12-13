package com.gtc.gpc_publish_poc.controller;

import com.gtc.gpc_publish_poc.helper.PublishMessageHelper;
import com.gtc.gpc_publish_poc.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MessagePublishController {

    @Autowired
    PublishMessageHelper publishMessageHelper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/movie")
    public void sendMessage(@RequestHeader Map<String, String> header,
                            @RequestBody Movie newMovie) {
        System.out.println("SendMessage movie -> [ " + newMovie + "]");
        String transactionId = header.get("transactionId");
        publishMessageHelper.publishMovieMessage(newMovie, header);
    }
}
