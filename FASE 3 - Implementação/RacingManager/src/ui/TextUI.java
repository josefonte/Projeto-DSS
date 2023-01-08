package ui;
import business.*;

import business.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TextUI  {
    // O model tem a 'lógica de negócio'.
    //private FacadeR
    //
    // aceManager

    private Model model;
    private Menu menu;
    private  boolean Admin;
    private Scanner is;

    public TextUI() {
        // Criar o menu

        this.model = new Model();

        this.menu = new Menu(new String[]{
                "Modo Admin",
                "Modo Jogador",
                "Criar User"
        });

        this.menu.setHandler(1,this::MenuPrincipal_Admin);
        this.menu.setHandler(2,this::MenuPrincipal_User);


        is = new Scanner(System.in);
    }

    public void MenuPrincipal_User() {

        Menu menuP = new Menu("Menu Principal",new String[]{
                "Jogar",
                "Consultar Pilotos",
                "Consultar Circuitos",
                "Consultar Carros"
        },true);

        menuP.setHandler(1, this::Jogar);
        menuP.setHandler(2, this::MenuPilotos_User);
        menuP.setHandler(3, this::MenuCircuitos_User);
        menuP.setHandler(4, this::MenuCarros_User);

        menuP.run();
    }

    public void MenuPrincipal_Admin() {
        // Criar o menu
        Menu menuP = new Menu("Menu Principal",new String[]{
                "Editar Campeonatos",
                "Editar Circuitos",
                "Editar Pilotos",
                "Editar Carros"
        },true);

        menuP.setHandler(1, this::MenuCampeonatos_Admin);
        menuP.setHandler(2, this::MenuCircuitos_Admin);
        menuP.setHandler(3, this::MenuPilotos_Admin);
        menuP.setHandler(4, this::MenuCarros_Admin);
        menuP.run();
    }

    public void MenuCampeonatos_Admin() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Campeonatos" , new String[]{
                "Ver lista de Campeonatos",
                "Consultar Info de um Campeonato",
                "Adicionar Campeonato",
                "Remover Campeonato"
        },true);

        menuP.setHandler(1, this::ListaCampeonatos);
        menuP.setHandler(3, this::AdicionarCampeonato);
        menuP.setHandler(4, this::RemoverCampeonato);
        menuP.run();
    }
    public void MenuCampeonatos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Campeonatos" , new String[]{
                "Ver lista de Campeonatos",
                "Consultar info de um campeonato"
        },true);

        menuP.setHandler(1, this::ListaCampeonatos);

        menuP.run();
    }

    public void MenuCircuitos_Admin() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Circuitos" , new String[]{
                "Ver lista de Circuitos",
                "Consultar Info de um Circuito",
                "Adicionar Circuito",
                "Remover Circuito",
        },true);

        menuP.setHandler(1, this::ListaCircuitos);
        menuP.setHandler(3, this::AdicionarCircuito);
        menuP.setHandler(4, this::RemoverCircuito);

        menuP.run();
    }

    public void MenuCircuitos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Circuitos" , new String[]{
                "Ver lista de Circuitos"
        });

        menuP.setHandler(1, this::ListaCircuitos);
        menuP.run();
    }

    public void MenuPilotos_Admin() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Pilotos" , new String[]{
                "Ver lista de Pilotos",
                "Consultar Info de um Piloto",
                "Adicionar Piloto",
                "Remover Piloto"
        },true);

        menuP.setHandler(1, this::ListaPilotos);
        menuP.setHandler(3, this::AdicionarPiloto);
        menuP.setHandler(4, this::RemoverPiloto);
        menuP.run();
    }
    public void MenuPilotos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Pilotos" , new String[]{
                "Ver lista de Pilotos",
                "Consultar Info de um Piloto"
        },true);

        menuP.setHandler(1, this::ListaPilotos);

        menuP.run();
    }

    public void MenuCarros_Admin() {

        Menu menuP = new Menu("Menu de Carros",new String[]{
                "Ver lista de Carros",
                "Consultar Info de um Carro",
                "Adicionar Carro",
                "Remover Carro",
        },true);

        menuP.setHandler(1, this::ListaCarros);
        menuP.setHandler(3, this::AdicionarCarro);
        menuP.setHandler(4, this::RemoverCarro);

        menuP.run();
    }
    public void MenuCarros_User() {

        Menu menuP = new Menu("Menu de Carros",new String[]{
                "Ver lista de Carros",
                "Consultar Info de um Carro",
        },true);

        menuP.setHandler(1,this::ListaCarros);

        menuP.run();
    }

    // ###################### METODOS AUXILIARES ##############################

    public void Jogar() {
         System.out.println("Jogando >-<");
    }

    public void ListaCampeonatos(){
        try {
            System.out.println("\n# Lista de Campeonatos #");
            for (String camp : model.infoCampeonatos(false)) System.out.println(camp);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void InfoCampeonato(){
        try {
            ListaCampeonatos();
            System.out.print("Campeonato em Detalhe: ");
            String nome = is.nextLine();
            System.out.println(model.getCampeonato(nome));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AdicionarCampeonato(){
        try {
            is = new Scanner(System.in);
            System.out.print("Nome do Campeonato: ");
            String nome = is.nextLine();

            //construtor de circuito Circuito circuito = new Circuito(nome,dist);
            //model.circuitos.add(circuito)
            //campeonatos.add(nome);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RemoverCampeonato(){
        try {
            ListaCircuitos();
            System.out.print("Nome do Campeonato a Remover:");
            is = new Scanner(System.in);
            String nome = is.nextLine();
           // model..remove();

            //model.removeCarro
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ListaCircuitos(){
        try {
            System.out.println("\n# Lista de Circuitos #");
            for (String camp : model.infoCampeonatos(false)) System.out.println(camp);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void InfoCircuito(){
        try {
                ListaCircuitos();
                String nome = "";
                while (!nome.equals("nao")){
                    System.out.println("Circuito em Detalhe (escreva 'nao', caso não deseje): ");
                    nome = is.nextLine();
                    System.out.println(model.getCircuito(nome));
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
    }

    public void AdicionarCircuito(){
        try {
            is = new Scanner(System.in);
            System.out.print("Nome do Circuito: ");
            String nome = is.nextLine();

            while(model.getCircuitos().containsKey(nome)){
                System.out.println("Nome do Circuito já existe");
                System.out.print("Nome do Circuito: ");
                nome = is.nextLine();
            }

            System.out.print("Distância do Circuito: ");
            float dist = is.nextFloat();

            Circuito circuito = new Circuito(nome,dist);
            model.addCircuito(circuito);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RemoverCircuito(){
        try {
            ListaCircuitos();
            System.out.println("Nome do Circuito a Remover: ");
            is = new Scanner(System.in);
            String nome = is.nextLine();
           // if (!model.removeCircuito(nome)) System.out.println("Circuito não Existe");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ListaPilotos(){
        try {
            System.out.println("\n# Lista de Pilotos #");
            for (String pil : model.infoPilotos(false)) System.out.println(pil);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void AdicionarPiloto(){
        try {
            is = new Scanner(System.in);
            System.out.println("Nome do Piloto: ");
            String nome = is.nextLine();
            System.out.println("Ratio Suave vs Agressivo (0-100): ");
            int sva = is.nextInt();
            System.out.println("Ratio Tempo Chuvoso vs Tempo Seco (0-100): ");
            int cts = is.nextInt();


            Piloto piloto = new Piloto(nome,sva,cts);
            model.addPiloto(piloto);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RemoverPiloto(){
        try {
            ListaCircuitos();
            is = new Scanner(System.in);
            System.out.println("Nome do Piloto a Remover:");
            String nome = is.nextLine();
            pilotos.remove(nome);

            //model.removePiloto
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void ListaCarros(){
        try {
            System.out.println("\n# Lista de Carros #");
            for (String carro : carros){
                System.out.println(carro);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void AdicionarCarro(){
        try {
            is = new Scanner(System.in);
            System.out.println("Marca: ");
            String marca = is.nextLine();
            System.out.println("Modelo: ");
            String modelo = is.nextLine();
            System.out.println("Cilindrada: ");
            int cilindrada = is.nextInt();
            System.out.println("Potencia: ");
            int potencia = is.nextInt();
            System.out.println("Fiabilidade: ");
            float fiabilidade = is.nextFloat();
            System.out.println("pac: ");
            int pac = is.nextInt();
            System.out.println("id: ");
            String id = is.nextLine();
            System.out.println("Pneus (Macio-Duro-Chuva): ");
            String p = is.nextLine();

            String pneus = switch (p){//TipoPneus pneus = switch (p) {
                case "MACIO", "macio", "Macio" -> "MACIO"; //TipoPneus.MACIO;
                case "DURO", "duro", "Duro" -> "DURO"; //TipoPneus.DURO;
                case "CHUVA", "chuva", "Chuva" -> "CHUVA";//TipoPneus.CHUVA;
                default -> "MACIO";
            };

            System.out.println("Modo Motor (Conservador-Normal-Agressivo): ");
            String mot = is.nextLine();
            String motor = switch (mot) {//ModoMotor motor =
                case "CONSERVADOR", "conservador", "Conservador" ->
                        "CONSERVADOR"; //ModoMotor.CONSERVADOR
                case "NORMAL", "normal", "Normal" -> "NORMAL"; //ModoMotor.NORMAL
                case "AGRESSIVO", "agressivo", "Agressivo" -> "AGRESSIVO"; //ModoMotor.AGRESSIVO
                default -> "NORMAL"; //ModoMotor.NORMAL
            };

            System.out.println("Categoria (C1-C2-GT-SC): ");
            String cat = is.nextLine();

            String categoria = switch (cat) {
                case "C1", "c1" -> "C1";
                case "C2", "c2" -> "C2";
                case "GT", "gt" -> "GT";
                case "SC","sc"-> "SC";
                default -> "c1";
            };

            //Carro carro = new Carro()
            //new Carro( categoria, marca,  modelo, cilindrada, potencia, fiabilidade, pac, id, pneus, motor)
            //model.addCarro()
            carros.add(modelo);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void RemoverCarro(){
        try {
            ListaCircuitos();
            System.out.println("Nome do Carro a Remover:");
            is = new Scanner(System.in);
            String nome = is.nextLine();
            circuitos.remove(nome);

            //model.removeCarro
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    /**
     * Executa o menu principal e invoca o método correspondente à opção seleccionada.
     */
    public void run() {
        this.menu.run();
        System.out.println("\nPrograma Encerrado. Até breve!...");
    }
}
