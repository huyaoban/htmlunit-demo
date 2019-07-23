package com.huyaoban.htmlunit.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryInfo {
	private String countryCode;

	private String baseUrl;

	private String authority;

	private String reviewPageLinkTemplate;

	private String reviewLinkTemplate;

	private String reviewPathTemplate;

	private String cookies;

	private Integer pageSize;

	private String reviewParserService;
}
