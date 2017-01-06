package escola.musica.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import escola.musica.dao.CursoDAO;
import escola.musica.modelo.Curso;
import escola.musica.modelo.TipoCurso;

@ManagedBean
@SessionScoped
public class CursoBean {

	// nesta linha eu não dei neu Curso, só disse que Curso será curso.
	private Curso curso;
	
	private List<TipoCurso> tipos = Arrays.asList(TipoCurso.values());
	private List<Curso> cursos = new ArrayList<Curso>();
	
	public CursoBean() {
		cursos = new CursoDAO().listarTodos();
		// precisa deste comando para qdo. abrir a página ela não estar com curso nulo.
		curso = new Curso();
	}
	
	
	public String salvar() {
		new CursoDAO().salvar(curso);
		cursos = new CursoDAO().listarTodos();
		
		//salva na memória quando grava. Qdo. não estou usando banco.
		//cursos.add(curso);
		
		curso = new Curso();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Curso salvo com sucesso!"));
		return "curso_lista?faces-redirect=true";
	}
	
	public String editar(Curso curso) {
		// este comando diz que meu curso é igual ao curso que veio selecionado da tabela
		// se não fizer isso meu curso aqui fica vazio.
		this.curso = curso;
		
		return "curso_formulario?faces-redirect=true";
	}
	
	
	public void prepararExclusao(Curso curso) {
		this.curso = curso;
	}
	
	// Não é preciso declarar o curso neste método por causa do método acima.
	public void excluir(){
		new CursoDAO().excluir(curso);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Curso excluído com sucesso!"));
		cursos = new CursoDAO().listarTodos();
	}
	
	// Não deixa ser digitada uma data maior que a data atual.
	// no campo do form xhtml está o complemento para funcionar
	public String getDataAtual() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<TipoCurso> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoCurso> tipos) {
		this.tipos = tipos;
	}
	
	
	
	
}