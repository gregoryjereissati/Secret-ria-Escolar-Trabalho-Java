import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nomeCompleto;
    private String dataNascimento;
    private String endereco;
    private String telefone;
    private String cpf;
    private int matricula;
    private List<Turma> turmasMatriculado;

    // Construtor
    public Aluno(String nomeCompleto, String dataNascimento, String endereco, String telefone, String cpf, int matricula) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.matricula = matricula;
        this.turmasMatriculado = new ArrayList<>();
    }

    // Métodos getters para as informações do aluno
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

    public String getCpf() {
        return cpf;
    }

    public int getMatricula() {
        return matricula;
    }

    public List<Turma> getTurmasMatriculado() {
        return turmasMatriculado;
    }

    public void adicionarTurma(Turma turma) {
        turmasMatriculado.add(turma);
    }

    public void imprimirDeclaracaoMatricula() {
        System.out.println("Nome: " + getNomeCompleto());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Turmas Matriculadas:");
    
        // Verifica se turmasMatriculado não é nulo antes de iterar
        if (turmasMatriculado != null) {
            // Iterar sobre as turmas matriculadas e imprimir suas informações
            for (Turma turma : turmasMatriculado) {
                System.out.println(" - Código: " + turma.getCodigo());
    
                // Verifica se a disciplina não é nula antes de acessar seus atributos
                if (turma.getDisciplina() != null) {
                    System.out.println("   Disciplina: " + turma.getDisciplina().getNome());
                } else {
                    System.out.println("   Disciplina: (Indisponível)");
                }
    
                // Verifica se outros atributos não são nulos antes de acessá-los
                if (turma.getHorario() != null) {
                    System.out.println("   Horário: " + turma.getHorario());
                } else {
                    System.out.println("   Horário: (Indisponível)");
                }
                System.out.println(); // Linha em branco para separar os alunos
                
                // Adicione mais verificações conforme necessário
            }
        } else {
            System.out.println("Aluno não matriculado em nenhuma turma.");
        }
    }
    
    
    
}