package product.service.application;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.strategy.HystrixPlugins;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener{
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationLifecycleListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("shutting down the app");
		
		// shutdown all thread pools; waiting a little time for shutdown
		Hystrix.reset(1, TimeUnit.SECONDS);
		
		// shutdown configuration listeners that might have been activated by Archaius
		if (ConfigurationManager.getConfigInstance() instanceof DynamicConfiguration) {
		    ((DynamicConfiguration) ConfigurationManager.getConfigInstance()).stopLoading();
		} else if (ConfigurationManager.getConfigInstance() instanceof ConcurrentCompositeConfiguration) {
		    ConcurrentCompositeConfiguration config = ((ConcurrentCompositeConfiguration) ConfigurationManager.getConfigInstance());
		    for (AbstractConfiguration innerConfig : config.getConfigurations()) {
		        if (innerConfig instanceof DynamicConfiguration) {
		            ((DynamicConfiguration) innerConfig).stopLoading();
		        }
		    }
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("starting the app");
		
		HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixConcurrencyStrategyLiberty());
	}

}
