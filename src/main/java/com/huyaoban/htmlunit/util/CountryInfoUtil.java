package com.huyaoban.htmlunit.util;

import java.util.HashMap;
import java.util.Map;

import com.huyaoban.htmlunit.model.CountryInfo;

public class CountryInfoUtil {
	private static final Map<String, CountryInfo> countryMap = new HashMap<String, CountryInfo>();

	static {
		CountryInfo us = CountryInfo.builder().countryCode("US").authority("www.amazon.com").pageSize(20)
				.reviewParserService("defaultAmazonReviewParseService")
				.baseUrl("https://www.amazon.com/").reviewPageLinkTemplate(
						"https://www.amazon.com/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.com/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"at-main\",\"value\":\"Atza|IwEBIJ5fn-lZuwqaUltQvLmjkevkq833kM3TinH_9azgSYRFSrUE6WpelauReZs9w6rQCoMJxCeJei9UlJV4e5HWS5AUF2iCrzYyx7eB3Z-yb_QXLtGIHo2luTzfdyLPMrwsl9wzAaAgSqIHX_oWCLBTUOrE5jUbkggc8KMrBw1RzfKMe-K6SkZwR_wc9cDKue_YpUw3vpucw9OL2xhQYjoPiHXaSk9kjoUtwbUi5eCe_3lWBBNyFiR1jp9TxuYFOFED01fwcZVH6dIkWNPBBvlyKwum6ZP9AlCeVkza026QFYiFRiEqjTSFDj_Ng8cbVVxg2RYYxteCdH9BB-sWi1tXRUzqEr22cN5r7uMuX78ZdfhW0slHGSoKtRoAIrCr26qsPpdCWyofqodKYTyF4ry24lXv9Bp22WAGF5MX9A5YqwjBP0fNjYx69YU0ryJ1xnuU2zxG5laFYvvM_-IFFcH_WnGLUZYMHmppV03wLpnXKYRbUA\"},{\"domain\":\"www.amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:ZY3VJ2HZE161YYWCS9E4+s-8G7T6MEGXMPDGT8FVWAC|1563876702295&t:1563876702295&adb:adblk_no\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"i18n-prefs\",\"value\":\"USD\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"sess-at-main\",\"value\":\"\\\"kbfv46CZz/w5IUqQyGrRZYAy/KP8FVGmO2wS/bEbHBc=\\\"\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"131-6182389-4182846\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082787201l\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"\\\"rBTtdZQ3FhNc0kaCUNFZA4r2B619E5J1Dj0Dvw7Vk4b3bCpQCA9OLi9puSJ80dnRve2KVsjQEY738bimjRsIBdqzxURSLwsFdSwZJAi4vR+d9R1Iv8DarSJpUS+YY5aXOPV4NnEFJS9UM18tfqPSAli780Wf2UyLG3jjbz1cYZjFnoIkwZKCwpsX0r/bpNmtzzD8+Cwciwnx687Ls7Zsr20n87JPQGe3bw//vEcZblg8SP9pkAAeg9SfL3MgXQR+12+b7KsCtWYg13x4ZDuI4A==\\\"\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"sid\",\"value\":\"\\\"oln1opAyHEmx0/G5yX99Aw==|oe7RyK4uFcWY8Wj8JNM0NsLg4b4k5NNHZ1u539EflTc=\\\"\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"sp-cdn\",\"value\":\"\\\"L5Z9:CN\\\"\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"sst-main\",\"value\":\"Sst1|PQGe2jp2yjnINpc2VF6ImIhmC6YwBZUMTiYGUavHqNoojTSj0G3fSUcbm-bhajxxSPbocxAprJS1vQMZJ7PO-1KF7zauqFkOaJF6JVsGO3Yc2uvc1WnpTDsbtDazH_JxfaljGDKY9m9UPXqdpa4CuQEkZl6fX8xVNc3vOpn7rYQE8v2XgTwcL-2XBKFORjnFz4UkCvZBMBP48aEgsnID6HL24WORGh6WU_BJrAy0E7v08JHiEeVe7O8FduoRBklyzbQ3dxOp1zaAiqJo8RQ1ZesQD0HHbrSEOQWO4ohpRha8kn7tdHayGatMNq3R90Owr0AFqsVai_nqbzdz9M1D1N1ktw\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-main\",\"value\":\"131-6560706-0571334\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"x-main\",\"value\":\"\\\"2s7RjY6OGP38701Rt5TdNw1O@ltmsWJTa4nRLSlDWc4igGwdyDLuSmYiDcMkM6ek\\\"\"},{\"domain\":\".amazon.com\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1KCwOVzXh36yeWdWRDgW6dAnAr6BOCMP0IpPTO1d2+JKf8BNLNlurEU/Dg4ZigJN2MpevSck5F4Fm+8JFRr2egA==\"}]")
				.build();
		countryMap.put(us.getCountryCode(), us);

		CountryInfo it = CountryInfo.builder().countryCode("IT").authority("www.amazon.it").pageSize(20)
				.reviewParserService("itAmazonReviewParseService")
				.baseUrl("https://www.amazon.it/")
				.reviewPageLinkTemplate(
						"https://www.amazon.it/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.it/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\"www.amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:5V98QNFYSDJTETES7HHR+s-S8MQV98ZYEYPNZW93J28|1563865633914&t:1563865633914&adb:adblk_no\"},{\"domain\":\".amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"259-5308802-0698265\"},{\"domain\":\".amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082758401l\"},{\"domain\":\".amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"\\\"StZik1CXpkalv43b94p5LeJ5sQh4DStyE9XpprejRYbrgLQrwIx11I5zN3WS0c/a5B0kGVHP/in7TVWzgGLOEAxEUwI1Ejfn/f03B34+jFe5e8BYRpn1tUHe5jOnh0Bj4hd91bZwvz/YlOE6yUJMZ7OR6G++okXlZJtNomCcXof4wpKzFB4tHGngbNU44To4WMRkgHhmh9JtbW4bmLZ1wVhq+kzWg7vAEgnLcpgKoTKrCt4X7BexYw==\\\"\"},{\"domain\":\".amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbit\",\"value\":\"262-7340777-2502441\"},{\"domain\":\".amazon.it\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1P2U5EsqQKFp8G3HnHJ2CbOzF6Ug6pbDMpSNQ7qk8ytcvkcFT/wG7TQ1Ve4F2A0L1chdRYTughSc=\"}]")
				.build();
		countryMap.put(it.getCountryCode(), it);

		CountryInfo de = CountryInfo.builder().countryCode("DE").authority("www.amazon.de").pageSize(20)
				.reviewParserService("deAmazonReviewParseService")
				.baseUrl("https://www.amazon.de/")
				.reviewPageLinkTemplate(
						"https://www.amazon.de/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.de/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\"www.amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:0XZETNSRDQ6A8VRHH667+s-YQ04PBBY2ZDJZD8CVEBD|1563865909160&t:1563865909160&adb:adblk_no\"},{\"domain\":\".amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"257-6415337-3446744\"},{\"domain\":\".amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082754801l\"},{\"domain\":\".amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"\\\"6neQcobhizYoxz0GwWpmeZyAIze62BgzCA7e35XX9CXv2Xgb388Im2wdWsqU56K0ITsXXVQPP2DfE1iIqYSvahZdpFcbNuLKox+2Ov4DYZIKXmnfuiTcFaxSvep98ud8dOVr5Ft8bKu21hCn4S229i90WPjsu82JRwxS/T9mtbyGwaeJCTJwljDRuFNiDcx7CeIAKJP/2yJvWby9YE6fhLdPaAGYf2WTTQL3cwQLdBUzmI6MhwNS2DsMk66qY5T9HbTS6oEQrF0=\\\"\"},{\"domain\":\".amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbde\",\"value\":\"262-1462806-2206240\"},{\"domain\":\".amazon.de\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1QVopl0gJHoMIdxCUqzLxFOW3mAPYCCLaJOGelcev96AU3N0umuvs0hvh3+ekbwLSUAQOzRr4iCs=\"}]")
				.build();
		countryMap.put(de.getCountryCode(), de);

		CountryInfo fr = CountryInfo.builder().countryCode("FR").authority("www.amazon.fr").pageSize(20)
				.reviewParserService("frAmazonReviewParseService")
				.baseUrl("https://www.amazon.fr/")
				.reviewPageLinkTemplate(
						"https://www.amazon.fr/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.fr/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\"www.amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:YTFQ5WNCAHFQ0YZJGKJX+s-7D3PJXMXTN3Z3DT8JSXJ|1563866061604&t:1563866061604&adb:adblk_no\"},{\"domain\":\".amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"261-5491711-0863333\"},{\"domain\":\".amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082754801l\"},{\"domain\":\".amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"GHJQ8Rma4JZPqDQPYkSt1C/iOis+jjIcz/wJICiw/6V4UzAHRYmpK3IfKGGdjXHOJddtAl3uOqNv8JBOyu6qJqly2uPPBDpx5lF5oHKVy66OxNx7Ish/X5/L7aDTe6eogEKNIqY4ixyXW84g8237g3FQZWIWXz+TitmBLSVzxkNEz/AhO9hxveSPtimYBtDGcpZYoRNwC9P5Qy+oq2a/WdqPsXgVzK0r0KIdnL+84u6MpWPtTVwQF6GYmak3MzG+\"},{\"domain\":\".amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbfr\",\"value\":\"258-6152084-5845049\"},{\"domain\":\".amazon.fr\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1StFSlTnaS62Vhsc/JWBz8F3rhUXZpmFoA14yml+6Kqgo9M9MF8ux4EXwHWfscy/SFMNz9/E3pCs=\"}]")
				.build();
		countryMap.put(fr.getCountryCode(), fr);

		CountryInfo ca = CountryInfo.builder().countryCode("CA").authority("www.amazon.ca").pageSize(20)
				.reviewParserService("defaultAmazonReviewParseService")
				.baseUrl("https://www.amazon.ca/")
				.reviewPageLinkTemplate(
						"https://www.amazon.ca/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.ca/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\"www.amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:39KFCWK6EQ32TQFRJAA8+s-05XKXSAGGQ8TJJA8M6JE|1563866218821&t:1563866218821&adb:adblk_no\"},{\"domain\":\".amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"131-7576143-8531315\"},{\"domain\":\".amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082787201l\"},{\"domain\":\".amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"iMNaS/o1ihKakW7NNWjYsgyT45J7N3xLsLQCFIbXcNYeBSMeyCccompUZ7ms3MKmCRl/J2SPbclcLU+lcmn/ImVRDmcyKZ+wpmE5MzYrN+ofhwXAi/ewqKyGW2tuHzcOwB2lPvqXaiJc+wl8fbayoybflP2J0IjyKwdeMwle4Rthrc7Q9G0Xs/oXoR2wm/mQLtdX+weVH3Uh7x8o56TfGpwYd4El37IxnygUCcnvJTj0MfQ4gjV1Zy6QFJ0Esqjk\"},{\"domain\":\".amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbca\",\"value\":\"131-1504825-7606061\"},{\"domain\":\".amazon.ca\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1ruM9JrhCebuw7yKBWEVvKSg+J94DcjB9J6GlxYz6s1ngtQLQnD2fI7qUSjRUY3YTPLQYzPDYtVY=\"}]")
				.build();
		countryMap.put(ca.getCountryCode(), ca);

		CountryInfo es = CountryInfo.builder().countryCode("ES").authority("www.amazon.es").pageSize(20)
				.reviewParserService("esAmazonReviewParseService")
				.baseUrl("https://www.amazon.es/")
				.reviewPageLinkTemplate(
						"https://www.amazon.es/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.es/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\"www.amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:W394Y02BPZ24HADZANFQ+s-NE1Y5K2XD8AT95MA81FN|1563866307212&t:1563866307212&adb:adblk_no\"},{\"domain\":\".amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"260-0366730-0909240\"},{\"domain\":\".amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082758401l\"},{\"domain\":\".amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"RtOgiA8zK68s8CixjYUn8ndULmmDk1epZ7zF61KAsRzZJHiwA/FQC5Es0Typd7Tb7P/COyjBdDJZ6iVgiJW0sUSw3pJy9btYAH3r9YXBskSFY7/vuRaJJC/pSEGMF53L1Pmas7Y3Gih8mziNTRfhpjQz9PIkIvDDh/JOuSdGUU+2xBU7W3JPY1+OpNfOvO7V\"},{\"domain\":\".amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbes\",\"value\":\"258-6006608-3671064\"},{\"domain\":\".amazon.es\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1WxT+lT3f6pzD6UOxu9PH/fCYjAvqUpoMqgC9rPh6BOG8fOZsLfQy8wPVU94vjyc4wRHAzrAhji4=\"}]")
				.build();
		countryMap.put(es.getCountryCode(), es);

		CountryInfo uk = CountryInfo.builder().countryCode("UK").authority("www.amazon.co.uk").pageSize(20)
				.reviewParserService("defaultAmazonReviewParseService")
				.baseUrl("https://www.amazon.co.uk/")
				.reviewPageLinkTemplate(
						"https://www.amazon.co.uk/product-reviews/{0}/ref=cm_cr_getr_d_paging_btm_next_{1}?ie=UTF8&reviewerType=all_reviews&pageNumber={1}&pageSize={2}")
				.reviewPathTemplate("/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.reviewLinkTemplate(
						"https://www.amazon.co.uk/gp/customer-reviews/{0}/ref=cm_cr_arp_d_rvw_ttl?ie=UTF8&ASIN={1}")
				.cookies(
						"[{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"at-acbuk\",\"value\":\"Atza|IwEBIHW2OTrxEqmH8cG1GqTRA5-J2t4Ly5ro36R56-VrI6AG6lcV3qpqcsTxHIxjsp6sqbBlXpvSigtuhuQcBWArOamWy1cbkLByXvOWAwtnq9P0T3xIi0svsA75cl8gfOoxzdclGJKTN8XAcwdQvVMDJgOC--cLKF-hCOr1SqUe42oP2JD3hAdlMxZZm_bSA8C1DiFsMzrrAGhPhMHrw09hhWQDmfDLyRZIdLyoRHs6y0AL9EfAL9iSfSu8_PuRgOX8grh7nXrr_ynqnkZR69BcwcVKYZCTkeNmI5WTd2YiE-M82I2_CtrSQb4ngHVVx7yMJ8cPHhFEPEOL_a7XMnq2zQhh_Fd2lNV1RHuPy8iYAoxep4uzXSNk5h-I02aVYj-T1obymOL75ahITiL36iaHFWPO1mj2YbbMrryGTdyggWNG9yNcyLgFzKFlM2ERgfAFKhUOweMNgJTk9w3-P8hb5NiaTtAXUXpcRebnOqLE2u6pHw\"},{\"domain\":\"www.amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"csm-hit\",\"value\":\"tb:KRZJFV41PABXP77F7E15+s-T238HJZZ1C1FKGKYBCQR|1563864070732&t:1563864070732&adb:adblk_no\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"i18n-prefs\",\"value\":\"GBP\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"sess-at-acbuk\",\"value\":\"\\\"GCFnjUc34C7Z9YttUbJYt2WCMudMqpX1S1BXLUwXmLU=\\\"\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id\",\"value\":\"258-6364813-4892209\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"session-id-time\",\"value\":\"2082758401l\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"session-token\",\"value\":\"\\\"RC24BJ9KFFrtRX+C3R6hcYpV4BbLfB+ZHdQ0lIVsoM8nc4Qh83SiOdtOiwQK5hSqKOAlUTNj1+Ds7c71HcjeYbUt0+4U5W8IHB143y3OqoZy8RSL5qJQy5+xSzpnlhtGu2Ulu7KElRxjVzwlQP4Z5T5EOeOvaq4ukCRuUgx/aiQNSvLhuIiC3gVkP5K7NfROhsMom0+Xv9dF/Mgwx+xE+DNtrVCgre+yWqZpuucPg010kBc82JxuPVeFs5q07+pvDJ09hxj51THGQmOeKFHrFg==\\\"\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"sid\",\"value\":\"\\\"/1y6KRgVSwWs0R1e/aUNlw==|2KNemspcGHjWoffe4LbWxR8ib9g43Gcbo2OqpT2OxFE=\\\"\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"sst-acbuk\",\"value\":\"Sst1|PQHGzCLsLMlTs0-5bK-zB2P8C0fXNJ5EpVPRO1PPNKNhoYMUR447tg1siw4q-BlAF9lbXDPXvepKawPWz5dTORTpnpAmMEqjJxUMWuSkcvcZLhxBOpM3fzMeHimO2kjZ6jl8YNv1jmzSNqYpQTvyub-Sve-pe0JO8Wu71gz4uma8g6n6t-TlhjXfvuPFUDXexMryIc4otLcuy4Ge3DS8rRYlUzQYxi0z-rT05ES_sc7yf2YLoShMRRJM4BGfP89ePCZq6ZjoIhEouAnWgamk2Z5_0nN_jQAXd7IGN5vljmT81s6qnhyKxK1ZaPgYHzywf3k-RhyRdsGVxA78Fbdg-ZEiKQ\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"ubid-acbuk\",\"value\":\"258-2808426-0837463\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"x-acbuk\",\"value\":\"\\\"?xsKcSbWkXHiJqKrxaJBFThsK6kMLUrjQxrCMFB1F0YnYqaDMslKUsbw1qyvptbM\\\"\"},{\"domain\":\".amazon.co.uk\",\"path\":\"/\",\"secure\":true,\"name\":\"x-wl-uid\",\"value\":\"1jq6zA01etxC8r3j1liwEd0MxUdlC5W4VGUNoWiJnY1GqJzVWqRX7QsMkBRgK5X3rmWWJ4nal7bYeCmHyBOk5hw==\"}]")
				.build();
		countryMap.put(uk.getCountryCode(), uk);
	}

	public static CountryInfo getCountryInfo(String countryCode) {
		return countryMap.get(countryCode);
	}
}
