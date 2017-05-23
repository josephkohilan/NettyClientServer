/**  
* NettyClientHandler.java - This class handles the incoming message received from the Server 
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyClient, NettyClientInitializer 
*/ 

package com.netty.client;

import java.util.logging.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

public class NettyClientHandler extends ChannelInboundMessageHandlerAdapter<String> {
	
	Logger logger = Logger.getLogger(NettyClientHandler.class.getName());
	
	/*
	 * This method is invoked when a new message is received. It is overridden to handle the incoming message.
	 * Here it just prints the message.
	 * @param SocketChannel channel
	 * @throws Exception
	 */
	@Override
	public void messageReceived(ChannelHandlerContext channelHandlerContext, String message) throws Exception {
		System.out.println(message);
	}

}
