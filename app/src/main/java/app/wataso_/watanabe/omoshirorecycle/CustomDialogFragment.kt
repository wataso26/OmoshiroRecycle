package app.wataso_.watanabe.omoshirorecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.franment_custom_dialog.*
import kotlinx.android.synthetic.main.franment_custom_dialog.view.*
import kotlinx.android.synthetic.main.setting.*
import org.w3c.dom.Text

class CustomDialogFragment:DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.franment_custom_dialog,container,false)


        rootView.cancel_button.setOnClickListener {
            dismiss()
        }
        rootView.submitButton.setOnClickListener {
            val selectedID =ratingRadioGroup.checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedID)

            var ratingResult = radio.text.toString()

            Toast.makeText(context,"You chose $ratingResult",Toast.LENGTH_LONG).show()

            dismiss()
            //テキストビューに選択した曜日を表示



        }

        return rootView
        //textView6.text = radio.text.toString()

    }
}