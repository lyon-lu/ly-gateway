package com.study.gateway.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class ObjectUtil {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final List<Class<?>> SIMPLE_TYPE = new ArrayList(3);

	static {
		SIMPLE_TYPE.add(String.class);
		SIMPLE_TYPE.add(Boolean.TYPE);
		SIMPLE_TYPE.add(Integer.TYPE);
	}

	public static boolean isEmpty(Object[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static boolean isEmpty(Collection<?> list) {
		return ((list == null) || (list.isEmpty()));
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return ((map == null) || (map.isEmpty()));
	}

	public static boolean isNotEmpty(Collection<?> list) {
		return ((list != null) && (!(list.isEmpty())));
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return ((map != null) && (!(map.isEmpty())));
	}

	@SuppressWarnings("rawtypes")
	public static int size(Object obj) {
		if (obj == null) {
			return 0;
		}

		if (obj instanceof Collection) {
			return ((Collection) obj).size();
		}

		if (obj.getClass().isArray()) {
			return ((Object[]) obj).length;
		}
		return 0;
	}
}