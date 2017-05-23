/**  
* NettyClientInitializer.java - This class initializes the properties of Channel pipeline
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyClient, NettyClientHandler
*/ 

package com.netty.client;

import java.util.logging.Logger;

import com.utility.Constants;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
	
	Logger logger = Logger.getLogger(NettyClientHandler.class.getName());
	
	/*
	 * initChannel initializes the channel pipeline properties like framer, encoder, decoder, handler
	 * @param SocketChannel channel
	 * @throws Exception
	 */
	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		
		pipeline.addLast(Constants.FRAMER_KEY, new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast(Constants.DECODER_KEY, new StringDecoder());
		pipeline.addLast(Constants.ENCODER_KEY, new StringEncoder());
		pipeline.addLast(Constants.HANDLER_KEY, new NettyClientHandler());
	}

}
