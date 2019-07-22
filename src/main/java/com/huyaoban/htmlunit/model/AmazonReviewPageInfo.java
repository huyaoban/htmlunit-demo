package com.huyaoban.htmlunit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmazonReviewPageInfo {
	private String asin;

	private Integer pageNo;

	private String reviewPageUrl;
}
