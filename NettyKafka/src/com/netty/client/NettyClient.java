/**  
* NettyClient.java - a simple class for demonstrating netty server-client chat application. 
* Running this class creates a new client along with the existing clients
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyClientInitializer, NettyClientHandler 
*/ 

package com.netty.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.utility.Constants;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyClient {
	
	Logger logger = Logger.getLogger(NettyClient.class.getName());  
	
	private final String host;
	private final int port;

	/*
	 * Sets URL and port number and creates a new client
	 */
	public static void main(String[] args) {
		new NettyClient(Constants.SERVER_URL, 8000).createNewClient();
	}
	
	/*
	 * constructor which assigns host and port number
	 */
	public NettyClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	/*
	 * creates new client,
	 * Reads user input,
	 * Converts the input to JSONObject and wraps it up with byte buff 
	 * and transmits it to the Server through channel
	 */
	public void createNewClient() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
					.handler(new NettyClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				Date date = new Date();
				Map<String, String> jsonInputMap = new HashMap<String, String>();
				jsonInputMap.put(Constants.JSON_DTTM_KEY, date.toString());
				jsonInputMap.put(Constants.JSON_MESSAGE_KEY, in.readLine());
				JSONObject jsonObject = new JSONObject(jsonInputMap);
				ByteBuf buf = Unpooled.copiedBuffer(jsonObject.toString(), CharsetUtil.UTF_8);
				channel.write(buf);
				channel.write(Constants.NEXT_LINE);
			}
		} catch (Exception e) {
			logger.info("Error occured with chat client");
		} finally {
			group.shutdownGracefully();
		}
	}

}
