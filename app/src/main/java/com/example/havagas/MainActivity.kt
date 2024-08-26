package com.example.havagas

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var formacao = ""
    private var dataSelecionada = ""
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        var diaDoMes = amb.dataNascimentoDp.dayOfMonth
        var mes = amb. dataNascimentoDp.month
        var ano = amb.dataNascimentoDp.year

        dataSelecionada = String.format("%s / %s / %s", diaDoMes, mes, ano)

        amb.limparBt.setOnClickListener {
            with (amb) {
                nomeCompletoEt.setText("")
                emailEt.setText("")
                listaEmailCb.isChecked = false
                telefoneEt.setText("")
                telefoneCelularCb.isChecked = false
                telefoneCelularEt.setText("")
                sexoRg.clearCheck()
                dataSelecionadaTv.setText("")
                formacaoSp.setSelection(0)
                anoConclusaoEt.setText("")
                instituicaoEt.setText("")
                tituloMonografiaEt.setText("")
                nomeOrientadorEt.setText("")
                vagasInteresseEt.setText("")
            }
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
                System.out.println(formacao)
                with (amb) {
                    if (formacao == "fundamental" || formacao == "ensino medio") {
                        anoFormaturaTv.visibility = View.VISIBLE
                        anoFormaturaEt.visibility = View.VISIBLE
                    } else {
                        anoFormaturaTv.visibility = View.GONE
                        anoFormaturaEt.visibility = View.GONE
                        anoFormaturaEt.setText("")
                    }


                    if (formacao == "graduacao" || formacao == "especializacao" || formacao == "mestrado" || formacao == "doutorado") {
                        anoConclusaoTv.visibility = View.VISIBLE
                        anoConclusaoEt.visibility = View.VISIBLE

                        instituicaoTv.visibility = View.VISIBLE
                        instituicaoEt.visibility = View.VISIBLE
                    } else{
                        anoConclusaoTv.visibility = View.GONE
                        anoConclusaoEt.visibility = View.GONE
                        anoConclusaoEt.setText("")

                        instituicaoTv.visibility = View.GONE
                        instituicaoEt.visibility = View.GONE
                        instituicaoEt.setText("")

                    }


                    if (formacao == "mestrado" || formacao == "doutorado") {
                        nomeOrientadorTv.visibility = View.VISIBLE
                        nomeOrientadorEt.visibility = View.VISIBLE

                        tituloMonografiaTv.visibility = View.VISIBLE
                        tituloMonografiaEt.visibility = View.VISIBLE
                    } else {
                        anoConclusaoEt.setText("")
                        instituicaoEt.setText("")

                        nomeOrientadorTv.visibility = View.GONE
                        nomeOrientadorEt.visibility = View.GONE
                        nomeOrientadorEt.setText("")


                        tituloMonografiaTv.visibility = View.GONE
                        tituloMonografiaEt.visibility = View.GONE
                        tituloMonografiaEt.setText("")
                    }
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

                val formacao = formacaoSp.selectedItem.toString()
                var anoFormacao = anoConclusaoEt.text.toString()
                var  anoConlusao = anoConclusaoEt.text.toString()
                var instituicao = instituicaoEt.text.toString()
                var tituloMonografia = tituloMonografiaEt.text.toString()
                var nomeOrientador = nomeOrientadorEt.text.toString()

                val vagasInteresse = vagasInteresseEt.text.toString()

                var mensagem = String.format("####   Dados Salvos   ####\n\n" +
                        "nomeCompleto: %s \n"+
                        "email: %s \n" +
                        "participar da lista: %s \n" +
                        "telefone: %s \n" +
                        "telefoneCelular: %s"+
                        "sexo: %s \n" +
                        "formacao: %s \n" +
                        "ano formacao: %s \n" +
                        "ano conclusao: %s \n" +
                        "instituicao: %s \n" +
                        "titulo monografia: %s \n" +
                        "nome orientador: %s \n" +
                        "vagasInteresse: %s \n",
                    nomeCompleto, email, listaEmail, telefone, telefoneCelular,
                    sexo, formacao, anoFormacao, anoConlusao, instituicao,
                    tituloMonografia, nomeOrientador, vagasInteresse)

                Toast.makeText(this@MainActivity, mensagem, Toast.LENGTH_SHORT).show()
            }
        }

    }
}