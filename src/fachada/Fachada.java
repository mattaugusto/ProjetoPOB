/**
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranhão Ayres
 * Persistencia de Objetos
 * Alunos: Joel e Matheus
 *
 */
package fachada;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOParticipante;
import daojpa.DAOPalestrante;
import daojpa.DAOTitulacao;
import daojpa.DAOEvento;
import daojpa.DAOPalestra;
import modelo.Titulacao;
import modelo.Palestrante;
import modelo.Participante;
import modelo.Evento;
import modelo.Palestra;

public class Fachada 
{
    private static DAOParticipante daoparticipante= new DAOParticipante();
    private static DAOPalestrante daopalestrante = new DAOPalestrante();
    private static DAOTitulacao daotitulacao = new DAOTitulacao();
    private static DAOEvento daoevento = new DAOEvento();
    private static DAOPalestra daopalestra = new DAOPalestra();

    public static void inicializar()
    {
        DAO.abrir();
    }

    public static void finalizar()
    {
        DAO.fechar();
    }

    public static Participante cadastrarParticipante(
            String nome,
            String cpf,
            String email,
            String instituicao) throws Exception
    {
        DAO.iniciar();
        Participante p = daoparticipante.localizarPeloCPF(cpf);
        if (p!= null) {
            throw new Exception("CPF já cadastrado!");
        }
        if (nome == null) {
            throw new Exception("Nome vazio!");
        }
        if (cpf == null) {
            throw new Exception("CPF vazio!");
        }
        if (email == null) {
            throw new Exception("Email vazio!");
        }
        if (instituicao == null) {
            throw new Exception("Instituicao vazio!");
        }
        p = new Participante(nome, cpf, email, instituicao);
        daoparticipante.persistir(p);
        DAO.efetivar();
        return p;
    }

    public static Palestrante cadastrarPalestrante(
            String nome,
            String cpf,
            String email,
            String tipoTitulacao) throws Exception
    {
        DAO.iniciar();
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p!= null) {
            throw new Exception("CPF já cadastrado!");
        }
        if (nome == null) {
            throw new Exception("Nome vazio!");
        }
        if (cpf == null) {
            throw new Exception("CPF vazio!");
        }
        if (email == null) {
            throw new Exception("Email vazio!");
        }
        if (!daopalestrante.isEmailDisponivel(email)) {
            throw new Exception("E-mail já cadastrado!");
        }
        if (tipoTitulacao.isEmpty()) {
            throw new Exception("Titulação invalida!");
        }
        Titulacao t = daotitulacao.localizarPeloTitulo(tipoTitulacao);
        if (t == null) {
            throw new Exception("Titulação não cadastrada");
        }
        p = new Palestrante(nome, cpf, email, t);
        daopalestrante.persistir(p);
        DAO.efetivar();
        return p;
    }

    public static Titulacao cadastrarTitulacao(String titulo) throws Exception
    {
        DAO.iniciar();
        Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
        if (t!= null) {
            throw new Exception("Titulação já cadastrada!");
        }
        t = new Titulacao(titulo);
        daotitulacao.persistir(t);
        DAO.efetivar();
        return t;
    }

    public static Palestra cadastrarPalestra(
            String titulo,
            String descricao,
            String duracao,
            String cpf) throws Exception
    {
        DAO.iniciar();
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p!= null) {
            throw new Exception("Palestra já cadastrada");
        }
        Palestrante palestrante = daopalestrante.localizarPeloCPF(cpf);
        if (palestrante == null) {
            throw new Exception("Palestrante não cadastrado");
        }
        p = new Palestra(titulo, descricao, duracao, palestrante);
        daopalestra.persistir(p);
        DAO.efetivar();
        return p;
    }

    public static Evento cadastrarEvento(
            String nome,
            String inicio,
            String fim) throws Exception
    {
        DAO.iniciar();
        Evento e = daoevento.localizarPeloNome(nome);
        if (e!= null) {
            throw new Exception("Evento já cadastrado");
        }
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        Date inicio_data = sdf.parse(inicio);
        Date fim_data = sdf.parse(fim);
        e = new Evento(nome, inicio_data, fim_data);
        daoevento.persistir(e);
        DAO.efetivar();
        return e;
    }

    public static void adicionarParticipanteEvento(String evento, String cpf) throws Exception
    {
        DAO.iniciar();
        Evento e = daoevento.localizarPeloNome(evento);
        if (e == null) {
            throw new Exception("Evento não cadastrado! " + evento);
        }
        Participante p = daoparticipante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Participante não cadastrado! " + cpf);
        }
        if (p.taCadastroEvento(e)) {
            throw new Exception("Participante já cadastrado no evento!");
        }
        e.adicionarParticipante(p);
        daoevento.atualizar(e);
        DAO.efetivar();
    }

    public static void adicionarPalestraEvento(String evento, String titulo) throws Exception
    {
        DAO.iniciar();
        Evento e = daoevento.localizarPeloNome(evento);
        if (e == null) {
            throw new Exception("Evento não cadastrado! " + evento);
        }
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p == null) {
            throw new Exception("Palestra não cadastrada! " + titulo);
        }
        e.adicionarPalestra(p);
        daoevento.atualizar(e);
        DAO.efetivar();
    }

    public static void apagarParticipante(String cpf) throws Exception
    {
        DAO.iniciar();
        Participante p = daoparticipante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Participante não cadastrado");
        }
        daoparticipante.apagar(p);
        DAO.efetivar();
    }

    public static void apagarEvento(String nome) throws Exception
    {
        DAO.iniciar();
        Evento e = daoevento.localizarPeloNome(nome);
        if (e == null) {
            throw new Exception("Evento não cadastrado");
        }
        daoevento.apagar(e);
        DAO.efetivar();
    }

    public static void apagarPalestrante(String cpf) throws Exception
    {
        DAO.iniciar();
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Palestrante não cadastrado");
        }
        daopalestrante.apagar(p);
        DAO.efetivar();
    }

    public static void apagarPalestra(String titulo) throws Exception
    {
        DAO.iniciar();
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p == null) {
            throw new Exception("Palestra não cadastrada");
        }
        daopalestra.apagar(p);
        DAO.efetivar();
    }

    public static void apagarTitulacao(String titulo) throws Exception
    {
        DAO.iniciar();
        Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
        if (t == null) {
            throw new Exception("Titulação não cadastrada");
        }
        daotitulacao.apagar(t);
        DAO.efetivar();
    }

    public static void atualizarTitulacaoDoPalestrante(String cpf, String titulo) throws Exception
    {
        DAO.iniciar();
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Palestrante não cadastrado");
        }
        Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
        if (t == null) {
            throw new Exception("Titulação não cadastrada");
        }
        p.setTitulacao(t);
        daopalestrante.atualizar(p);
        DAO.efetivar();
    }

    public static String listarEventos()
    {
        List<Evento> eventos = daoevento.listar();
        String texto = "Listagem de eventos: \n";
        if (eventos.isEmpty())
            return texto += "nenhum evento cadastrado";
        
        for (Evento evento : eventos) {
            texto += "\n" + evento;
        }
        return texto;
    }
}