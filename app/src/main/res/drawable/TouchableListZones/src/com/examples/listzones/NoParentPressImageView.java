/**
 * Created on Aug 27, 2011 by Dave Smith
 * Wireless Designs, LLC
 *
 * NoParentPressImageView.java
 * ImageView that ignores press state changes from the parent
 */
package com.examples.listzones;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class NoParentPressImageView extends ImageView {
    
    public NoParentPressImageView(Context context) {
        this(context, null);
    }
    
    public NoParentPressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public void setPressed(boolean pressed) {
        // If the parent is pressed, do not set to pressed.
        if (pressed && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }
}
