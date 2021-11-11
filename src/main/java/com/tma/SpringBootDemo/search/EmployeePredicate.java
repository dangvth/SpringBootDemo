/**
 * 
 */
package com.tma.SpringBootDemo.search;

import com.querydsl.core.types.Predicate;
import com.tma.SpringBootDemo.entity.QEmployee;

/**
 * @author dangv
 *
 */
public class EmployeePredicate {

	/**
	 * Predicate search name
	 * 
	 * @param name the name to set predicate
	 * @return the {@link Predicate}
	 */
	public static Predicate nameContain(String name) {
		return QEmployee.employee.firstName.containsIgnoreCase(name)
				.or(QEmployee.employee.lastName.containsIgnoreCase(name));
	}
}
