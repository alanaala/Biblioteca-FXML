package com.alana.bibliotecafxml.model;

import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
    private int indiceLivro;
    private String usuario;
    private String tipoUsuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(int indiceLivro, String usuario, String tipoUsuario, Date dataEmprestimo) {
        this.indiceLivro = indiceLivro;
        this.usuario = usuario;
        this.tipoUsuario = tipoUsuario;
        this.dataEmprestimo = dataEmprestimo;

        Calendar c = Calendar.getInstance();
        c.setTime(dataEmprestimo);
        if (tipoUsuario.equalsIgnoreCase("professor")) {
            c.add(Calendar.DAY_OF_MONTH, 15);
        } else {
            c.add(Calendar.DAY_OF_MONTH, 7);
        }
        this.dataDevolucao = c.getTime();
    }

    public int getIndiceLivro() { return indiceLivro; }
    public String getUsuario() { return usuario; }
    public String getTipoUsuario() { return tipoUsuario; }
    public Date getDataEmprestimo() { return dataEmprestimo; }
    public Date getDataDevolucao() { return dataDevolucao; }

    public double calcularMulta() {
        Date hoje = new Date();
        if (hoje.after(dataDevolucao)) {
            long diff = hoje.getTime() - dataDevolucao.getTime();
            long diasAtraso = diff / (1000*60*60*24);
            return diasAtraso * 2.0;
        }
        return 0.0;
    }
}
