# NettyClientServer

To run this application,

Setup kafka queue to it's default settings.
Create a topic "kafkaTopic"
Run the server by running "com.netty.server.NettyServer"
Run the client "com.netty.client.NettyClient"
In the NettyClient console type the message. 

The message is converted to JsonObject and then wrapped to ByteBuf and sent to server.
The server queues the message in kafka queue.
