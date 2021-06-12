package app.wataso_.watanabe.omoshirorecycle.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import app.wataso_.watanabe.omoshirorecycle.R
import kotlinx.android.synthetic.main.phrase_custom_dialog.view.*

class CustomDialogFragment:DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.phrase_custom_dialog,container,false)


        rootView.cancel_button.setOnClickListener {
            dismiss()
        }
        rootView.radio1.setOnClickListener{
            Toast.makeText(context,"トゥース！",Toast.LENGTH_SHORT).show()
        }
        rootView.radio2.setOnClickListener{
            Toast.makeText(context,"なんて日だ",Toast.LENGTH_SHORT).show()
        }
        rootView.radio3.setOnClickListener{
            Toast.makeText(context,"君のハートにレボリューション★",Toast.LENGTH_SHORT).show()
        }
        rootView.radio4.setOnClickListener{
            Toast.makeText(context,"安心してください、履いてますよ",Toast.LENGTH_SHORT).show()
        }
        rootView.radio5.setOnClickListener{
            Toast.makeText(context,"35億",Toast.LENGTH_SHORT).show()
        }
        rootView.radio6.setOnClickListener{
            Toast.makeText(context,"あったかいん～だから～",Toast.LENGTH_SHORT).show()
        }
        rootView.radio7.setOnClickListener{
            Toast.makeText(context,"ゲッツ",Toast.LENGTH_SHORT).show()
        }

        return rootView
        //textView6.text = radio.text.toString()

    }
}