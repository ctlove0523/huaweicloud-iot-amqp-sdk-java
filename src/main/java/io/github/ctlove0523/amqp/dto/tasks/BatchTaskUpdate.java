package io.github.ctlove0523.amqp.dto.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BatchTaskUpdate {
    @JsonProperty("status_desc")
	private String statusDesc;

    @JsonProperty("task_id")
	private String taskId;

    @JsonProperty("task_type")
	private String taskType;

    @JsonProperty("app_id")
	private String appId;

	private String status;

}

