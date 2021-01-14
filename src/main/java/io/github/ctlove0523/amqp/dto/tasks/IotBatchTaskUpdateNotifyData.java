package io.github.ctlove0523.amqp.dto.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


import io.github.ctlove0523.amqp.dto.BaseNotifyData;



@Getter
@Setter
public class IotBatchTaskUpdateNotifyData extends BaseNotifyData {
    @JsonProperty("notify_data")
	private BatchTaskUpdateNotifyData notifyData;



}

