package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        amb.telefoneCelularCb.setOnClickListener {
            if (amb.telefoneCelularCb.isChecked) {
                amb.telefoneCelularTv.visibility = View.VISIBLE
                amb.telefoneCelularEt.visibility = View.VISIBLE
            } else {
                amb.telefoneCelularTv.visibility = View.GONE
                amb.telefoneCelularEt.visibility = View.GONE

            }
        }

        amb.dataNascimentoDp.setOnDateChangedListener { _, year, mouth, day ->
            dataSelecionada = String.format("%s / %s / %s", day, mouth, year)
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

                val formacao = when (formacaoSp.selectedItem.toString()) {
                    "" -> "Indefinido"
                    else -> formacaoSp.selectedItem.toString()
                }
                var anoFormacao = when (anoConclusaoEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> anoConclusaoEt.text.toString()
                }
                var anoConlusao = when (anoConclusaoEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> anoConclusaoEt.text.toString()
                }
                var instituicao = when (instituicaoEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> instituicaoEt.text.toString()
                }
                var tituloMonografia = when (tituloMonografiaEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> tituloMonografiaEt.text.toString()
                }
                var nomeOrientador = when (nomeOrientadorEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> nomeOrientadorEt.text.toString()
                }
                var vagasInteresse = when (vagasInteresseEt.text.toString()) {
                    "" -> "Indefinido"
                    else -> vagasInteresseEt.text.toString()
                }

                var mensagem = String.format("####   Dados Salvos   ####\n\n" +
                        "nomeCompleto: %s \n"+
                        "email: %s \n" +
                        "participar da lista: %s \n" +
                        "telefone: %s \n" +
                        "telefoneCelular: %s \n"+
                        "sexo: %s \n" +
                        "data nascimento: %s \n" +
                        "formacao: %s \n" +
                        "ano formacao: %s \n" +
                        "ano conclusao: %s \n" +
                        "instituicao: %s \n" +
                        "titulo monografia: %s \n" +
                        "nome orientador: %s \n" +
                        "vagasInteresse: %s \n",
                    nomeCompleto, email, listaEmail, telefone, telefoneCelular,
                    sexo, dataSelecionada, formacao, anoFormacao, anoConlusao, instituicao,
                    tituloMonografia, nomeOrientador, vagasInteresse)

                Toast.makeText(this@MainActivity, mensagem, Toast.LENGTH_SHORT).show()
            }
        }

    }
}