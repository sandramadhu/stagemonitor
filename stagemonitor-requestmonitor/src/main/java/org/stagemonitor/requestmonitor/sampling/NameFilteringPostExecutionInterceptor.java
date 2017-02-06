package org.stagemonitor.requestmonitor.sampling;

import org.stagemonitor.core.util.StringUtils;
import org.stagemonitor.requestmonitor.RequestMonitorPlugin;

import java.util.Collection;

class NameFilteringPostExecutionInterceptor extends PostExecutionRequestTraceReporterInterceptor {

	@Override
	public void interceptReport(PostExecutionInterceptorContext context) {
		final Collection<String> onlyReportRequestsWithName = context.getConfig(RequestMonitorPlugin.class)
				.getOnlyReportRequestsWithNameToElasticsearch();
		if (StringUtils.isEmpty(context.getRequestInformation().getOperationName())) {
			context.shouldNotReport(getClass());
		} else if (!onlyReportRequestsWithName.isEmpty() && !onlyReportRequestsWithName.contains(context.getRequestInformation().getOperationName())) {
			context.shouldNotReport(getClass());
		}
	}

}