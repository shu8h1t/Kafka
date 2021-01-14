package com.github.shubhit;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;

public class CustomDeserializationExceptionHandler implements DeserializationExceptionHandler {  
	  
	  @Override  
	  public DeserializationHandlerResponse handle(ProcessorContext context, ConsumerRecord<byte[], byte[]> record, 
	  									Exception exception) {  
	        //logic to deal with the corrupt record  
	  	return DeserializationHandlerResponse.CONTINUE;  
	    }

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		
	}  
	   
	}