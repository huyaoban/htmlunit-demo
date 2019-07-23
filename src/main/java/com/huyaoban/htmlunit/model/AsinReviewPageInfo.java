package com.huyaoban.htmlunit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsinReviewPageInfo {
	private String asin;

	private String countryCode;

	private Integer pageNo;

	private String reviewPageLink;
}
