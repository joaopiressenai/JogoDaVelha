package com.joao.jogodavelha;

import java.io.Serializable;

public class Jogo implements Serializable {
    private String nomeJogardor1;
    private String nomeJogardor2;
    private String nomeVenceor;
    private String jogadorAtual;
    private String marcacao;
    private boolean houveGanahardor;
    private int qtdeJogadasDisponiveis;
    private String resetarTabuleiro;

    public Jogo() {
        this.nomeJogardor1 = "";
        this.nomeJogardor2 = "";
        this.nomeVenceor = "";
        this.houveGanahardor = false;
        this.qtdeJogadasDisponiveis = 9;
        this.marcacao = "X";
    }

    public String getNomeJogardor1() {
        return nomeJogardor1;
    }

    public void setNomeJogardor1(String nomeJogardor1) {
        this.nomeJogardor1 = nomeJogardor1;
    }

    public String getNomeJogardor2() {
        return nomeJogardor2;
    }

    public void setNomeJogardor2(String nomeJogardor2) {
        this.nomeJogardor2 = nomeJogardor2;
    }

    public String getNomeVenceor() {
        return nomeVenceor;
    }

    public void setNomeVenceor(String nomeVenceor) {
        this.nomeVenceor = nomeVenceor;
    }

    public String getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(String jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public String getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(String marcacao) {
        this.marcacao = marcacao;
    }

    public boolean isHouveGanahardor() {
        return houveGanahardor;
    }

    public void setHouveGanahardor(boolean houveGanahardor) {
        this.houveGanahardor = houveGanahardor;
    }

    public int getQtdeJogadasDisponiveis() {
        return qtdeJogadasDisponiveis;
    }

    public void setQtdeJogadasDisponiveis(int qtdeJogadasDisponiveis) {
        this.qtdeJogadasDisponiveis = qtdeJogadasDisponiveis;
    }

    public String getResetarTabuleiro() {
        return resetarTabuleiro = "";
    }

    public void alterarMarcacao() {
        if(marcacao.equals("X")) {
            marcacao = "0";
        } else {
            marcacao = "X";
        }
    }

    public void alterarJogador() {
        if(jogadorAtual.equals(nomeJogardor1)) {
            jogadorAtual = nomeJogardor2;
        } else {
            jogadorAtual = nomeJogardor1;
        }
    }
}
