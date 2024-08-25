package com.example.havagas

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var formacao = ""
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.limparBt.setOnClickListener {
            with (amb) {
                nomeCompletoEt.setText("")
                emailEt.setText("")
                listaEmailCb.isChecked = false
                telefoneEt.setText("")
                telefoneCelularCb.isChecked = false
                telefoneCelularEt.setText("")
                sexoRg.clearCheck()
                //TODO logica de 'limpar' dte Picker
                formacaoSp.setSelection(0)
                anoConclusaoEt.setText("")
                instituicaoEt.setText("")
                tituloMonografiaEt.setText("")
                nomeOrientadorEt.setText("")
                vagasInteresseEt.setText("")
            }

            amb.formacaoSp.onItemSelectedListener = object: OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?,
                                            view: View?,
                                            position: Int,
                                            id: Long) {
                    formacao = when(position) {
                        0 -> "Indefinido"
                        else -> amb.formacaoSp.selectedItem.toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //NSA
                }
            }

            amb.salvaBt.setOnClickListener {
                with(amb) {
                    val nomeCompleto =  nomeCompletoEt.text.toString()
                    val email = emailEt.text.toString()
                    val listaEmail = when (listaEmailCb.isChecked) {
                        true -> "Tem interesse em recever emails com oportunidades"
                        false -> "Não tem interesse em recever emails com oportunidades"
                    }
                    val telefone = telefoneEt.text.toString()
                    val telefoneCelular = when (telefoneCelularCb.isChecked) {
                        false -> "Indefinido"
                        true -> telefoneCelularEt.text.toString()
                    }
                    val selectedSexoId = sexoRg.checkedRadioButtonId
                    val sexo = when (selectedSexoId) {
                        R.id.masculinoRb -> "Masculino"
                        R.id.femininoRb -> "Feminino"
                        else -> "Indefinido"
                    }
                    // Todo pegar data de nascimento
                    //TODO restante da coleta de dados
                    /*
                    formacaoSp.setSelection(0)
                    anoConclusaoEt.setText("")
                    instituicaoEt.setText("")
                    tituloMonografiaEt.setText("")
                    nomeOrientadorEt.setText("")
                    vagasInteresseEt.setText("")
                    */
                     */
                }
            }


        }
    }
}