package com.diffbot.api.scala;

/**
 * The supported Diffbot service versions.
 */
public enum Version {
	/**
	 * Version 1, please use {@link Version#v2} instead.
	 * @deprecated Use {@link Version#v2} instead.
	 */
	@Deprecated
	v1,
	/**
	 * Version 2.
	 */
	v2,
	/**
	 * Version 3.
	 */
	v3
}
