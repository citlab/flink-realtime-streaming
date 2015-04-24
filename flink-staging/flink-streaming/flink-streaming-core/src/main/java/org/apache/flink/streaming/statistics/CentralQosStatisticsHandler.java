/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.statistics;

import org.apache.flink.api.common.JobID;
import org.apache.flink.runtime.executiongraph.ExecutionGraph;
import org.apache.flink.runtime.messages.ExecutionGraphMessages.ExecutionStateChanged;
import org.apache.flink.runtime.messages.ExecutionGraphMessages.JobStatusChanged;
import org.apache.flink.runtime.statistics.AbstractCentralStatisticsHandler;
import org.apache.flink.runtime.statistics.CustomStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.duration.FiniteDuration;

public class CentralQosStatisticsHandler extends AbstractCentralStatisticsHandler {
	private static final Logger LOG = LoggerFactory.getLogger(CentralQosStatisticsHandler.class);
	private JobID jobID;
	private ExecutionGraph executionGraph;
	private FiniteDuration reportInterval;

	@Override
	public void open(JobID jobID, ExecutionGraph executionGraph, FiniteDuration reportInterval) {
		this.jobID = jobID;
		this.executionGraph = executionGraph;
		this.reportInterval = reportInterval;
		LOG.warn("New QoS statistics controller initialized!");
	}

	@Override
	public void handleStatistic(CustomStatistic statistic) {
		LOG.info("Got some statistics: {}", statistic);
	}

	@Override
	public void reportStatistics() {
		LOG.warn("Statistic? Running!");
	}

	@Override
	public void handleExecutionStateChanged(ExecutionStateChanged executionStatus) {
		LOG.warn("Got execution state change: {}", executionStatus);

		//executionGraph.getRegisteredExecutions().get(executionStatus.executionID()).getAssignedResource().getInstance().getTaskManager().tell();
	}

	@Override
	public void handleJobStatusChanged(JobStatusChanged jobStatus) {
		LOG.warn("Got new job status: {}", jobStatus);
	}

	@Override
	public void close() {
		LOG.warn("Central qos handler closed!");
	}
}