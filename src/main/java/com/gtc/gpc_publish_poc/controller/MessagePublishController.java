package com.gtc.gpc_publish_poc.controller;

import com.gtc.gpc_publish_poc.helper.PublishMessageHelper;
import com.gtc.gpc_publish_poc.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublishController {

    @Autowired
    PublishMessageHelper publishMessageHelper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/movie")
    public void sebdNessage(@RequestBody Movie newMovie) {
        System.out.println("SendMessage movie -> [ " + newMovie + "]");
        publishMessageHelper.publishMovieMessage(newMovie);
    }
}
