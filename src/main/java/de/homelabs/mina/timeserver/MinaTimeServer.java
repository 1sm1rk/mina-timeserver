package de.homelabs.mina.timeserver;

import java.io.IOException;

public class MinaTimeServer
{
    
    public static void main( String[] args ) throws IOException
    {
        MainServer server = new MainServer();
			server.start();
		
    }
	
}