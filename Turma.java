import java.util.List;
import java.util.ArrayList;


public class Turma {
    private String horario;
    private int codigo;
    private int maxAlunos;
    private List<Professor> professores;
    private Disciplina disciplina;
    private List<Aluno> alunosMatriculados;


    // Construtor
    public Turma(String horario, int codigo, int maxAlunos, List<Professor> professores, Disciplina disciplina) {
        this.horario = horario;
        this.codigo = codigo;
        this.maxAlunos = maxAlunos;
        this.professores = professores;
        this.disciplina = disciplina;
        this.alunosMatriculados = new ArrayList<>();
    }

    // Métodos para adicionar/remover aluno à turma, entre outros
    public void adicionarAluno(Aluno aluno) {
        if (alunosMatriculados.size() < maxAlunos && !alunosMatriculados.contains(aluno)) {
            alunosMatriculados.add(aluno);
            disciplina.matricularAluno(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }

    public String getHorario() {
        return horario;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getMaxAlunos() {
        return maxAlunos;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunosMatriculados;
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
    }
}
