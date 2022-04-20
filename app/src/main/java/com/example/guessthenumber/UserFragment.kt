package com.example.guessthenumber

import android.content.Context
import android.os.Bundle
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
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var randomNumber = Random.nextInt(0,100)

        val etNumber = view.findViewById<EditText>(R.id.etNumberForUser)
        val btnCheck = view.findViewById<Button>(R.id.btnCheck)
        val tvFinalMessage = view.findViewById<TextView>(R.id.tvFinalMessage)
        val btnReset = view.findViewById<Button>(R.id.btnReset)

        fun numFound() {
            tvFinalMessage.text = "Congratulations, you found the number ${randomNumber}"
        }

        fun smallerNum() = "Try a smaller one".also { tvFinalMessage.text = it }

        fun biggerNum() = "Try a bigger one".also { tvFinalMessage.text = it }

        btnCheck.setOnClickListener {
            var number: Int

            try {
                number = etNumber.text.toString().toInt()

                when {
                    number == randomNumber -> {
                        numFound()
                        it.hideKeyboard()
                    }
                    number > randomNumber -> smallerNum()
                    number < randomNumber -> biggerNum()
                }
            } catch (e: Exception) {
                showToast()
            }
        }

        btnReset.setOnClickListener {
            randomNumber = Random.nextInt(0,100)
            tvFinalMessage.text = ""
            etNumber.text.clear()
        }
    }

    private fun showToast() {
        Toast.makeText(activity,"Enter a number",Toast.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken,0)
    }
}