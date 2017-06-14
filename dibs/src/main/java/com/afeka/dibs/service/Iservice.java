package com.afeka.dibs.service;
import java.util.List;
@SuppressWarnings("unchecked")

public interface Iservice<T> {
	public List<T> add (T... objects);
	public List<T> getAll();
}
