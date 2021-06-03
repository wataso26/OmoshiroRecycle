package app.wataso_.watanabe.omoshirorecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.genre_fragment_custom_dialog.*
import kotlinx.android.synthetic.main.genre_fragment_custom_dialog.view.*
import kotlinx.android.synthetic.main.item_data_cell.*
import kotlinx.android.synthetic.main.toko.*

class GenreCustomDialogFragment:DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(R.layout.genre_fragment_custom_dialog,container,false)

        rootView.genre_cancel_button.setOnClickListener {
            dismiss()
        }
        rootView.genre_submitButton.setOnClickListener {
            val selectedID =radioGroup.checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedID)

            var ratingResult = radio.text.toString()

            Toast.makeText(context,"You rated: $ratingResult",Toast.LENGTH_LONG).show()

            dismiss()//参考

        }

        return rootView

    }

}
