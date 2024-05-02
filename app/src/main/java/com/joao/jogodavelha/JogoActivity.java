package com.joao.jogodavelha;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JogoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button0, button1, button2, button3, button4;
    private Button button5, button6, button7, button8;
    private Jogo jogo;
    private TextView textViewJogadorAtual;
    private String tabuleiro[][] = {
            {"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        setarJogadores();
    }

    private void setarJogadores() {
        Intent telaJogador = getIntent();
        jogo = (Jogo) telaJogador.getSerializableExtra("jogo");
        jogo.setJogadorAtual(jogo.getNomeJogardor1());

        TextView textViewJogadores = findViewById(R.id.textViewJogadores);
        textViewJogadorAtual = findViewById(R.id.textViewJogadorAtual);

        String jogadores = jogo.getNomeJogardor1().concat(" x ").concat(jogo.getNomeJogardor2());
        textViewJogadores.setText(jogadores);

        textViewJogadorAtual.setText("Jogando: " .concat(jogo.getJogadorAtual()));
    }


    @Override
    public void onClick(View v) {
        Log.d("BUTTON", v.getId() + "");

        if (v.getId() == R.id.button0) {
            button0.setVisibility(View.VISIBLE);
            button0.setText(jogo.getMarcacao());

            tabuleiro[0][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button1) {
            button1.setVisibility(View.VISIBLE);
            button1.setText(jogo.getMarcacao());

            tabuleiro[0][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button2) {
            button2.setVisibility(View.VISIBLE);
            button2.setText(jogo.getMarcacao());

            tabuleiro[0][2] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button3) {
            button3.setVisibility(View.VISIBLE);
            button3.setText(jogo.getMarcacao());

            tabuleiro[1][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button4) {
            button4.setVisibility(View.VISIBLE);
            button4.setText(jogo.getMarcacao());

            tabuleiro[1][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button5) {
            button5.setVisibility(View.VISIBLE);
            button5.setText(jogo.getMarcacao());

            tabuleiro[1][2] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button6) {
            button6.setVisibility(View.VISIBLE);
            button6.setText(jogo.getMarcacao());

            tabuleiro[2][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button7) {
            button7.setVisibility(View.VISIBLE);
            button7.setText(jogo.getMarcacao());

            tabuleiro[2][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button8) {
            button8.setVisibility(View.VISIBLE);
            button8.setText(jogo.getMarcacao());

            tabuleiro[2][2] = jogo.getMarcacao();
        }

        int numeroJogadas = jogo.getQtdeJogadasDisponiveis() - 1;
        jogo.setQtdeJogadasDisponiveis(numeroJogadas);

        if(jogo.getQtdeJogadasDisponiveis() == 0) {
            naoHouveGanhardor();
        } else {
            verificarGanhador();

            jogo.alterarMarcacao();
            jogo.alterarJogador();
            textViewJogadorAtual.setText("Jogando: " .concat(jogo.getJogadorAtual()));
        }
    }

    private void naoHouveGanhardor() {
        String msg = "";
        textViewJogadorAtual.setText(msg);
        Som.parar();
        Som.executar(this, R.raw.deu_velha);
    }

    private void verificarGanhador() {
        if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[0][1].equals(jogo.getMarcacao()) &&
                tabuleiro[0][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[1][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[1][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[2][0].equals(jogo.getMarcacao()) &&
                tabuleiro[2][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][0].equals(jogo.getMarcacao()) &&
                tabuleiro[2][0].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[0][1].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][1].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[0][2].equals(jogo.getMarcacao()) &&
                tabuleiro[1][2].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        } else if (tabuleiro[0][2].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][0].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
        }

        if (jogo.isHouveGanahardor()) {
            Som.parar();
            Som.executar(this, R.raw.success);
        }
    }

}