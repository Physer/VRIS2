package com.valtech.amsterdam.vris.model;

import com.valtech.amsterdam.vris.model.ITimeSlot;

/**
 * Created by jasper.van.zijp on 14-7-2017.
 */

/**
 * Interface for clickListeners
 */
public interface OnClickListener {
    /**
     * Fire this when the item is clicked
     * @param item The @ITimeSlot that is clicked
     * @param position The position of the item in the list
     */
    void onClick(ITimeSlot item, int position);
}
