/**  
* NettyServerInitializer.java - This class initializes the properties of Channel pipeline
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyServer, NettyServerHandler
*/ 

package com.netty.server;

import java.util.logging.Logger;

import com.utility.Constants;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
	
	Logger logger = Logger.getLogger(NettyServerInitializer.class.getName());
	
	/*
	 * initChannel initializes the channel pipeline properties like framer, encoder, decoder, handler
	 * @param SocketChannel channel
	 * @throws Exception
	 */
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		ChannelPipeline pipeline = arg0.pipeline();
		
		pipeline.addLast(Constants.FRAMER_KEY, new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		pipeline.addLast(Constants.DECODER_KEY, new StringDecoder());
		pipeline.addLast(Constants.ENCODER_KEY, new StringEncoder());
		pipeline.addLast(Constants.HANDLER_KEY, new NettyServerHandler());
	}

}
