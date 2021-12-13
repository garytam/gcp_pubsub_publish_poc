package com.gtc.gpc_publish_poc.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.gtc.gpc_publish_poc.model.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class PublishMessageHelper {

    @Value("${projectId}")
    private String projectId;

    @Value("${topicId}")
    private String topicId;

    public void publishMovieMessage(Movie movie, Map<String, String> header) {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;

        Map<String, String> attributeMap = new HashMap<>();
        List<String> list = Arrays.asList("transactionId", "author", "year");
        list.forEach( item -> {
            attributeMap.put(item, header.get(item.toLowerCase(Locale.ROOT)));
        });

        ImmutableMap<String, String> immutableMap = ImmutableMap.copyOf(attributeMap);
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();

            ObjectMapper objectMapper = new ObjectMapper();;
            String payload = objectMapper.writeValueAsString(movie);
            String message = "fourth wireless message";
            ByteString data = ByteString.copyFromUtf8(payload);
            PubsubMessage pubsubMessage =
                    PubsubMessage.newBuilder()
                            .setData(data)
                            .putAllAttributes(immutableMap) //ImmutableMap.of("year", "2020", "author", "Gordo", "parm", "wireless"))
                            .build();

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Published a message with custom attributes: " + messageId);

        } catch(Exception exc){
            System.out.println(exc.getMessage());
        } finally{
            if (publisher != null) {
                try {
                    // When finished with the publisher, shutdown to free up resources.
                    publisher.shutdown();
                    publisher.awaitTermination(1, TimeUnit.MINUTES);
                } catch ( Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
    }
}
