package example.com.test_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout

class MainActivity : AppCompatActivity() {

    private var flMainLayout: FrameLayout? = null
    private var etSearch: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setting components xml id's
        flMainLayout = findViewById(R.id.mainlayout)
        etSearch = findViewById(R.id.et_search)

        //Assign layout to FrameLayout that handle the report
        flMainLayout!!.addView(ParentLayoutT(this))
    }
}
