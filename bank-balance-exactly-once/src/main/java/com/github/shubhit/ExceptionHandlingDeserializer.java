package com.github.shubhit;

import java.util.Collections;
import java.util.Set;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionHandlingDeserializer implements Deserializer<JsonNode> {
	private ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingDeserializer.class);
	
	public ExceptionHandlingDeserializer() {
		this(Collections.emptySet());
	}



	ExceptionHandlingDeserializer(final Set<DeserializationFeature> deserializationFeatures) {
        deserializationFeatures.forEach(objectMapper::enable);
    }



	@Override
	public JsonNode deserialize(String topic, byte[] bytes) {
		// TODO Auto-generated method stub
		if (bytes == null)
            return null;

        JsonNode data;
        try {
            data = objectMapper.readTree(bytes);
            return data;
        } catch (Exception e) {
        	logger.warn("Exception caught during Deserialization: {}", e.getMessage());

            // return the sentinel record upon corrupted data
            return null;
        }
	}

}
