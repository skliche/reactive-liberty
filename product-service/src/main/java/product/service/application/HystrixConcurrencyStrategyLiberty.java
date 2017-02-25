package product.service.application;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

/**
 * A WAS specific implementation of the HystrixConcurrencyStrategy that uses a ManagedThreadFactory under
 * the JNDI name "concurrent/hystrix/threadFactory"
 * 
 * @see http://netflix.github.io/Hystrix/javadoc/index.html?com/netflix/hystrix/strategy/concurrency/HystrixConcurrencyStrategy.html
 * @author skliche
 *
 */
public class HystrixConcurrencyStrategyLiberty extends HystrixConcurrencyStrategy {
	private static final String THREAD_FACTORY_JNDI = "concurrent/hystrix/threadFactory";
	private InitialContext ic;
	
	public HystrixConcurrencyStrategyLiberty() {
		try {
			this.ic =  new InitialContext();
		} catch (NamingException e) {
        	throw new IllegalStateException(e);
        } 
	}
	
	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize,
			HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
        try {
        	ThreadFactory threadFactory =  (ThreadFactory) ic.lookup(THREAD_FACTORY_JNDI);
            return new ThreadPoolExecutor(corePoolSize.get(), maximumPoolSize.get(), keepAliveTime.get(), unit, workQueue, threadFactory);
        } catch (NamingException e) {
        	throw new IllegalStateException(e);
        } 
    }
}
