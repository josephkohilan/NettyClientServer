/**  
* KafkaQueueBO.java - This class is the business object that transfers queue hit from producer to the consumer
* @author  Joseph Kohilan
* @version 1.0 
* @see KafkaQueueDAO, KafkaQueueDAOImpl 
*/ 

package com.kafka.bo;

import java.util.logging.Logger;

import com.kafka.dao.KafkaQueueDAO;
import com.kafka.dao.KafkaQueueDAOImpl;

public class KafkaQueueBO {
	
	Logger logger = Logger.getLogger(KafkaQueueBO.class.getName());
	
	/*
	 * This method makes the queue hit from java application to the DAO interface
	 * @param String message
	 */
	public void writeQueue(String message){
		KafkaQueueDAO kafkaQueueDAO = new KafkaQueueDAOImpl();
		kafkaQueueDAO.writeQueue(message);
	}

}
