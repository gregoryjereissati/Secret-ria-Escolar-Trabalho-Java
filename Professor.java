
import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nomeCompleto;
    private String dataNascimento;
    private String endereco;
    private String telefone;
    private String CPF;
    private int matricula;
    private List<Disciplina> disciplinasMinistradas;
    private List<Turma> turmasResponsavel;

    // Construtor
    public Professor(String nomeCompleto, String dataNascimento, String endereco, String telefone, String cpf, int matricula) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.CPF = cpf;
        this.matricula = matricula;
        this.disciplinasMinistradas = new ArrayList<>();
        this.turmasResponsavel = new ArrayList<>();
    }

    // Método para adicionar disciplina que o professor leciona
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinasMinistradas.add(disciplina);
    }

     // Método para remover disciplina
     public void removerDisciplina(Disciplina disciplina) {
        disciplinasMinistradas.remove(disciplina);
    }

    // Métodos getters para as informações do professor
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCPF() {
        return CPF;
    }

    public int getMatricula() {
        return matricula;
    }

    // Método para obter disciplinas ministradas pelo professor
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nomeCompleto + ", Matrícula: " + this.matricula;
    }

    public void adicionarTurmaResponsavel(Turma turma) {
        this.turmasResponsavel.add(turma);
    }

    public void imprimirDeclaracaoVinculo() {
        System.out.println("Nome: " + getNomeCompleto());
        System.out.println("Matrícula: " + getMatricula());  // Adapte conforme necessário
        System.out.println("Turmas Responsáveis:");
    
        // Verifica se turmasResponsavel não é nulo antes de iterar
        if (turmasResponsavel != null) {
            // Iterar sobre as turmas responsáveis e imprimir suas informações
            for (Turma turma : turmasResponsavel) {
                System.out.println(" - Código da Turma: " + turma.getCodigo());
                System.out.println("   Disciplina: " + turma.getDisciplina().getNome());
                System.out.println("   Horário: " + turma.getHorario());
                // Adicione mais informações da turma conforme necessário
            }
        } else {
            System.out.println("Professor não é responsável por nenhuma turma.");
        }
    }
}
