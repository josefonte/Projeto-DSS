package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TextUI  {
    // O model tem a 'lógica de negócio'.
    //private FacadeRaceManager

    private List<String> carros = new ArrayList<>();
    private List<String> pilotos = new ArrayList<>();
    private List<String> circuitos = new ArrayList<>();
    private Menu menu;
    private  boolean Admin;

    private Scanner is;

    /**
     * Construtor.
     *
     * Cria os menus e a camada de negócio.
     */
    public TextUI() {
        // Criar o menu
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

        menuP.setHandler(1, this::MenuPilotos_Admin);
        menuP.setHandler(2, this::MenuCircuitos_Admin);
        menuP.setHandler(3, this::MenuCarros_Admin);
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

        menuP.run();
    }
    public void MenuCampeonatos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Campeonatos" , new String[]{
                "Ver lista de Campeonatos",
        },true);

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
        menuP.run();
    }

    public void MenuCircuitos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Circuitos" , new String[]{
                "Ver lista de Circuitos",
                "Voltar"
        });
        menuP.setHandler(2, this::MenuPrincipal_Admin);
    }

    public void MenuPilotos_Admin() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Pilotos" , new String[]{
                "Ver lista de Pilotos",
                "Consultar Info de um Piloto",
                "Adicionar Piloto",
                "Remover Piloto"
        },true);

        menuP.run();
    }
    public void MenuPilotos_User() {
        // Criar o menu
        Menu menuP = new Menu("Menu dos Pilotos" , new String[]{
                "Ver lista de Pilotos",
                "Consultar Info de um Piloto"
        },true);

        menuP.run();
    }

    public void MenuCarros_Admin() {

        Menu menuP = new Menu("Menu de Carros",new String[]{
                "Ver lista de Carros",
                "Consultar Info de um Carro",
                "Adicionar Carros",
                "Remover Carros",
        },true);

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

    public void ListaCircuitos(){
        try {
            for (int i=0; i<20; i++) {
                circuitos.add("Circuito" + i);
            }
            System.out.println("\n# Lista de Circuitos #");
            for (String circuito : circuitos){
                System.out.println(circuito);
            }

            //model.getCarros()

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void AdicionaCircuito(){
        try {
            is = new Scanner(System.in);
            System.out.println("Nome do Circuito: ");
            String nome = is.nextLine();
            is.nextLine();
            System.out.println("Distância do Circuito: ");
            Float dist = is.nextFloat();

            //construtor de circuito Circuito circuito = new Circuito(nome,dist);
            //model.circuitos.add(circuito)
            circuitos.add(nome);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RemoveCircuito(){
        try {
            ListaCircuitos();
            System.out.println("Nome do Circuito a Remover:");
            is = new Scanner(System.in);
            String nome = is.nextLine();
            circuitos.remove(nome);

            //model.removeCarro
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void ListaPilotos(){
        try {
            for (int i=0; i<20; i++){
                pilotos.add("Piloto"+i);
            }
            System.out.println("\n# Lista de Pilotos #");
            for (String piloto : pilotos){
                System.out.println(piloto);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void AdicionaPiloto(){
        try {
            is = new Scanner(System.in);
            System.out.println("Nome do Piloto: ");
            String nome = is.nextLine();
            System.out.println("Ratio Suave vs Agressivo (0-100): ");
            int sva = is.nextInt();
            System.out.println("Ratio Tempo Chuvoso vs Tempo Seco (0-100): ");
            int cts = is.nextInt();

            pilotos.add(nome);
            // Piloto new =
            //model.pilotos.addPiloto();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void RemovePiloto(){
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
            for (int i=0; i<20; i++){
                carros.add("Carro"+i);
            }
            System.out.println("\n# Lista de Carros #");
            for (String carro : carros){
                System.out.println(carro);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void AdicionaCarro(){
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
            is.nextLine();
            System.out.println("Pneus (Macio-Duro-Chuva): ");
            String pneus = is.nextLine();

            if (pneus.equals("MACIO") || pneus.equals("macio") ||pneus.equals("Macio")) pneus = "MACIO"; //TipoPneus pneus = TipoPneus.MACIO;
            else if (pneus.equals("DURO") || pneus.equals("duro") || pneus.equals("Duro")) pneus = "DURO"; //TipoPneus pneus = TipoPneus.DURO;
            else if (pneus.equals("CHUVA") || pneus.equals("chuva") ||pneus.equals("Chuva")) pneus = "CHUVA";//TipoPneus pneus = TipoPneus.CHUVA;
            else //TipoPneus pneus = TipoPneus.MACIO;
            is.nextLine();

            System.out.println("Modo Motor (Conservador-Normal-Agressivo): ");
            is.nextLine();
            String motor = is.nextLine();
            String mot = switch (motor) {//ModoMotor motor =
                case "CONSERVADOR", "conservador", "Conservador" ->
                        "CONSERVADOR"; //ModoMotor.CONSERVADOR
                case "NORMAL", "normal", "Normal" -> "NORMAL"; //ModoMotor.NORMAL
                case "AGRESSIVO", "agressivo", "Agressivo" -> "AGRESSIVO"; //ModoMotor.AGRESSIVO
                default -> "NORMAL"; //ModoMotor.NORMAL
            };

            is.nextLine();

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

    public void RemoveCarro(){
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
