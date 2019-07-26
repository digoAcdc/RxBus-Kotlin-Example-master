package therajanmaurya.rxbus.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private lateinit var personDisposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        personDisposable = RxBus.listen(RxEvent.EventAddPerson::class.java).subscribe {
            //adapter.addPerson(person = it.personName)
            tv.text = it.personName
        }

        bt.setOnClickListener {
            finish()
        }
        startActivity(Intent(this, AddPersonActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!personDisposable.isDisposed) personDisposable.dispose()
    }
}