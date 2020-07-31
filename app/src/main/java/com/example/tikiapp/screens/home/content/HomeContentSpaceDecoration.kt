package com.example.tikiapp.screens.home.content

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeContentSpaceDecoration(
    private val height: Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = height
    }

}