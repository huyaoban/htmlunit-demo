package com.huyaoban.htmlunit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel
public class AsinReviewInfo {
	@ApiModelProperty(value = "ASIN")
	private String asin;

	@ApiModelProperty(value = "Review Url")
	private String reviewUrl;

	@ApiModelProperty(value = "Review Url Template")
	private String reviewUrlTmplt;

	@ApiModelProperty(value = "Page Size")
	private Integer pageSize = 20;
}
