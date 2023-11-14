package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;

public class test {

	private static final Logger Logger = LogManager.getLogger(Test.class);

	public static void main(String[] arg) {
		
		Logger.trace("H");
		Logger.debug("H");
		Logger.info("H");
		Logger.warn("H");
		Logger.error("H");
		Logger.fatal("H");	
		
		Logger.debug("");
	}
}
