package com.davemilligan.collection;

import java.util.function.Predicate;

public interface CriteriaPredicable {
	Predicate<Criterial> predicate();
}
