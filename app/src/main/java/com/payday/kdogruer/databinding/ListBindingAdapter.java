package com.payday.kdogruer.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


;import com.payday.kdogruer.data.Resource;

public final class ListBindingAdapter {
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.getData() == null)
            return;

    }
}