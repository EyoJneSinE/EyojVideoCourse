package com.eniskaner.coursedetail.ui.util

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.eniskaner.coursedetail.R

class PaymentPopupDialog(
    context: Context,
    private val price: String,
    private val onPaymentSuccess: () -> Unit
) : AlertDialog(context) {

    override fun show() {
        val progressBar = ProgressBar(context).apply {
            visibility = View.GONE
            isIndeterminate = true
        }

        val statusTextView = TextView(context).apply {
            text = context.getString(R.string.empty_string)
            visibility = View.GONE
            textSize = 16f
            gravity = Gravity.CENTER
            setTextColor(context.getColor(android.R.color.holo_green_dark))
        }

        val payButton = Button(context).apply {
            text = context.getString(R.string.pay_now)
            setBackgroundColor(context.getColor(android.R.color.holo_orange_light))
            setTextColor(context.getColor(android.R.color.white))
            setPadding(16, 16, 16, 16)
            setOnClickListener {
                isEnabled = false
                visibility = View.GONE
                progressBar.visibility = View.VISIBLE

                Handler(Looper.getMainLooper()).postDelayed({
                    progressBar.visibility = View.GONE
                    statusTextView.visibility = View.VISIBLE
                    statusTextView.text = context.getString(R.string.payment_successful)

                    Handler(Looper.getMainLooper()).postDelayed({
                        dismiss()
                        onPaymentSuccess()
                    }, 2000)
                }, 3000)
            }
        }

        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
            gravity = Gravity.CENTER
            addView(payButton)
            addView(progressBar)
            addView(statusTextView)
        }

        setTitle(context.getString(R.string.complete_your_payment))
        setMessage(context.getString(R.string.amount, price))
        setView(container)
        super.show()
    }
}
