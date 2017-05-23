/**  
* NettyServerHandler.java - This class handles the incoming message received from the Server 
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyServer, NettyServerInitializer 
*/

package com.netty.server;

import java.util.logging.Logger;

import org.json.JSONObject;

import com.kafka.dao.KafkaQueueDAOImpl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

public class NettyServerHandler extends ChannelInboundMessageHandlerAdapter<String> {

	public static final ChannelGroup channels = new DefaultChannelGroup();
	
	Logger logger = Logger.getLogger(NettyServerHandler.class.getName());
	
	/*
	 * This method is invoked when a new message is received. It is overridden to handle the incoming message.
	 * Here it just prints the message and also sends message to queue
	 * @param SocketChannel channel
	 * @throws Exception
	 */
	@Override
	public void messageReceived(ChannelHandlerContext arg0, String message) throws Exception {
		JSONObject jsonObject = new JSONObject(message);
		KafkaQueueDAOImpl kafkaQueueClass = new KafkaQueueDAOImpl();
		kafkaQueueClass.writeQueue(String.valueOf(jsonObject));
	}

}
