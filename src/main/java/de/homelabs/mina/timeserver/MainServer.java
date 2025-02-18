package de.homelabs.mina.timeserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainServer  {
	private static final Logger logger = LoggerFactory.getLogger(MainServer.class);
	private static final int PORT = 9123;
	private IoAcceptor acceptor;
	
	public void start() throws IOException {
		addShutdownHook();
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());

		acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new TestCodecFactory(false)));
		acceptor.setHandler(new TestHandler());

		// acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new
		// TextLineCodecFactory( Charset.forName( "UTF-8" ))));
		// acceptor.setHandler( new TimeServerHandler() );

		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.bind(new InetSocketAddress(PORT));

		logger.debug("test");
		logger.info("server startet on port {}", PORT);
	}

	private void addShutdownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				logger.info("server shutdown!");
				if (acceptor != null) {
					acceptor.unbind();
					acceptor.dispose();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}));
	}
}
