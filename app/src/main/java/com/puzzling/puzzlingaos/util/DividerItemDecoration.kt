package com.puzzling.puzzlingaos.util

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(private val colorIndex: Int, private val margin: Int) :
    RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val paint = Paint().apply {
            color = ContextCompat.getColor(parent.context, colorIndex)
        }
        val height = 1.dp

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (i != parent.childCount - 1) {
                c.drawRect(
                    child.left.toFloat() + margin,
                    child.bottom.toFloat(),
                    child.right.toFloat() - margin,
                    child.bottom.toFloat() + height,
                    paint,
                )
            }
        }
    }
}
