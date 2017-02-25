package product.service.application;

import javax.servlet.annotation.WebServlet;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SuppressWarnings("serial")
@WebServlet("/hystrix.stream")
public class HystrixStreamServlet extends HystrixMetricsStreamServlet { }
