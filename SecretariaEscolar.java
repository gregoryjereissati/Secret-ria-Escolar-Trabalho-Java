import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SecretariaEscolar {
    private List<Professor> professores;
    private List<Disciplina> disciplinas;
    private List<Turma> turmas;
    private List<Aluno> alunos;
    private List<Matricula> matriculas;

    public SecretariaEscolar() {
        this.professores = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    //PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES////PROFESSORES//

    // Método para cadastrar professores

    public void cadastrarProfessor(String nomeCompleto, String dataNascimento, String endereco, String telefone, String cpf, int matricula) {
        Professor professor = new Professor(nomeCompleto, dataNascimento, endereco, telefone, cpf, matricula);
        professores.add(professor);
    }

    public Professor buscarProfessorPorNome(String nome) {
        for (Professor professor : professores) {
            if (professor.getNomeCompleto().equalsIgnoreCase(nome)) {
                return professor;
            }
        }
        return null; // Retorna null se o professor não for encontrado
    }

    // Método para excluir professor de disciplinas

    public void desvincularProfessor(Professor professor) {
        for (Disciplina disciplina : disciplinas) {
            disciplina.removerProfessor(professor);
        }

    // Excluir professor de turmas

        for (Turma turma : turmas) {
            turma.removerProfessor(professor);
        }
    }
    public void excluirProfessor(Professor professor) {
        professores.remove(professor);
    }

    //Método para mostrar professores cadastrados

     public void mostrarProfessoresCadastrados() {
        System.out.println("Professores Cadastrados:");
        for (Professor professor : professores) {
            System.out.println(); // Linha em branco para separar as turmas
            System.out.println("Professor: " + professor.getNomeCompleto());
            System.out.println("Nascimento: " + professor.getDataNascimento());
            System.out.println("Endereço: " + professor.getEndereco());
            System.out.println("Telefone: " + professor.getTelefone());
            System.out.println("CPF: " + professor.getCPF());
            System.out.println("Matrícula: " + professor.getMatricula());
            System.out.println(); // Linha em branco para separar as turmas
        }
    }

    //DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS////DISCIPLINAS//

    // Método para cadastrar disciplinas
    public void cadastrarDisciplina(String nome, int codigo, int cargaHoraria) {
        Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria);
        disciplinas.add(disciplina);
    }
    public Disciplina buscarDisciplinaPorNome(String nome) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().equalsIgnoreCase(nome)) {
                return disciplina;
            }
        }
        return null; // Retorna null se a disciplina não for encontrada
    }

  // Método para designar uma turma com um professor e uma disciplina
  public void designarTurma(List<Professor> professores, Disciplina disciplina, String horario, int codigo, int maxAlunos) {
    if (professores.isEmpty()) {
        System.out.println("Erro ao designar turma. Nenhum professor fornecido.");
        return;
    }

    // Adiciona a disciplina nas disciplinas ministradas pelos professores
    for (Professor professor : professores) {
        professor.adicionarDisciplina(disciplina);
    }
}

    // Método para verificar se a disciplina está vinculada a turmas
    public boolean disciplinaVinculadaATurmas(String codigo) {
        for (Turma turma : turmas) {
            if (String.valueOf(turma.getDisciplina().getCodigo()).equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // Método para excluir disciplina
    public void excluirDisciplina(String codigo) {
        Disciplina disciplinaParaExcluir = null;
        for (Disciplina disciplina : disciplinas) {
            if (String.valueOf(disciplina.getCodigo()).equals(codigo)) {
                disciplinaParaExcluir = disciplina;
                break;
            }
        }
        if (disciplinaParaExcluir != null) {
            disciplinas.remove(disciplinaParaExcluir);
            System.out.println("Disciplina excluída com sucesso.");
        } else {
            System.out.println("Erro ao excluir disciplina. Disciplina não encontrada.");
        }
    }

    //Método para mostrar disciplinas cadastradas
    public void mostrarDisciplinasCadastradas() {
        System.out.println("Disciplinas Cadastradas:");
        for (Disciplina disciplina : disciplinas) {
            System.out.println(); // Linha em branco para separar as turmas
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Código: " + disciplina.getCodigo());
            System.out.println("Carga Horária: " + disciplina.getCargaHoraria());
            System.out.println(); // Linha em branco para separar as turmas
        }
    }

//TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS////TURMAS//

    // Método para cadastrar turma
public void cadastrarTurma(int codigo, List<Professor> professores, Disciplina disciplina, String horario, int maxAlunos) {
    // Cria a nova turma
    Turma turma = new Turma(horario, codigo, maxAlunos, professores, disciplina);
    turmas.add(turma); // Adiciona a turma à lista de turmas

    // Adiciona a turma à lista de turmas responsáveis de cada professor
    for (Professor professor : professores) {
        professor.adicionarTurmaResponsavel(turma);
    }

    // Adiciona a disciplina nas disciplinas ministradas pelos professores
    for (Professor professor : professores) {
        professor.adicionarDisciplina(disciplina);
    }

    System.out.println("Turma cadastrada com sucesso!");
}  

    // Método para excluir turma
    public void excluirTurma(int codigo) {
        Turma turma = buscarTurmaPorCodigo(codigo);
        if (turma != null) {
            // Remove a disciplina das disciplinas ministradas pelos professores
            for (Professor professor : turma.getProfessores()) {
                professor.removerDisciplina(turma.getDisciplina());
            }
    
            // Remove a turma
            turmas.remove(turma);
            System.out.println("Turma excluída com sucesso!");
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    // Método para buscar turma por código
    public Turma buscarTurmaPorCodigo(int codigo) {
        for (Turma turma : turmas) {
            if (turma.getCodigo() == codigo) {
                return turma;
            }
        }
        return null;
    }

    public void mostrarTurmasExistentes() {
        if (turmas != null && !turmas.isEmpty()) {
            System.out.println("Turmas Cadastradas:");
            for (Turma turma : turmas) {
                System.out.println("Código: " + turma.getCodigo());
    
                Disciplina disciplina = turma.getDisciplina();
                if (disciplina != null) {
                    System.out.println("Disciplina: " + disciplina.getNome());
                } else {
                    System.out.println("Disciplina: [Sem Disciplina]");
                }
    
                System.out.println("Horário: " + turma.getHorario());
                System.out.println("Quantidade Máxima de Alunos: " + turma.getMaxAlunos());
                System.out.println("Professor: " + turma.getProfessores());
                System.out.println(); // Linha em branco para separar as turmas
            }
        } else {
            System.out.println("Não há turmas cadastradas.");
        }
    }
    
    
    
    


//ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS////ALUNOS//

    // Método para cadastrar alunos
    public void cadastrarAluno(String nomeCompleto, String dataNascimento, String endereco, String telefone, String cpf, int matricula) {
        Aluno aluno = new Aluno(nomeCompleto, dataNascimento, endereco, telefone, cpf, matricula);
        alunos.add(aluno);
    }

    // Método para buscar um aluno por matrícula
    public Aluno buscarAlunoPorMatricula(int matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula() == matricula) {
                return aluno;
            }
        }
        return null; // Retorna null se o aluno não for encontrado
    }
    
    // Método para desvincular um aluno de disciplinas e turmas
    public void desvincularAluno(Aluno aluno) {
    // Desvincular o aluno de disciplinas
        for (Disciplina disciplina : disciplinas) {
            disciplina.removerAluno(aluno);
        }

        // Desvincular o aluno de turmas
        for (Turma turma : turmas) {
            turma.removerAluno(aluno);
        }
    }

        // Método para excluir um aluno
        public void excluirAluno(Aluno aluno) {
            alunos.remove(aluno);
        }

        // Método para mostrar alunos cadastrados
    public void mostrarAlunosCadastrados() {
        System.out.println("Alunos Cadastrados:");
        for (Aluno aluno : alunos) {
            System.out.println(); // Linha em branco para separar os alunos
            System.out.println("Aluno: " + aluno.getNomeCompleto());
            System.out.println("Nascimento: " + aluno.getDataNascimento());
            System.out.println("Endereço: " + aluno.getEndereco());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("CPF: " + aluno.getCpf());
            System.out.println("Matrícula: " + aluno.getMatricula());
            System.out.println(); // Linha em branco para separar os alunos
        }
    }

//MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA////MATRICULA//

    private static class Matricula {
        private int alunoMatricula;
        private int turmaCodigo;

        public Matricula(int alunoMatricula, int turmaCodigo) {
            this.alunoMatricula = alunoMatricula;
            this.turmaCodigo = turmaCodigo;
        }

        public int getAlunoMatricula() {
            return alunoMatricula;
        }

        public int getTurmaCodigo() {
            return turmaCodigo;
        }
    }

    
// Método adicionarMatricula modificado para usar a nova versão do matriculaExiste
public void adicionarMatricula(int alunoMatricula, int turmaCodigo) {
    // Verifica se o aluno já está cadastrado
    Aluno aluno = buscarAlunoPorMatricula(alunoMatricula);
    Turma turma = buscarTurmaPorCodigo(turmaCodigo);

    if (aluno != null && turma != null) {
        // Verifica se o aluno já está matriculado na turma
        if (matriculaExiste(alunoMatricula, turmaCodigo)) {
            System.out.println("Aluno já está matriculado nessa turma.");
        } else {
            // Verifica se o aluno já está matriculado em outra turma com a mesma disciplina
            if (matriculaExiste(alunoMatricula, turma.getDisciplina())) {
                System.out.println("Aluno já está matriculado em outra turma com a mesma disciplina.");
            } else {
                // Caso não exista, realiza a matrícula
                Matricula novaMatricula = new Matricula(alunoMatricula, turmaCodigo);
                matriculas.add(novaMatricula);

                // Adiciona a turma à lista de turmas matriculadas do aluno
                aluno.adicionarTurma(turma);

                System.out.println("Matrícula adicionada com sucesso!");
            }
        }
    } else {
        System.out.println("Aluno ou turma não encontrado. Não é possível adicionar a matrícula.");
    }
}

    

    public void excluirMatricula(int alunoMatricula, int turmaCodigo) {
        // Verifica se a matrícula existe antes de tentar excluir
        Matricula matricula = buscarMatricula(alunoMatricula, turmaCodigo);
        if (matricula != null) {
            matriculas.remove(matricula);
            System.out.println("Matrícula excluída com sucesso!");
        } else {
            System.out.println("Matrícula não encontrada.");
        }
        
    }

    public void mostrarMatriculas() {
        System.out.println("Matrículas:");
        for (Matricula matricula : matriculas) {
            System.out.println("Aluno: " + matricula.getAlunoMatricula() + ", Turma: " + matricula.getTurmaCodigo());
        }
    }

   // Método original para verificar a matrícula
private boolean matriculaExiste(int alunoMatricula, int turmaCodigo) {
    for (Matricula matricula : matriculas) {
        if (matricula.getAlunoMatricula() == alunoMatricula && matricula.getTurmaCodigo() == turmaCodigo) {
            return true;
        }
    }
    return false;
}

// Nova versão do método matriculaExiste que verifica a disciplina
private boolean matriculaExiste(int alunoMatricula, Disciplina disciplina) {
    for (Matricula matricula : matriculas) {
        if (matricula.getAlunoMatricula() == alunoMatricula && buscarTurmaPorCodigo(matricula.getTurmaCodigo()).getDisciplina().equals(disciplina)) {
            return true;
        }
    }
    return false;
}

    private Matricula buscarMatricula(int alunoMatricula, int turmaCodigo) {
        for (Matricula matricula : matriculas) {
            if (matricula.getAlunoMatricula() == alunoMatricula && matricula.getTurmaCodigo() == turmaCodigo) {
                return matricula;
            }
        }
        return null;
    }

    // Verifica se a turma com o código fornecido existe
    public boolean turmaExiste(int codigoTurma) {
        for (Turma turma : turmas) {
            if (turma.getCodigo() == codigoTurma) {
                return true;
            }
        } System.out.println("Erro ao realizar matrícula. Turma não encontrada.");
        return false;
    }

    

// Método para emitir declaração de matrícula para um aluno ativo
public void emitirDeclaracaoMatricula(Aluno aluno) {
    if (aluno != null) {
        // Emitir a declaração de matrícula
        System.out.println("Declaração de Matrícula para o Aluno:");
        aluno.imprimirDeclaracaoMatricula();
    } else {
        System.out.println("Aluno não encontrado.");
    }
}


public void emitirDeclaracaoVinculoProfessor(String nomeProfessor) {
    // Buscar o professor pelo nome
    Professor professor = buscarProfessorPorNome(nomeProfessor);

    if (professor != null) {
        // Emitir a declaração de vínculo
        System.out.println("Declaração de Vínculo para o Professor:");
        professor.imprimirDeclaracaoVinculo();
    } else {
        System.out.println("Professor não encontrado.");
    }
}
    public static void main(String[] args) {
        SecretariaEscolar secretaria = new SecretariaEscolar();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("a) Gerenciar Professores");
            System.out.println("b) Gerenciar Disciplina");
            System.out.println("c) Gerenciar Turmas");
            System.out.println("d) Gerenciar Aluno");
            System.out.println("e) Gerenciar Matrícula");
            System.out.println("f) Emitir Declaração de Matrícula");
            System.out.println("g) Emitir Declaração de Vínculo");
            System.out.println("x) Sair");

            String opcao = scanner.nextLine();
            
            switch (opcao.toLowerCase()) {
                case "a":
                // Gerenciar Professores
                while (true) {
                    System.out.println("Escolha uma opção:");
                    System.out.println("1) Cadastrar Professor");
                    System.out.println("2) Excluir Professor");
                    System.out.println("3) Mostrar Professores Cadastrados");
                    System.out.println("x) Voltar");
            
                    String opcaoGerenciarProfessores = scanner.nextLine();
            
                    switch (opcaoGerenciarProfessores.toLowerCase()) {
                        case "1":
                            // Cadastrar Professor
                            System.out.println("Informe o nome do professor:");
                            String nomeProfessor = scanner.nextLine();
                            System.out.println("Informe a data de nascimento do professor:");
                            String dataNascimentoProfessor = scanner.nextLine();
                            System.out.println("Informe o endereço do professor:");
                            String enderecoProfessor = scanner.nextLine();
                            System.out.println("Informe o telefone do professor:");
                            String telefoneProfessor = scanner.nextLine();
                            System.out.println("Informe o CPF do professor:");
                            String cpfProfessor = scanner.nextLine();
                            System.out.println("Informe a matrícula do professor:");
                            int matriculaProfessor = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha
            
                            // Cadastrar professor usando o método da SecretariaEscolar
                            secretaria.cadastrarProfessor(nomeProfessor, dataNascimentoProfessor, enderecoProfessor, telefoneProfessor, cpfProfessor, matriculaProfessor);
                            System.out.println("Professor cadastrado com sucesso!");
                            break;
            
                        case "2":
                            // Excluir Professor
                            System.out.println("Informe o nome do professor a ser excluído:");
                            String nomeProfessorExcluir = scanner.nextLine();
                            Professor professorExcluir = secretaria.buscarProfessorPorNome(nomeProfessorExcluir);
            
                            if (professorExcluir != null) {
                                // Desvincular professor de disciplinas e turmas
                                secretaria.desvincularProfessor(professorExcluir);
            
                                // Excluir o professor
                                secretaria.excluirProfessor(professorExcluir);
                                System.out.println("Professor excluído com sucesso!");
                            } else {
                                System.out.println("Professor não encontrado.");
                            }
                            break;
            
                        case "3":
                            // Mostrar Professores Cadastrados
                            secretaria.mostrarProfessoresCadastrados();
                            break;
            
                        case "x":
                            // Voltar ao menu principal
                            break;
            
                        default:
                            System.out.println("Opção inválida. Tente novamente.");

                    }
                    if (opcaoGerenciarProfessores.toLowerCase().equals("x")) {
                            break;
                        }
                }
                break;
                      

    case "b":
    // Submenu "Gerenciar Disciplinas"
    while (true) {
        System.out.println("Escolha uma opção:");
        System.out.println("1) Cadastrar Disciplina");
        System.out.println("2) Excluir Disciplina");
        System.out.println("3) Mostrar Disciplinas Cadastradas");
        System.out.println("x) Voltar ao Menu Principal");

        String opcaoDisciplinas = scanner.nextLine();

        switch (opcaoDisciplinas.toLowerCase()) {
            case "1":
                // Cadastro de Disciplina
                    System.out.println("Informe o nome da disciplina:");
                    String nomeDisciplina = scanner.nextLine();
                    System.out.println("Informe o código da disciplina:");
                    int codigoDisciplina = scanner.nextInt();
                    System.out.println("Informe a carga horária da disciplina:");
                    int cargaHorariaDisciplina = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                // Lógica para cadastrar disciplina
                secretaria.cadastrarDisciplina(nomeDisciplina, codigoDisciplina, cargaHorariaDisciplina);
                System.out.println("Disciplina cadastrada com sucesso!");
                break;

            case "2":
                // Excluir Disciplina
                System.out.println("Informe o código da disciplina a ser excluída:");
                String codigoDisciplinaExcluir = scanner.nextLine();

                // Lógica para verificar se a disciplina está vinculada a turmas
                if (!secretaria.disciplinaVinculadaATurmas(codigoDisciplinaExcluir)) {
                    // Se não estiver vinculada, pode excluir
                    secretaria.excluirDisciplina(codigoDisciplinaExcluir);
                } else {
                    System.out.println("Erro ao excluir disciplina. Disciplina vinculada a turmas.");
                }
                break;

            case "3":
                // Mostrar Disciplinas Cadastradas
                secretaria.mostrarDisciplinasCadastradas();
                break;

            case "x":
                // Voltar ao Menu Principal
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

        if (opcaoDisciplinas.toLowerCase().equals("x")) {
            break;
        }
    }
    break;

    case "c":
    while (true) {
        System.out.println("Escolha uma opção para turmas:");
        System.out.println("1) Cadastrar Turma");
        System.out.println("2) Excluir Turma");
        System.out.println("3) Mostrar Turmas");
        System.out.println("x) Voltar");

        String opcaoTurma = scanner.nextLine();

        switch (opcaoTurma.toLowerCase()) {
            case "1":
                 // Cadastrar Turma
    System.out.println("Informe o código da turma:");
    int codigoTurma = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha

    System.out.println("Escolha uma disciplina para a turma:");
    secretaria.mostrarDisciplinasCadastradas();
    String nomeDisciplinaTurma = scanner.nextLine();
    Disciplina disciplinaTurma = secretaria.buscarDisciplinaPorNome(nomeDisciplinaTurma);

    System.out.println("Informe o horário da turma:");
    String horarioTurma = scanner.nextLine();

    System.out.println("Informe a quantidade máxima de alunos na turma:");
    int maxAlunosTurma = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha

    // Adiciona um professor à turma
    System.out.println("Informe o Nome do professor para a turma:");
    String NomeProfessorTurma = scanner.nextLine();
    Professor professorTurma = secretaria.buscarProfessorPorNome(NomeProfessorTurma);

    if (professorTurma != null) {
        List<Professor> professoresTurma = new ArrayList<>();
        professoresTurma.add(professorTurma);

        // Cadastra a turma
        secretaria.cadastrarTurma(codigoTurma, professoresTurma, disciplinaTurma, horarioTurma, maxAlunosTurma);
    } else {
        System.out.println("Professor não encontrado. Turma não cadastrada.");
    }
    break;
             

            case "2":
                // Excluir Turma
            System.out.println("Informe o código da turma a ser excluída:");
            int codigoExcluirTurma = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            // Exclui a turma
            secretaria.excluirTurma(codigoExcluirTurma);
                break;

            case "3":
                // Mostrar Turmas
            secretaria.mostrarTurmasExistentes();
                break;

            case "x":
                // Voltar
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }
        if (opcaoTurma.toLowerCase().equals("x")) {
            break;
        }
    }
    break;

    case "d":
    // Submenu "Gerenciar Alunos"
    while (true) {
        System.out.println("Escolha uma opção:");
        System.out.println("1) Cadastrar Aluno");
        System.out.println("2) Excluir Aluno");
        System.out.println("3) Mostrar Alunos Cadastrados");
        System.out.println("x) Voltar");

        String opcaoGerenciarAlunos = scanner.nextLine();

        switch (opcaoGerenciarAlunos.toLowerCase()) {
            case "1":
                // Cadastrar Aluno
                System.out.println("Informe o nome do aluno:");
                String nomeAluno = scanner.nextLine();
                System.out.println("Informe a data de nascimento do aluno:");
                String dataNascimentoAluno = scanner.nextLine();
                System.out.println("Informe o endereço do aluno:");
                String enderecoAluno = scanner.nextLine();
                System.out.println("Informe o telefone do aluno:");
                String telefoneAluno = scanner.nextLine();
                System.out.println("Informe o CPF do aluno:");
                String cpfAluno = scanner.nextLine();
                System.out.println("Informe a matrícula do aluno:");
                int matriculaAluno = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                secretaria.cadastrarAluno(nomeAluno, dataNascimentoAluno, enderecoAluno, telefoneAluno, cpfAluno, matriculaAluno);
                System.out.println("Aluno cadastrado com sucesso!");
                break;

            case "2":
                // Excluir Aluno
                System.out.println("Informe a matrícula do aluno a ser excluído:");
                int matriculaAlunoExcluir = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                Aluno alunoExcluir = secretaria.buscarAlunoPorMatricula(matriculaAlunoExcluir);

                if (alunoExcluir != null) {
                    // Desvincular aluno de disciplinas e turmas
                    secretaria.desvincularAluno(alunoExcluir);

                    // Excluir o aluno
                    secretaria.excluirAluno(alunoExcluir);
                    System.out.println("Aluno excluído com sucesso!");
                } else {
                    System.out.println("Aluno não encontrado.");
                }
                break;

            case "3":
                // Mostrar Alunos Cadastrados
                secretaria.mostrarAlunosCadastrados();
                break;

            case "x":
                // Voltar ao menu principal
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
        if (opcaoGerenciarAlunos.toLowerCase().equals("x")) {
            break;
        }
    }
    break;


    case "e":
    // Submenu "Realizar Matrícula"
    while (true) {
        System.out.println("Escolha uma opção:");
        System.out.println("1) Adicionar Matrícula");
        System.out.println("2) Excluir Matrícula");
        System.out.println("3) Mostrar Matrículas");
        System.out.println("x) Voltar ao Menu Principal");

        String opcaoMatriculas = scanner.nextLine();

        switch (opcaoMatriculas.toLowerCase()) {
            case "1":
    // Realizar Matrícula
    System.out.println("Informe a matrícula do aluno:");
    int matriculaAluno = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha

    System.out.println("Informe o código da turma:");
    int codigoTurmaMatricula = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha

    // Verifica se a turma existe
    if (secretaria.turmaExiste(codigoTurmaMatricula)) {
        // Adiciona a matrícula se a turma existir
        secretaria.adicionarMatricula(matriculaAluno, codigoTurmaMatricula);
        
    } else {
        
    }
    break;



            case "2":
                // Excluir Matrícula
                System.out.println("Informe a matrícula do aluno:");
                int matriculaAlunoExcluir = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.println("Informe o código da turma:");
                int codigoTurmaExcluir = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                // Lógica para excluir matrícula
                secretaria.excluirMatricula(matriculaAlunoExcluir, codigoTurmaExcluir);
                System.out.println("Matrícula excluída com sucesso!");
                break;

            case "3":
                // Mostrar Matrículas
                secretaria.mostrarMatriculas();
                break;

            case "x":
                // Voltar ao Menu Principal
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

        if (opcaoMatriculas.toLowerCase().equals("x")) {
            break;
        }
    }
    break;


    case "f":
    // Emitir Declaração de Matrícula
    System.out.println("Informe a matrícula do aluno:");
    int matriculaAlunoDeclaracao = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha

    Aluno alunoParaDeclaracao = secretaria.buscarAlunoPorMatricula(matriculaAlunoDeclaracao);

    if (alunoParaDeclaracao != null) {
        secretaria.emitirDeclaracaoMatricula(alunoParaDeclaracao);
    } else {
        System.out.println("Aluno não encontrado.");
    }
    break;


    case "g":
    // Emitir Declaração de Vínculo para um Professor
    System.out.println("Informe o nome do professor:");
    String nomeProfessorVinculo = scanner.nextLine();
    
    secretaria.emitirDeclaracaoVinculoProfessor(nomeProfessorVinculo);
    break;

                case "x":
                    // Sair do programa
                    System.out.println("Saindo do programa. Até mais!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}