package edu.cqu.common;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public class EntitySupport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void SetEntityByRequest(Entity e, HttpServletRequest request) {
		if (e != null) {
			Field[] fields = e.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {

				for (Field f : fields) {
					try {
						f.set(e, request.getParameter(f.getName()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

		}

	}

}
