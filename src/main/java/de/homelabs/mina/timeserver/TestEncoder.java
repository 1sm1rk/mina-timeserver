package de.homelabs.mina.timeserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEncoder extends ProtocolEncoderAdapter {
	private static final Logger logger = LoggerFactory.getLogger(TestEncoder.class);
	
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		logger.debug("encoding: {}",message);
		byte[] data = ((String) message).getBytes();
		int datacount = ((String) message).length();
		IoBuffer buffer = IoBuffer.allocate(datacount, false);
        buffer.setAutoExpand(true);
        buffer.put(data);
        buffer.flip();
		out.write(buffer);
	}

}
