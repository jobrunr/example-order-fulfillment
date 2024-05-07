package org.jobrunr.example.tasks.filters;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.filters.JobServerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderFulfilmentTasksFilter implements JobServerFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFulfilmentTasksFilter.class);

    @Override
    public void onFailedAfterRetries(Job job) {
        // TODO alert operational team of Job failure
        LOGGER.info("All retries failed for Job {}", job.getJobName());
    }
}
