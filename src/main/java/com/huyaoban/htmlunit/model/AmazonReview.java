package com.huyaoban.htmlunit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmazonReview {
	private String amazonReviewId;

	private String asin;

	private String customerName;

	private String customerProfileLink;

	private String currentStar;

	private String title;

	private String reviewDate;

	private Boolean verifiedPurchase;

	private String content;

	private String contentHtml;

	private Integer helpfulVotes;

	private String reviewLink;

	public AmazonReview(Builder builder) {
		this.amazonReviewId = builder.amazonReviewId;
		this.asin = builder.asin;
		this.customerName = builder.customerName;
		this.customerProfileLink = builder.customerProfileLink;
		this.currentStar = builder.currentStar;
		this.title = builder.title;
		this.reviewDate = builder.reviewDate;
		this.verifiedPurchase = builder.verifiedPurchase;
		this.content = builder.content;
		this.contentHtml = builder.contentHtml;
		this.helpfulVotes = builder.helpfulVotes;
		this.reviewLink = builder.reviewLink;
	}

	public static class Builder {
		private String amazonReviewId;

		private String asin;

		private String customerName;

		private String customerProfileLink;

		private String currentStar;

		private String title;

		private String reviewDate;

		private Boolean verifiedPurchase;

		private String content;

		private String contentHtml;

		private Integer helpfulVotes;

		private String reviewLink;

		public Builder withAmazonReviewId(String val) {
			this.amazonReviewId = val;
			return this;
		}

		public Builder withAsin(String val) {
			this.asin = val;
			return this;
		}

		public Builder withCustomerName(String val) {
			this.customerName = val;
			return this;
		}

		public Builder withCustomerProfileLink(String val) {
			this.customerProfileLink = val;
			return this;
		}

		public Builder withCurrentStar(String val) {
			this.currentStar = val;
			return this;
		}

		public Builder withTitle(String val) {
			this.title = val;
			return this;
		}

		public Builder withReviewDate(String val) {
			this.reviewDate = val;
			return this;
		}

		public Builder withVerifiedPurchase(Boolean val) {
			this.verifiedPurchase = val;
			return this;
		}

		public Builder withContent(String val) {
			this.content = val;
			return this;
		}

		public Builder withContentHtml(String val) {
			this.contentHtml = val;
			return this;
		}

		public Builder withHelpfulVotes(Integer val) {
			this.helpfulVotes = val;
			return this;
		}

		public Builder withReviewLink(String val) {
			this.reviewLink = val;
			return this;
		}

		public AmazonReview build() {
			return new AmazonReview(this);
		}
	}
}
