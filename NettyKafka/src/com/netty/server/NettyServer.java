/**  
* NettyServer.java - Running this class creates a server
* @author  Joseph Kohilan
* @version 1.0 
* @see NettyServerInitializer, NettyServerHandler 
*/
package com.netty.server;

import java.util.logging.Logger;

import com.utility.Constants;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

	private final int port;
	
	Logger logger = Logger.getLogger(NettyServer.class.getName());

	/*
	 * Sets port number in which the server is hosted
	 */
	public static void main(String[] args) {
		new NettyServer(8000).startServer();
	}
	
	/*
	 * constructor which assigns port number
	 */
	public NettyServer(int port) {
		this.port = port;
	}
	
	/*
	 * Starts the server,
	 * Handles and passes messages to client
	 * Convert message to JSONObject
	 * Sends the JSONObject to kafka queue
	 */
	public void startServer() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			System.out.println(Constants.SERVER_RUNNING_MESSAGE);
			ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class).childHandler(new NettyServerInitializer());

			bootstrap.bind(port).sync().channel().closeFuture().sync();
		} catch (Exception e) {
			logger.info("Error occured with chat server");
			System.out.println(Constants.SERVER_STOPPED_MESSAGE);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			System.out.println(Constants.SERVER_STOPPED_MESSAGE);
		}
	}
	
}
