package de.homelabs.mina.timeserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class TestCodecFactory implements ProtocolCodecFactory {

	private ProtocolEncoder encoder;
    private ProtocolDecoder decoder;
    
    public TestCodecFactory(boolean client) {
        if (client) {
            encoder = null;
            decoder = null;
        } else {
            encoder = new TestEncoder();
            decoder = new TestDecoder();
        }
    }
    
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }
    
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
    
}
