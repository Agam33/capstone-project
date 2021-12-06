package com.example.sportreservation.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.example.sportreservation.R
import com.example.sportreservation.utils.OrderStatus

class OrderButton: AppCompatButton {

    private var mState: OrderStatus = OrderStatus.PESAN

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var state: OrderStatus
        get() = mState
        set(value)  {
            mState = value
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        when(mState) {
            OrderStatus.PESAN -> {
                text = "Pesan"
                setBackgroundColor(context.getColor(R.color.purple_500))
            }
            OrderStatus.BATALKAN -> {
                text = "Batalkan"
                setBackgroundColor(context.getColor(R.color.red))
            }
            OrderStatus.SELESAI -> {}
        }
    }
}