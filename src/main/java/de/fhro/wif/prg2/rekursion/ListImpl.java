package de.fhro.wif.prg2.rekursion;

import de.fhro.wif.prg2.iteratoren.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl<T> implements List<T> {
	protected class Element {
		T value;
		Element next;

		Element(T value) { this.value = value; }

		int size() {
			if (next == null)
				return 1;
			else
				return 1 + next.size();
		}

		@Override
		public String toString() {
			if (next == null)
				return value.toString();
			else
				return value.toString() + ", " + next.toString();
		}
	}

	protected Element first = null;

	@Override
	public String toString() {
		/*
		// iterative Variante:
		String s = "[";
		Element it = first;

		while (it != null) {
			s = s + it.value.toString();
			it = it.next;

			if (it != null)
				s = s + ", ";
		}

		return s + "]";
		*/

		// rekursive variante:
		if (first == null)
			return "[]";
		else
			return "[" + first.toString() + "]";
	}

	@Override
	public void add(T value) {
		if (first == null) {
			first = new Element(value);
			return;
		}

		Element it = first;
		while (it.next != null)
			it = it.next;

		it.next = new Element(value);
	}

	@Override
	public T get(int i) {
		if (first == null)
			throw new NoSuchElementException();

		Element it = first;
		for (int j = 0; j < i; j++) {
			it = it.next;

			if (it == null)
				throw new NoSuchElementException();
		}

		return it.value;
	}

	@Override
	public T remove(int i) {
		if (first == null)
			throw new NoSuchElementException();
		if (i == 0) {
			T value = first.value;
			first = first.next;
			return value;
		}

		Element it = first;

		for (int j = 0; j < i - 1; j++) {
			it = it.next;

			if (it == null)
				throw new NoSuchElementException();
		}

		if (it.next == null)
			throw new NoSuchElementException();

		T wert = it.next.value;

		it.next = it.next.next;
		return wert;
	}

	@Override
	public void insert(int i, T o) {
		if (first == null || i == 0) {
			Element alt = first;
			first = new Element(o);
			first.next = alt;
			return;
		}

		Element it = first;

		for (int j = 0; j < i-1; j++) {
			it = it.next;
			if (it == null)
				throw new NoSuchElementException();
		}

		Element hilfe = it.next;
		it.next = new Element(o);
		it.next.next = hilfe;
	}

	@Override
	public int size() {
		if (first == null)
			return 0;
		else
			return first.size();
	}

	@Override
	public boolean contains(T t) {
		if (first == null)
			return false;

		Element it = first;
		while (it != null) {
			if (it.value.equals(t))
				return true;
			it = it.next;
		}

		// nicht gefunden!
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Element it = first;
			@Override
			public boolean hasNext() {
				return it != null;
			}

			@Override
			public T next() {
				T h = it.value;
				it = it.next;
				return h;
			}
		};
	}
}
