package de.homelabs.mina.timeserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class TestDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.remaining() >= 3) {
			/*
			if (in.get(1) == 0x0d && in.get(2) == 0x0a) {
				//get got a key
				int key = in.get(0);
				out.write(key);
				return true;
			}*/
			int key = in.get();
			int m1 = in.get();
			int m2 = in.get();
			
			if (m1 == 0x0d && m2 == 0x0a) {
				out.write(key);
				return true;
			}
		}
		
		//else error
		session.write("PUNK!");
		session.closeOnFlush();
		return false;
		
		/*if (in.get(0) != 1) {
		session.write("PUNK!");
		session.closeNow();
		return false;
	}*/
	}

}
