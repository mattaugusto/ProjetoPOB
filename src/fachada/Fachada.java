/**
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Prof. Fausto Maranhăo Ayres
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
            throw new Exception("Titulaçăo invalida!");
        }
        Titulacao t = daotitulacao.localizarPeloTitulo(tipoTitulacao);
        if (t == null) {
            throw new Exception("Titulaçăo năo cadastrada");
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
            throw new Exception("Titulaçăo já cadastrada!");
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
            throw new Exception("Palestrante năo cadastrado");
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
            throw new Exception("Evento năo cadastrado! " + evento);
        }
        Participante p = daoparticipante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Participante năo cadastrado! " + cpf);
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
            throw new Exception("Evento năo cadastrado! " + evento);
        }
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p == null) {
            throw new Exception("Palestra năo cadastrada! " + titulo);
        }
        if (e.getPalestras().contains(p)) {
        	throw new Exception("Palestra já cadastrada no evento! " + titulo);
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
            throw new Exception("Participante năo cadastrado");
        }
        daoparticipante.apagar(p);
        DAO.efetivar();
    }

    public static void apagarEvento(String nome) throws Exception
    {
        DAO.iniciar();
        Evento e = daoevento.localizarPeloNome(nome);
        if (e == null) {
            throw new Exception("Evento năo cadastrado");
        }
        daoevento.apagar(e);
        DAO.efetivar();
    }

    public static void apagarPalestrante(String cpf) throws Exception
    {
        DAO.iniciar();
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Palestrante năo cadastrado");
        }
        daopalestrante.apagar(p);
        DAO.efetivar();
    }

    public static void apagarPalestra(String titulo) throws Exception
    {
        DAO.iniciar();
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p == null) {
            throw new Exception("Palestra năo cadastrada");
        }
        daopalestra.apagar(p);
        DAO.efetivar();
    }

    public static void apagarTitulacao(String titulo) throws Exception
    {
        DAO.iniciar();
        Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
        if (t == null) {
            throw new Exception("Titulaçăo năo cadastrada");
        }
        daotitulacao.apagar(t);
        DAO.efetivar();
    }

    public static void atualizarTitulacaoDoPalestrante(String cpf, String titulo) throws Exception
    {
        DAO.iniciar();
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p == null) {
            throw new Exception("Palestrante năo cadastrado");
        }
        Titulacao t = daotitulacao.localizarPeloTitulo(titulo);
        if (t == null) {
            throw new Exception("Titulaçăo năo cadastrada");
        }
        p.setTitulacao(t);
        daopalestrante.atualizar(p);
        DAO.efetivar();
    }
    
    public static void atualizarTitulacao(Integer id, String titulo) throws Exception
    {
        DAO.iniciar();
        Titulacao titulacao = daotitulacao.localizar(id);
        if (titulacao == null) {
            throw new Exception("Titulacao nao cadastrada");
        }
        titulacao.setTitulo(titulo);
        daotitulacao.atualizar(titulacao);
        DAO.efetivar();
    }

    public static String listarEventos()
    {
        List<Evento> eventos = daoevento.listar();
        String texto = "Listagem de eventos: \n";
        if (eventos.isEmpty()) {
            return texto += "nenhum evento cadastrado";
        }
        for (Evento evento : eventos) {
            texto += "\n   " + evento;
        }
        return texto += "\n";
    }

    public static String listarPalestras()
    {
        List<Palestra> palestras = daopalestra.listar();
        String texto = "Listagem de palestras: \n";
        if (palestras.isEmpty()) {
            return texto += "nenhuma palestra cadastrada";
        }
        for (Palestra palestra : palestras) {
            texto += "\n   " + palestra;
        }
        return texto += "\n";
    }

    public static String listarParticipantes()
    {
        List<Participante> participantes = daoparticipante.listar();
        String texto = "Listagem de participantes: \n";
        if (participantes.isEmpty()) {
            return texto += "nenhum participante cadastrado";
        }
        for (Participante participante : participantes) {
            texto += "\n   " + participante;
        }
        return texto += "\n";
    }

    public static String listarPalestrantes()
    {
        List<Palestrante> palestrantes = daopalestrante.listar();
        String texto = "Listagem de palestrantes: \n";
        if (palestrantes.isEmpty()) {
            return texto += "nenhum palestrante cadastrado";
        }
        for (Palestrante palestrante : palestrantes) {
            texto += "\n   " + palestrante;
        }
        return texto += "\n";
    }
    
    public static String listarTitulacoes()
    {
        List<Titulacao> titulacoes = daotitulacao.listar();
        String texto = "Listagem de titulacoes: \n";
        if (titulacoes.isEmpty()) {
            return texto += "nenhuma titulacao cadastrado";
        }
        for (Titulacao titulacao : titulacoes) {
            texto += "\n   " + titulacao;
        }
        return texto += "\n";
    }

    public static String consulta1() // Palestrantes sem Palestras
    {
        List<Palestrante> palestrantes = daopalestrante.consultarPalestrantesSemPalestras();
        String texto = "Listagem de palestrantes sem palestras: \n";
        if (palestrantes.isEmpty()) {
            return texto += "\n   nenhum palestrante cadastrado sem palestra\n";
        }
        for (Palestrante palestrante : palestrantes) {
            texto += "\n   " + palestrante;
        }
        return texto += "\n";
    }

    public static String consulta2() // Palestrantes sem Titulacao
    {
        List<Palestrante> palestrantes = daopalestrante.consultarPalestrantesSemTitulo();
        String texto = "Listagem de palestrantes sem titulo: \n";
        if (palestrantes.isEmpty()) {
            return texto += "\n   nenhum palestrante cadastrado sem titulo\n";
        }
        for (Palestrante palestrante : palestrantes) {
            texto += "\n   " + palestrante;
        }
        return texto += "\n";
    } 
    
    public static String consulta3() // Total de Participantes por Evento
    {
        List<Object[]> totalPorEvento = daoevento.totalParticipantesEvento();
        String texto = "Listagem de total de participantes no evento: \n";
        if (totalPorEvento.isEmpty()) {
            return texto += "\n   nenhum total\n";
        }
        for (Object[] obj : totalPorEvento) {
        	texto += "\n   " + obj[0]+" "+obj[1];
        }
        return texto += "\n";
    }
    
    public static String consulta4(String titulo) throws Exception // Palestrante por Titulacao
    {
    	Titulacao titulacao = daotitulacao.localizarPeloTitulo(titulo);
    	if (titulacao == null) {
    		throw new Exception("Titulação não encontrada");
    	}
        List<Palestrante> palestrantes = daopalestrante.consultarPalestrantesPorTitulo(titulo);
        String texto = "Listagem de palestrantes por titulo: \n";
        if (palestrantes.isEmpty()) {
            return texto += "\n   nenhum palestrante cadastrado com titulo de " + titulo + "\n";
        }
        for (Palestrante palestrante : palestrantes) {
            texto += "\n   " + palestrante;
        }
        return texto += "\n";
    }

    public static String consulta5() // Total de Titulacao por Evento
    {
        List<Object[]> totalPorEvento = daoevento.totalEventoTitulacao();
        String texto = "Listagem total de titulacao por evento: \n";
        if (totalPorEvento.isEmpty()) {
            return texto += "\n   nenhum total\n";
        }
        for (Object[] obj : totalPorEvento) {
        	texto += "\n   " + obj[0]+" "+obj[1]+" "+obj[2];
        }
        return texto += "\n";
    }

    public static String consulta6()
    {
    	return null;
    }

    public static void atualizarEvento(
            Integer id,
            String nome,
            String inicio,
            String fim) throws Exception
    {
        DAO.iniciar();
        Evento evento = daoevento.localizar(id);
        if (evento == null) {
            throw new Exception("Evento nao cadastrada");
        }
        
        Evento e = daoevento.localizarPeloNome(nome);
        if (e != null && e.getId() != evento.getId()) {
        	throw new Exception("Nome para evento n disponivel");
        }
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        Date inicio_data = sdf.parse(inicio);
        Date fim_data = sdf.parse(fim);
        evento.setFim(fim_data);
        evento.setInicio(inicio_data);
        evento.setNome(nome);
        daoevento.atualizar(evento);
        DAO.efetivar();
    }

    public static Palestrante atualizarPalestrante(
    		Integer id,
            String nome,
            String cpf,
            String email,
            String tipoTitulacao) throws Exception
    {
        DAO.iniciar();
        Palestrante palestrante = daopalestrante.localizar(id);
        if (palestrante == null) {
        	throw new Exception("Palestrante nao cadastrado");
        }
        
        Palestrante p = daopalestrante.localizarPeloCPF(cpf);
        if (p!= null && p.getId() != id) {
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
        if (!p.getEmail().equals(email) && !daopalestrante.isEmailDisponivel(email)) {
            throw new Exception("E-mail já cadastrado!");
        }
        if (tipoTitulacao.isEmpty()) {
            throw new Exception("Titulaçăo invalida!");
        }
        Titulacao t = daotitulacao.localizarPeloTitulo(tipoTitulacao);
        if (t == null) {
            throw new Exception("Titulaçăo năo cadastrada");
        }
        p.setNome(nome);
        p.setCpf(cpf);
        p.setEmail(email);
        p.setTitulacao(t);
        daopalestrante.atualizar(p);
        DAO.efetivar();
        return p;
    }

    public static Palestra atualizarPalestra(
    		Integer id,
            String titulo,
            String descricao,
            String duracao,
            String cpf) throws Exception
    {
        DAO.iniciar();
        Palestra palestra = daopalestra.localizar(id);
        if (palestra == null) {
        	throw new Exception("Palestra nao cadastrada");
        }
        Palestra p = daopalestra.localizarPeloTitulo(titulo);
        if (p!= null && p.getId() != id) {
            throw new Exception("Palestra já cadastrada");
        }
        Palestrante palestrante = daopalestrante.localizarPeloCPF(cpf);
        if (palestrante == null) {
            throw new Exception("Palestrante năo cadastrado");
        }
        p.setDescricao(descricao);
        p.setDuracao(duracao);
        p.setTitulo(titulo);
        p.setPalestrante(palestrante);
        daopalestra.persistir(p);
        DAO.efetivar();
        return p;
    }

    public static Participante atualizarParticipante(
    		Integer id,
            String nome,
            String cpf,
            String email,
            String instituicao) throws Exception
    {
        DAO.iniciar();
        Participante participante = daoparticipante.localizar(id);
        if (participante == null) {
        	throw new Exception("Participante nao cadastrado");
        }
        Participante p = daoparticipante.localizarPeloCPF(cpf);
        System.out.println(p);
        if (p!= null && p.getId() != id) {
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
        p.setCpf(cpf);
        p.setEmail(email);
        p.setNome(nome);
        daoparticipante.atualizar(p);
        DAO.efetivar();
        return p;
    }
}