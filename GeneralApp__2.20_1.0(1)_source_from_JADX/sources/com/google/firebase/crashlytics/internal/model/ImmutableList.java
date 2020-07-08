package com.google.firebase.crashlytics.internal.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-crashlytics@@17.0.0-beta04 */
public final class ImmutableList<E> implements List<E>, RandomAccess {
    private final List<E> immutableList;

    public static <E> ImmutableList<E> from(E... elements) {
        return new ImmutableList<>(Arrays.asList(elements));
    }

    public static <E> ImmutableList<E> from(List<E> mutableList) {
        return new ImmutableList<>(mutableList);
    }

    private ImmutableList(List<E> mutableList) {
        this.immutableList = Collections.unmodifiableList(mutableList);
    }

    public int size() {
        return this.immutableList.size();
    }

    public boolean isEmpty() {
        return this.immutableList.isEmpty();
    }

    public boolean contains(Object o) {
        return this.immutableList.contains(o);
    }

    public Iterator<E> iterator() {
        return this.immutableList.iterator();
    }

    public Object[] toArray() {
        return this.immutableList.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return this.immutableList.toArray(a);
    }

    public boolean add(E e) {
        return this.immutableList.add(e);
    }

    public boolean remove(Object o) {
        return this.immutableList.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return this.immutableList.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return this.immutableList.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return this.immutableList.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return this.immutableList.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.immutableList.retainAll(c);
    }

    public void clear() {
        this.immutableList.clear();
    }

    public boolean equals(Object o) {
        return this.immutableList.equals(o);
    }

    public int hashCode() {
        return this.immutableList.hashCode();
    }

    public E get(int index) {
        return this.immutableList.get(index);
    }

    public E set(int index, E element) {
        return this.immutableList.set(index, element);
    }

    public void add(int index, E element) {
        this.immutableList.add(index, element);
    }

    public E remove(int index) {
        return this.immutableList.remove(index);
    }

    public int indexOf(Object o) {
        return this.immutableList.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return this.immutableList.lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return this.immutableList.listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return this.immutableList.listIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return this.immutableList.subList(fromIndex, toIndex);
    }
}
