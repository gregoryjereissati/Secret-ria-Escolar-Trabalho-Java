import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int codigo;
    private int cargaHoraria;
    private List<Aluno> alunosMatriculados;
    private List<Professor> professoresMinistrantes;

    // Construtor
    public Disciplina(String nome, int codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.alunosMatriculados = new ArrayList<>();
    }

     // Métodos para matricular aluno na disciplina
public boolean matricularAluno(Aluno aluno) {
    if (!alunosMatriculados.contains(aluno)) {
        alunosMatriculados.add(aluno);
        return true; // Retorna verdadeiro se a matrícula foi realizada com sucesso
    } else {
        return false; // Retorna falso se o aluno já estiver matriculado
    }
}

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void removerProfessor(Professor professor) {
        professoresMinistrantes.remove(professor);
    }
    
    public void removerAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
    }
}