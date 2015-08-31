package com.diffbot.api.scala;

/**
 * The supported Diffbot service versions.
 */
public enum Version {
	/**
	 * Version 1, please use {@link Version#v3} instead.
	 * @deprecated Use {@link Version#v3} instead.
	 */
	@Deprecated
	v1,
	/**
	 * Version 2, please use {@link Version#v3} instead.
	 * @deprecated Use {@link Version#v3} instead.
	 */
	@Deprecated
	v2,
	/**
	 * Version 3.
	 */
	v3
}
