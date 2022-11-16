package uz.saidarabxon.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var bosilish = false
    private var nuqtauchun = true
    private var arrayMisolBelgi = ArrayList<String>()
    private var stringMisol = ""
    private var stringBelgi = ""
    private var stringExemple = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_backspace.setOnClickListener {
            if (stringExemple != "") {
                var nuqtaUchun =
                    stringExemple.substring(stringExemple.length - 1, stringExemple.length)
                stringExemple = stringExemple.substring(0, stringExemple.length - 1)
                tv_example.text = stringExemple
                if (nuqtaUchun == ".") {
                    nuqtauchun = true
                }
                if (nuqtaUchun == "×" || nuqtaUchun == "÷" || nuqtaUchun == "+" || nuqtaUchun == "-") {
                    var arrayList = ArrayList<String>()
                    var string = ""
                    for (k in stringExemple) {

                        if (k == '×' || k == '÷' || k == '+' || k == '-') {
                            arrayList.add(string)
                            arrayList.add(k.toString())
                            string = ""
                        }

                        if (k != '×' && k != '÷' && k != '+' && k != '-') {
                            string += k
                        }

                    }
                    arrayList.add(string)
                    stringMisol = arrayList[arrayList.size - 1]
                    stringBelgi = ""
                }
                if (nuqtaUchun != "×" && nuqtaUchun != "÷" && nuqtaUchun != "+" && nuqtaUchun != "-") {
                    stringMisol = stringMisol.substring(0, stringMisol.length - 1)
                }
            }
        }
        btn_equalsSign.setOnClickListener {
            if (stringExemple.isNotEmpty()) {
                if (stringExemple[stringExemple.length - 1] == '×' || stringExemple[stringExemple.length - 1] == '÷' || stringExemple[stringExemple.length - 1] == '+' || stringExemple[stringExemple.length - 1] == '-') {
                    stringExemple = stringExemple.substring(0, stringExemple.length - 1)
                }
                BuildArray()
                NatijaChiqarish()
            }
        }

        btn_ac.setOnClickListener {
            nuqtauchun = true
            bosilish = false
            arrayMisolBelgi.clear()
            stringMisol = ""
            stringBelgi = ""
            stringExemple = ""
            tv_example.text = ""
            tv_table.text = "0"
        }

        btn_percent.setOnClickListener {
            if (stringBelgi == "" && stringExemple != "" && stringMisol != "0") {
                FoiziniHisoblash()
            }
        }

        btn_plus.setOnClickListener {
            BelgiQoshish("+")
        }

        btn_minus.setOnClickListener {
            BelgiQoshish("-")
        }

        btn_divisionSign.setOnClickListener {
            BelgiQoshish("÷")
        }

        btn_multiplicationSign.setOnClickListener {
            BelgiQoshish("×")
        }

        btn_1.setOnClickListener {
            RaqamQoshish("1")
        }

        btn_2.setOnClickListener {
            RaqamQoshish("2")
        }

        btn_3.setOnClickListener {
            RaqamQoshish("3")
        }

        btn_4.setOnClickListener {
            RaqamQoshish("4")
        }

        btn_5.setOnClickListener {
            RaqamQoshish("5")
        }

        btn_6.setOnClickListener {
            RaqamQoshish("6")
        }

        btn_7.setOnClickListener {
            RaqamQoshish("7")
        }

        btn_8.setOnClickListener {
            RaqamQoshish("8")
        }

        btn_9.setOnClickListener {
            RaqamQoshish("9")
        }

        btn_0.setOnClickListener {
            if (stringMisol != "0") {
                RaqamQoshish("0")
            }
        }

        btn_point.setOnClickListener {
            if (nuqtauchun) {
                if (stringMisol != "") {
                    RaqamQoshish(".")
                    nuqtauchun = false
                }
                if (stringMisol == "") {
                    RaqamQoshish("0.")
                    nuqtauchun = false
                }
            }
        }

    }

    fun RaqamQoshish(string: String) {
        if (stringMisol == "0" && string != ".") {
            stringExemple = if (stringExemple.length > 1) {
                stringExemple.substring(0, stringExemple.length - 1)
            } else {
                ""
            }
            stringMisol = ""
        }
        bosilish = true
        stringExemple += string
        stringMisol += string
        tv_example.text = stringExemple
        tv_table.text = "0"
        if (stringBelgi != "") {
            (stringBelgi)
            stringBelgi = ""
        }
    }

    fun BelgiQoshish(string: String) {
        if (!bosilish) {
            stringExemple += tv_table.text
            (tv_table.text.toString())
        }
        bosilish = true
        nuqtauchun = true
        if (stringBelgi == "") {
            stringExemple += string
            stringBelgi += string
        }
        if (stringBelgi != "") {
            stringExemple = "${stringExemple.substring(0, stringExemple.length - 1)}$string"
            stringBelgi = string
        }
        tv_example.text = stringExemple
        tv_table.text = "0"
        if (stringMisol != "") {
            (stringMisol)
            stringMisol = ""
        }
    }

    private fun NatijaChiqarish() {

        if (stringMisol != "") {
            (stringMisol)
        }

        if (bosilish) {
            if (arrayMisolBelgi.size - 1 > 0) {
                Hisoblash()
            } else {
                if (arrayMisolBelgi[0] != "×" || arrayMisolBelgi[0] != "÷" || arrayMisolBelgi[0] != "+" || arrayMisolBelgi[0] != "-") {
                    tv_table.text = arrayMisolBelgi[0]
                } else {
                    stringExemple = ""
                    tv_example.text = ""
                    tv_table.text = "0"
                }
            }
        } else {
            stringExemple = ""
            tv_example.text = ""
            tv_table.text = "0"
        }
        nuqtauchun = true
        bosilish = false
        arrayMisolBelgi.clear()
        stringMisol = ""
        stringBelgi = ""
        stringExemple = ""

    }

    private fun FoiziniHisoblash() {
        if (stringMisol.toDouble() / 100 > 0.0000009) {
            var int = stringMisol.length
            var string = ""
            stringMisol = (stringMisol.toFloat() / 100).toString()
            string = (stringExemple.reversed().substring(int, stringExemple.length)).reversed()
            stringExemple = "$string$stringMisol"
            tv_example.text = stringExemple
        }
    }

    private fun BuildArray() {
        var stringBuilder = ""
        var k = 0

        if (stringExemple[0] == '-') {
            stringExemple = ((stringExemple.reversed() + "0")).reversed()
        }


        while (true) {

            if (stringExemple[k] == 'E') {
                while (true) {
                    if (k == stringExemple.length) {
                        break
                    }
                    if (stringExemple[k] == '×' || stringExemple[k] == '+' || stringExemple[k] == '÷') {
                        break
                    }
                    stringBuilder += stringExemple[k]
                    k++
                }
            }

            if (k == stringExemple.length) {
                break
            }

            if (stringExemple[k] == '×' || stringExemple[k] == '÷' || stringExemple[k] == '+' || stringExemple[k] == '-') {
                arrayMisolBelgi.add(stringBuilder)
                arrayMisolBelgi.add(stringExemple[k].toString())
                stringBuilder = ""
            }

            if (stringExemple[k] != '×' && stringExemple[k] != '÷' && stringExemple[k] != '+' && stringExemple[k] != '-') {
                stringBuilder += stringExemple[k].toString()
            }

            if (k == stringExemple.length - 1) {
                break
            }
            k++
        }
        arrayMisolBelgi.add(stringBuilder)
    }

    private fun Hisoblash() {
        var i = 0
        var int = 0
        var belgi = 0
        var booleanBelgi = true

        while (true) {
            var boolean = false
            if (booleanBelgi) {
                for (k in 0 until arrayMisolBelgi.size) {
                    if (arrayMisolBelgi[k] == "×" || arrayMisolBelgi[k] == "÷") {
                        belgi++
                    }
                }
                booleanBelgi = false
            }
            if (arrayMisolBelgi[i] == "×") {
                var birinchiSon = arrayMisolBelgi[i - 1].toDouble()
                var ikkinchiSon = arrayMisolBelgi[i + 1].toDouble()
                var javob = 0.0
                javob = birinchiSon * ikkinchiSon
                arrayMisolBelgi[i - 1] = javob.toString()
                int = i
                boolean = true
            }

            if (arrayMisolBelgi[i] == "÷") {
                var birinchiSon = arrayMisolBelgi[i - 1].toDouble()
                var ikkinchiSon = arrayMisolBelgi[i + 1].toDouble()
                var javob = 0.0
                javob = birinchiSon / ikkinchiSon
                arrayMisolBelgi[i - 1] = javob.toString()
                int = i
                boolean = true
            }

            if (boolean) {
                i = 0
                arrayMisolBelgi.removeAt(int)
                arrayMisolBelgi.removeAt(int)
                belgi--
                boolean = false
            }
            if (belgi == 0) {

                booleanBelgi = true
                i = 0
                int = 0
                break
            }
            i++
        }

        while (true) {

            var boolean = false

            if (booleanBelgi) {
                for (k in 0 until arrayMisolBelgi.size) {
                    if (arrayMisolBelgi[k] == "+" || arrayMisolBelgi[k] == "-") {
                        belgi++
                    }
                }
                booleanBelgi = false
            }

            if (arrayMisolBelgi[i] == "+") {
                var birinchiSon = arrayMisolBelgi[i - 1].toDouble()
                var ikkinchiSon = arrayMisolBelgi[i + 1].toDouble()
                var javob = 0.0
                javob = birinchiSon + ikkinchiSon
                arrayMisolBelgi[i - 1] = javob.toString()
                int = i
                boolean = true
            }

            if (boolean) {
                i = 0
                arrayMisolBelgi.removeAt(int)
                arrayMisolBelgi.removeAt(int)
                belgi--
                boolean = false
            }

            if (arrayMisolBelgi[i] == "-") {
                var birinchiSon = arrayMisolBelgi[i - 1].toDouble()
                var ikkinchiSon = arrayMisolBelgi[i + 1].toDouble()
                var javob = 0.0
                javob = birinchiSon - ikkinchiSon
                arrayMisolBelgi[i - 1] = javob.toString()
                int = i
                boolean = true
            }

            if (boolean) {
                i = 0
                arrayMisolBelgi.removeAt(int)
                arrayMisolBelgi.removeAt(int)
                belgi--
                boolean = false
            }

            if (belgi == 0) {
                booleanBelgi = true
                i = 0
                int = 0
                break
            }
            i++
        }
        tv_table.text = arrayMisolBelgi[0].toFloat().toString()
    }
}