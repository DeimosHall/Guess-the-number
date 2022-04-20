package com.example.guessthenumber

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AIFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a_i, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNumber = view.findViewById<EditText>(R.id.etNumberForMachine)
        val btnCheckUserNum = view.findViewById<Button>(R.id.btnCheckUserNum)
        val tvNumberFound = view.findViewById<TextView>(R.id.tvNumberFound)

        btnCheckUserNum.setOnClickListener {
            var number: Int
            try {
                number = etNumber.text.toString().toInt()
                findNumber(number = number, textView = tvNumberFound)
                it.hideKeyboard()
            } catch (e: Exception) {
                showToast()
            }
        }
    }

    private fun showToast() {
        Toast.makeText(activity,"Enter a number", Toast.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AIFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AIFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun findNumberOneByOne(number: Int, textView: TextView) {
        var attemps = 0
        for (i in 0..100) {
            when (i) {
                number -> {
                    textView.text = "Your number is ${i}, it tooks ${attemps} tries to the machine"
                    break
                }
                else -> attemps++
            }
        }
    }

    private fun findNumber(number: Int, textView: TextView) {
        var counter = 0
        // Loop with 1 sec steps that finishes at 9 secs
        object: CountDownTimer(5000,500) {
            override fun onTick(p0: Long) {
                when (counter) {
                    1 -> textView.text = "Searching."
                    2 -> textView.text = "Searching.."
                    3 -> textView.text = "Searching..."
                    4 -> textView.text = "Searching."
                    5 -> textView.text = "Searching.."
                    6 -> textView.text = "Searching..."
                    7 -> textView.text = "Searching."
                    8 -> textView.text = "Searching.."
                    9 -> textView.text = "Searching..."
                }
                counter++
            }

            override fun onFinish() {
                findNumberOneByOne(number,textView)
            }
        }.start()
    }

    fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken,0)
    }
}