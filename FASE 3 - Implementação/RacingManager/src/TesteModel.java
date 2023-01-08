import business.*;
//import data.*;
import data.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class TesteModel {
    //public Map<String,Campeonato> campeonatos;
    public static Map<String,Carro> carros;
    public static Map<String,Circuito> circuitos;
    public static Map<String,Piloto> pilotos;
    public static Map<String,Utilizador> utilizadores;
    //public int contadorParticipante;

    public TesteModel(){
        //campeonatos = CampeonatoDAO.getInstance();
        //carros = CarroDAO.getInstance();
        circuitos = CircuitoDAO.getInstance();
        pilotos = PilotoDAO.getInstance();
        //utilizadores = UtilizadorDAO.getInstance();
        //contadorParticipante = 0;
    }
    public static void main(String[] args) {
        TesteModel testeModel = new TesteModel();
        DAOconfig DAOconfig = null;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)){
            Piloto p1 = new Piloto("Rafael", 2,3);
            Piloto p2 = new Piloto("Ines", 4,3);
            Piloto p3 = new Piloto("Raposo", 5,3);
            Piloto p4 = new Piloto("Ze", 1,3);
            Piloto p5 = new Piloto("Joao", 1,3);
            Piloto p6 = new Piloto("Rafael", 12, 2);
            pilotos.put("Rafael", p1);
            pilotos.put("Ines",p2);
            pilotos.put("Raposo",p3);
            pilotos.put("Ze",p4);
            pilotos.put("Joao",p5);
            pilotos.put("Rafael",p6);
            Piloto pget = pilotos.get("Rafael");
            System.out.println("pilotos antes : ");
            System.out.println(p1.toString());
            System.out.println(p2.toString());
            System.out.println(p3.toString());
            System.out.println(p4.toString());
            System.out.println(p5.toString());
            System.out.println(p6.toString());
            System.out.println("pilotos depois : ");
            System.out.println(pget.toString());
            TipoSegmento curva = TipoSegmento.CURVA;
            TipoSegmento chicane = TipoSegmento.CHICANE;
            TipoSegmento reta = TipoSegmento.RETA;
            SegmentoDePista s1 = new SegmentoDePista(1,2,reta);
            SegmentoDePista s2 = new SegmentoDePista(1,2,curva);
            SegmentoDePista s3 = new SegmentoDePista(1,2,reta);
            SegmentoDePista s4 = new SegmentoDePista(1,2,chicane);
            ArrayList<SegmentoDePista> l = new ArrayList<SegmentoDePista>();
            l.add(s1);
            l.add(s2);
            l.add(s3);
            l.add(s4);
            Circuito c1 = new Circuito(2,"Industrial",l);
            circuitos.put("Industrial",c1);
            Circuito c2 = circuitos.get("Industrial");
            System.out.println(c1.toString());
            System.out.println(c2.toString());

            l.add(s1);
            Circuito c3 = new Circuito(4, "Industrial", l);
            circuitos.put("Industrial",c3);

            Circuito c4 = circuitos.get("Industrial");
            System.out.println(c4.toString());

            Utilizador u1 = new Utilizador("Ze",TipoUtilizador.JOGADOR,15);
            utilizadores.put("Ze",u1);
            Utilizador u2 = utilizadores.get("Ze");
            System.out.println(u2.toString());
            Carro c = new C2("bmw","A5",6000,1,2,4,"as",11);
            carros.put("as",c);

            Carro carro1 = carros.get("as");
            System.out.println(carro1.toString());



        } catch (Exception e) {
            System.out.println("Não foi possível arrancar: " + e.getMessage());
        }
    }
}
