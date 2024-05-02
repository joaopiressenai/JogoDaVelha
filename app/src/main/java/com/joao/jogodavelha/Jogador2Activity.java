package com.joao.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Jogador2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador2);

        EditText editTextJogador = findViewById(R.id.editTextJogador2);
        ImageButton imageButtonTelaJogador2 = findViewById(R.id.imageButtonJg2);

        Intent telaJogador1 = getIntent();
        Jogo jogo = (Jogo) telaJogador1.getSerializableExtra("jogo");
        imageButtonTelaJogador2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jogador2 = editTextJogador.getText().toString().trim();
                if (jogador2.isEmpty()) {
                    editTextJogador.setError("Nome inválido");
                    return;
                }

                jogo.setNomeJogardor2(jogador2);

                Intent telaJogador2 = new Intent(Jogador2Activity.this, JogoActivity.class);
                telaJogador2.putExtra("jogo", jogo);
                startActivity(telaJogador2);
                finish();
            }
        });
    }
}