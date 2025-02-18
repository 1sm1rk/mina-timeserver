package de.homelabs.mina.timeserver;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHandler extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(TestHandler.class);
	
	@Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }
	
    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
    	//System.out.println("Message: "+message);
    	session.write("bump");
    	//System.out.println("bump");
    	
    	
        /*String str = message.toString();
        if( str.trim().equalsIgnoreCase("quit") ) {
        	session.write("test!");
        	session.closeOnFlush();
            return;
        }
        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written...");*/
    }
    
    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        logger.debug( "IDLE {}", session.getIdleCount( status ));
    }
}
