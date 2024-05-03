package com.joao.jogodavelha;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
        Log.d("BUTTON", "Botão clicado: " + v.getId());

        if (v.getId() == R.id.button0) {
            button0.setVisibility(View.VISIBLE);
            button0.setText(jogo.getMarcacao());
            button0.setClickable(false);

            tabuleiro[0][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button1) {
            button1.setVisibility(View.VISIBLE);
            button1.setText(jogo.getMarcacao());
            button1.setClickable(false);

            tabuleiro[0][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button2) {
            button2.setVisibility(View.VISIBLE);
            button2.setText(jogo.getMarcacao());
            button2.setClickable(false);

            tabuleiro[0][2] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button3) {
            button3.setVisibility(View.VISIBLE);
            button3.setText(jogo.getMarcacao());
            button3.setClickable(false);

            tabuleiro[1][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button4) {
            button4.setVisibility(View.VISIBLE);
            button4.setText(jogo.getMarcacao());
            button4.setClickable(false);

            tabuleiro[1][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button5) {
            button5.setVisibility(View.VISIBLE);
            button5.setText(jogo.getMarcacao());
            button5.setClickable(false);

            tabuleiro[1][2] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button6) {
            button6.setVisibility(View.VISIBLE);
            button6.setText(jogo.getMarcacao());
            button6.setClickable(false);

            tabuleiro[2][0] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button7) {
            button7.setVisibility(View.VISIBLE);
            button7.setText(jogo.getMarcacao());
            button7.setClickable(false);

            tabuleiro[2][1] = jogo.getMarcacao();
        } else if (v.getId() == R.id.button8) {
            button8.setVisibility(View.VISIBLE);
            button8.setText(jogo.getMarcacao());
            button8.setClickable(false);

            tabuleiro[2][2] = jogo.getMarcacao();
        }

        int numeroJogadas = jogo.getQtdeJogadasDisponiveis() - 1;
        jogo.setQtdeJogadasDisponiveis(numeroJogadas);

        verificarGanhador();

        // Verifica se houve empate
        if (!jogo.isHouveGanahardor() && jogo.getQtdeJogadasDisponiveis() == 0) {
            naoHouveGanhardor();
        } else {
            jogo.alterarMarcacao();
            jogo.alterarJogador();
            textViewJogadorAtual.setText("Jogando: " .concat(jogo.getJogadorAtual()));
        }
    }



    @SuppressLint("SetTextI18n")
    private void naoHouveGanhardor() {
        Som.parar();
        Som.executar(JogoActivity.this, R.raw.deu_velha);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Deu velha!");
        builder.setMessage("Ninguém ganhou desta vez. 😔");

        builder.setIcon(R.drawable.velha); // ícone para indicar empate

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_layout, null);
        view.setBackgroundColor(Color.RED);
        builder.setView(view);

        // Configurando o texto com a mensagem de empate
        TextView messageTextView = view.findViewById(R.id.messageTextView);
        messageTextView.setText("O jogo terminou em empate. Tente novamente!");

        // Botão para iniciar um novo jogo
        builder.setPositiveButton("Novo jogo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Som.parar();
                Som.executar(JogoActivity.this, R.raw.secunda);
                resetarTabuleiro();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void verificarGanhador() {
        if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[0][1].equals(jogo.getMarcacao()) &&
                tabuleiro[0][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[1][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[1][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[2][0].equals(jogo.getMarcacao()) &&
                tabuleiro[2][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][0].equals(jogo.getMarcacao()) &&
                tabuleiro[2][0].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[0][1].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][1].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[0][2].equals(jogo.getMarcacao()) &&
                tabuleiro[1][2].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[0][0].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][2].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        } else if (tabuleiro[0][2].equals(jogo.getMarcacao()) &&
                tabuleiro[1][1].equals(jogo.getMarcacao()) &&
                tabuleiro[2][0].equals(jogo.getMarcacao()))
        {
            jogo.setHouveGanahardor(true);
            jogo.setNomeVenceor(jogo.getJogadorAtual());
        }

        if (jogo.isHouveGanahardor()) {
            Som.parar();
            Som.executar(this, R.raw.success);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Parabéns!");

            builder.setIcon(R.drawable.trofeu);

            View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_layout, null);
            view.setBackgroundColor(Color.YELLOW); // Define a cor de fundo
            builder.setView(view);

            TextView messageTextView = view.findViewById(R.id.messageTextView);
            messageTextView.setText("🏆 O jogador: " + jogo.getNomeVenceor() + " é o vencedor 🏆");

            builder.setPositiveButton("Novo jogo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Som.parar();
                    Som.executar(JogoActivity.this, R.raw.secunda);
                    resetarTabuleiro();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    public void resetarTabuleiro() {
        // Reseta o tabuleiro após fechar a caixa de diálogo
        button0.setClickable(true);
        button0.setText("");
        tabuleiro[0][0] = "-";

        button1.setClickable(true);
        button1.setText("");
        tabuleiro[0][1] = "-";

        button2.setClickable(true);
        button2.setText("");
        tabuleiro[0][2] = "-";

        button3.setClickable(true);
        button3.setText("");
        tabuleiro[1][0] = "-";

        button4.setClickable(true);
        button4.setText("");
        tabuleiro[1][1] = "-";

        button5.setClickable(true);
        button5.setText("");
        tabuleiro[1][2] = "-";

        button6.setClickable(true);
        button6.setText("");
        tabuleiro[2][0] = "-";

        button7.setClickable(true);
        button7.setText("");
        tabuleiro[2][1] = "-";

        button8.setClickable(true);
        button8.setText("");
        tabuleiro[2][2] = "-";

        // Reinicializa a variável de controle de jogo
        jogo.setHouveGanahardor(false);
        jogo.setNomeVenceor(null);
        jogo.setQtdeJogadasDisponiveis(9);
    }

}