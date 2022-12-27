package business;

public interface IClienteFacade {

    /**
     * Método que adiciona um aluno.
     *
     * @param a aluno a adicionar
     */

    void adicionaAluno(Aluno a);

    /**
     * Método que adiciona uma turma
     *
     * @param t turma a dicionar
     */
    void adicionaTurma(Turma t);

    /**
     * Método que altera a sala da turma.
     *
     * @param tid id da turma que vai mudar de sala
     * @param s nova sala da turma
     */
    void alteraSalaDeTurma(String tid, Sala s);

    /**
     * Método que verifica se uma turma existe
     *
     * @param tid id da turma a procurar
     * @return true se a turma existe
     */
    boolean existeTurma(String tid);

    /**
     * Método que procura um aluno
     *
     * @param num número do aluno a procurar
     * @return true se o aluno existe
     */
    Aluno procuraAluno(String num);

    /**
     * Método que devolve os alunos de uma turma.
     *
     * @param tid id da turma
     * @return alunos da turma
     */
    Collection<String> getAlunos(String tid);

    /**
     * Método que devolve todas as turmas
     *
     * @return todas as turmas
     */
    Collection<Turma> getTurmas();

    /**
     * Método que verifica se um aluno existe
     *
     * @param num número do aluno a procurar
     * @return true se o aluno existe
     */
    boolean existeAluno(String num);

    /**
     * Método que adiciona um aluno à turma.
     *
     * @param tid id da turma onde se vai colocar o aluno
     * @param num número do aluno
     */
    void adicionaAlunoTurma(String tid, String num);

    /**
     * Método que devolve todos os alunos registados.
     *
     * @return todos os alunos registados
     */
    Collection<Aluno> getAlunos();

    /**
     * Método que remove um aluno da turma.
     *
     * @param tid id da turma
     * @param num número do aluno a remover
     */
    void removeAlunoTurma(String tid, String num);

    /**
     * Método que verifica se o aluno existe na turma
     *
     * @param tid Turma a pesqueisar
     * @param num Número de aluno a pesquisar
     * @return True se aluno existe na turma
     */
    boolean existeAlunoEmTurma(String tid, String num);

    boolean existeSala(String num);

    void adicionaSala(Sala sala);

    Object procuraSala(String num);

    Object getSalas();
}