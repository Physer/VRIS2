package com.valtech.amsterdam.vris.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by marvin.brouwer on 24-7-2017.
 */

public final class TimeSlotList extends BaseObservable implements ObservableList<ITimeSlot>, Observable {
    private ObservableArrayList<ITimeSlot> internalList;

    public TimeSlotList(){
        internalList = new ObservableArrayList<>();
    }

    private OnPropertyChangedCallback propertyChangedCallBack = new OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
        internalList.set(internalList.indexOf(sender), (ITimeSlot) sender);
        notifyPropertyChanged(propertyId);
        notifyChange();
        }
    };

    @Override
    public void addOnListChangedCallback(OnListChangedCallback<? extends ObservableList<ITimeSlot>> callback) {
        internalList.addOnListChangedCallback(callback);
    }

    @Override
    public void removeOnListChangedCallback(OnListChangedCallback<? extends ObservableList<ITimeSlot>> callback) {
        internalList.addOnListChangedCallback(callback);
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalList.contains(o);
    }

    @NonNull
    @Override
    public Iterator<ITimeSlot> iterator() {
        return internalList.iterator();
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return internalList.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] a) {
        return internalList.toArray(a);
    }

    @Override
    public boolean add(ITimeSlot timeSlot) {
        boolean added = internalList.add(timeSlot);
        timeSlot.addOnPropertyChangedCallback(this.propertyChangedCallBack);
        notifyChange();
        return added;
    }

    @Override
    public boolean remove(Object o){
        if(!(o instanceof ITimeSlot)) return false;

        ITimeSlot timeSlot = (ITimeSlot) o;
        timeSlot.removeOnPropertyChangedCallback(this.propertyChangedCallBack);
        boolean removed = internalList.remove(timeSlot);
        notifyChange();
        return removed;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> c) {
        return internalList.containsAll(c);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends ITimeSlot> c) {
        boolean added = internalList.addAll(c);
        for(ITimeSlot timeSlot: c){
            timeSlot.addOnPropertyChangedCallback(this.propertyChangedCallBack);
        }

        notifyChange();
        return added;
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends ITimeSlot> c) {
        boolean added = internalList.addAll(index, c);
        for(ITimeSlot timeSlot: c){
            timeSlot.addOnPropertyChangedCallback(this.propertyChangedCallBack);
        }

        notifyChange();
        return added;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        for(Object o: c){
            if(!(o instanceof ITimeSlot)) return false;
            ITimeSlot timeSlot = (ITimeSlot) o;
            timeSlot.removeOnPropertyChangedCallback(this.propertyChangedCallBack);
        }
        boolean removed = internalList.removeAll(c);
        notifyChange();
        return removed;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        // throw new NoSuchMethodException();
        return false;
    }

    @Override
    public void clear() {
        for(ITimeSlot timeSlot: internalList){
            timeSlot.removeOnPropertyChangedCallback(this.propertyChangedCallBack);
        }
        internalList.clear();
        notifyChange();
    }

    @Override
    public ITimeSlot get(int index) {
        return internalList.get(index);
    }

    @Override
    public ITimeSlot set(int index, ITimeSlot timeSlot) {
        ITimeSlot setTimeSlot = internalList.set(index, timeSlot);
        timeSlot.removeOnPropertyChangedCallback(this.propertyChangedCallBack);
        timeSlot.addOnPropertyChangedCallback(this.propertyChangedCallBack);
        notifyChange();
        return setTimeSlot;
    }

    @Override
    public void add(int index, ITimeSlot timeSlot) {
        internalList.add(index, timeSlot);
        timeSlot.addOnPropertyChangedCallback(this.propertyChangedCallBack);
        notifyChange();
    }

    @Override
    public ITimeSlot remove(int index) {
        ITimeSlot timeSlot = internalList.remove(index);
        timeSlot.removeOnPropertyChangedCallback(this.propertyChangedCallBack);

        notifyChange();
        return timeSlot;
    }

    @Override
    public int indexOf(Object o) {
        return internalList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return internalList.lastIndexOf(o);
    }

    @Override
    public ListIterator<ITimeSlot> listIterator() {
        return internalList.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<ITimeSlot> listIterator(int index) {
        return internalList.listIterator(index);
    }

    @NonNull
    @Override
    public List<ITimeSlot> subList(int fromIndex, int toIndex) {
        return internalList.subList(fromIndex, toIndex);
    }
}
