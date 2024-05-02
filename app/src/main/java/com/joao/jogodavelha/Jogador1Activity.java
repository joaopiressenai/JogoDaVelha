package com.joao.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Jogador1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador1);

        Som.parar();
        Som.executar(this, R.raw.secunda);

        EditText editTextJogador = findViewById(R.id.edtiTextJogador1);
        ImageButton buttonTela = findViewById(R.id.imageButtonJg1);

        buttonTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jogador1 = editTextJogador.getText().toString().trim();
                if (jogador1.isEmpty()) {
                    editTextJogador.setError("Nome inválido");
                    return;
                }

                Jogo jogo = new Jogo();
                jogo.setNomeJogardor1(jogador1);

                Intent telaJogador1 = new Intent(Jogador1Activity.this, Jogador2Activity.class);
                telaJogador1.putExtra("jogo", jogo);
                startActivity(telaJogador1);
                finish();
            }
        });
    }
}