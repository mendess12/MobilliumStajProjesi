package com.yusufmendes.basicnoteapp.util.swipe

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.yusufmendes.basicnoteapp.util.listener.MyButtonClickListener

class MyButton(
    private val context: Context,
    private val text : String,
    private val textSize : Int,
    private val imageResId: Int,
    private val color: Int,
    private val listener: MyButtonClickListener,
) {

    private var position: Int = 0
    private var clickRegion: RectF? = null
    private val resources: Resources

    init {
        resources = context.resources
    }

    fun onClick(x: Float, y: Float): Boolean {
        if (clickRegion != null && clickRegion!!.contains(x, y)) {
            listener.onClick(position)
            return true
        }
        return false
    }

    fun onDraw(c: Canvas, rectF: RectF, position: Int) {
        val p = Paint()
        p.color = color
        c.drawRect(rectF, p)

        // Text

        p.color = Color.WHITE
        p.textSize = textSize.toFloat()

        val r = Rect()
        val cHeight = rectF.height()
        val cWidth = rectF.width()
        p.textAlign = Paint.Align.LEFT
        p.getTextBounds(text,0,text.length,r)
        var x = 0f
        var y = 0f
        if (imageResId == 0){
            x = cWidth / 2f - r.width() / 2f - r.left.toFloat()
            y = cHeight / 2f + r.height() / 2f - r.bottom.toFloat()
            c.drawText(text, rectF.left+x,rectF.top+y,p)

        }else{
            val d = ContextCompat.getDrawable(context,imageResId)
            val bitmap = drawbleToBitmap(d)
            c.drawBitmap(bitmap,(rectF.left+rectF.right)/2,(rectF.top+rectF.bottom)/2, p)
        }

        clickRegion = rectF
        this.position = position

    }

    private fun drawbleToBitmap(d: Drawable?) : Bitmap{
        if (d is  BitmapDrawable) return d.bitmap
        val bitmap = Bitmap.createBitmap(d!!.intrinsicWidth, d.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        d.setBounds(0,0,canvas.width,canvas.height)
        d.draw(canvas)
        return bitmap
    }
}
