package vn.com.hieu.registerPage.utils;

import java.util.Map;

public final class CommonUtils {
	
	private CommonUtils() {
		
	}

	public static <K, V>  String convertOptionMapToJsList(Map<K, V> map) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		map.forEach((k, v) -> builder.append("{\"name\":\""+ k.toString() +"\",\"value\":\""+ v.toString() +"\"},"));
		builder.append("]");
		int lastComma = builder.lastIndexOf(",");
		if(lastComma != -1) {
			builder.replace(lastComma, lastComma + 1, "");
		}
		return builder.toString();
	}
	
}
